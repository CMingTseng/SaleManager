package tvcompany.salemanager.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.MessagesListAdapter;
import tvcompany.salemanager.database.DatabaseManager;
import tvcompany.salemanager.model.Message;
import tvcompany.salemanager.model.ServerApplication;

public class ChatActivity extends AppCompatActivity {

    private MessagesListAdapter adapter;
    private List<Message> listMessages;
    private ListView listViewMessages;
    private Socket mSocket;
    private Button btnSend;
    private String userSend, userRecieve;
    private Message message;
    private EditText txtMessage;
    private Gson gson;
    private boolean mTyping = false;
    private Handler mTypingHandler = new Handler();
    private Boolean isConnected = true;
    private DatabaseManager db;
    private long numberMessage;
    private static final int TYPING_TIMER_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        userSend = intent.getExtras().getString("FROM");
        userRecieve = intent.getExtras().getString("TO");

        // init data and get data from client
        //Text Message
        txtMessage = (EditText) findViewById(R.id.inputMsg);

        ///Adapter
        listViewMessages = (ListView) findViewById(R.id.list_view_messages);
        listMessages = new ArrayList<Message>();
        adapter = new MessagesListAdapter(this, listMessages);
        listViewMessages.setAdapter(adapter);
        try {
            db = new DatabaseManager(ChatActivity.this);
            GetMessage(userSend, userRecieve);

        } catch (Exception e) {
            Log.i("BUGGGGGGGGGGGGG", e.toString());
        } finally {
            SetNumberMessage();
        }
        //Gson
        gson = new Gson();

        //Listening...
        try {

            mSocket = IO.socket(ServerApplication.CHAT_SERVER_URL);
            mSocket.connect();
            mSocket.on(Socket.EVENT_CONNECT,onConnect);
            mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
            //EmittingOnline();
            mSocket.on(userSend, onNewMessage);
            mSocket.on(userSend + "Viewed", viewed);
            mSocket.on(userSend + "LoadMessage", loadMessage);
            mSocket.on(userSend + "Sent", MessageSent);
            mSocket.on(userSend + "Recieved", recieved);
            mSocket.on(userSend + "Typing", onTyping);
            mSocket.on(userSend + "StopTyping", onStopTyping);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        //Send Data
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    message = new Message(userSend, userRecieve, txtMessage.getText().toString(), 1, 1, new Date(), 1);
                    appendMessage(new Message(userSend, userRecieve, txtMessage.getText().toString(), 1, 1, new Date(), 0));
                    db.InserMessage(new Message(userSend, userRecieve, txtMessage.getText().toString(), 1, numberMessage++, new Date(), 0));
                    JSONObject obj = new JSONObject(gson.toJson(message));
                    txtMessage.setText("");
                    removeTyping();
                    removeStatus();
                    mSocket.emit("SendToServer", obj);

                } catch (Exception e) {
                    Log.i("BUGGGGGGGGGGGGG", e.toString());
                }
            }
        });

        //Text change
        txtMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mSocket.connected()) return;
                if (!mTyping && !txtMessage.getText().toString().equals("")) {
                    try {

                        mTyping = true;
                        message = new Message(userRecieve, userSend, userSend + " is typing...", 1, 1, new Date(), 2);
                        JSONObject obj = new JSONObject(gson.toJson(message));
                        mSocket.emit("Typing", obj);


                    } catch (Exception e) {
                    }
                }
                mTypingHandler.removeCallbacks(onTypingTimeout);
                mTypingHandler.postDelayed(onTypingTimeout, TYPING_TIMER_LENGTH);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.off(Socket.EVENT_CONNECT,onConnect);
        mSocket.off(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.off(userSend, onNewMessage);
        mSocket.off(userSend + "Viewed", viewed);
        mSocket.off(userSend + "LoadMessage", loadMessage);
        mSocket.off(userSend + "Sent", MessageSent);
        mSocket.off(userSend + "Recieved", recieved);
        mSocket.off(userSend + "Typing", onTyping);
        mSocket.off(userSend + "StopTyping", onStopTyping);
    }

    private void appendMessage(final Message m) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                listMessages.add(m);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private Emitter.Listener viewed = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setStatusMessage("Đã xem");
                }
            });
        }
    };

    private Emitter.Listener MessageSent = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listMessages.add(new Message(userRecieve, userSend, "Đã gửi", 1, 1, new Date(), 3));
                    adapter.notifyDataSetChanged();
                }
            });
        }
    };

    private Emitter.Listener recieved = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setStatusMessage("Đã nhận");
                }
            });
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    byte[] amthanh;
                    try {
                        String s = data.get("Content").toString();
                        message = gson.fromJson(s, Message.class);
                        removeTyping();
                        removeStatus();
                        appendMessage(message);
                        message.setIdSort(numberMessage++);
                        db.InserMessage(message);
                        mSocket.emit("Recieved", userRecieve);
                        if (txtMessage.isShown()) {
                            mSocket.emit("Viewed", userRecieve);
                        }
                    } catch (JSONException e) {
                        return;
                    }


                }
            });
        }
    };


    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    try {
                        String s = data.get("Content").toString();
                        message = gson.fromJson(s, Message.class);

                    } catch (Exception e) {
                        return;
                    }
                    appendMessage(message);
                }
            });
        }
    };

    private Emitter.Listener onStopTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    try {
                        String s = data.get("Content").toString();
                        message = gson.fromJson(s, Message.class);
                    } catch (JSONException e) {
                        return;
                    }
                    removeTyping();
                }
            });
        }
    };


    private void removeTyping() {
        try {
            if (listMessages.get(listMessages.size() - 1).getTypeAction() == 2) {
                listMessages.remove(listMessages.size() - 1);
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }

    }

    private Runnable onTypingTimeout = new Runnable() {
        @Override
        public void run() {
            if (!mTyping) return;
            try {
                message = new Message(userRecieve, userSend, "", 1, 1, new Date(), 2);
                JSONObject obj = new JSONObject(gson.toJson(message));
                mTyping = false;
                mSocket.emit("StopTyping", obj);
            } catch (Exception e) {
            }

        }
    };

    public void setStatusMessage(String status) {
        try {
            if (listMessages.get(listMessages.size() - 1).getTypeAction() == 3) {
                listMessages.get(listMessages.size() - 1).setData(status);
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {

        }
    }

    public void removeStatus() {
        try {
            for (int i = listMessages.size() - 1; i >= listMessages.size() - 5; i--) {
                if (listMessages.get(i).getTypeAction() == 3) {
                    listMessages.remove(i);
                    adapter.notifyDataSetChanged();
                    break;
                }

            }
        } catch (Exception e) {

        }
    }

    private Emitter.Listener loadMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    byte[] amthanh;
                    try {
                        String s = data.get("Content").toString();
                        TypeToken<List<Message>> token = new TypeToken<List<Message>>() {
                        };
                        List<Message> list = gson.fromJson(s, token.getType());
                        for (int i = 0; i < list.size(); i++) {
                            listMessages.add(list.get(i));
                            message = list.get(i);
                            message.setIdSort(numberMessage++);
                            db.InserMessage(message);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        return;
                    }


                }
            });
        }
    };

    public void GetMessage(String userSend, String userRecieve) {
        try {
            List<Message> list = db.GetMessage(userSend, userRecieve);
            for (int i =list.size() -1;i>=0; i--) {
                listMessages.add(list.get(i));
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.i("BUGGGGGGGGGGGGG", e.toString());
        }
    }

    public void SetNumberMessage() {
        try {
            for (int i = listMessages.size() - 1; i >= 0; i--) {
                if (listMessages.get(i).getTypeAction() == 0 || listMessages.get(i).getTypeAction() == 1) {
                    numberMessage = listMessages.get(i).getIdSort();
                    break;
                }
            }

        } catch (Exception e) {
            numberMessage = 0;
        }
    }

    public void EmittingOnline()
    {
        mSocket.emit("UserOnline",userSend);
        mSocket.emit("LoadMessage",userRecieve);
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EmittingOnline();
                }
            });
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSocket.emit("Viewed", userRecieve);
    }
}
