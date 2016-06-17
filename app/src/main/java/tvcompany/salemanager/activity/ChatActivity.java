package tvcompany.salemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.MessagesListAdapter;
import tvcompany.salemanager.model.Message;
import tvcompany.salemanager.model.ServerApplication;

public class ChatActivity extends AppCompatActivity {

    private MessagesListAdapter adapter;
    private List<Message> listMessages;
    private ListView listViewMessages;
    private Socket mSocket;
    private Button btnSend;
    private String userSend,userRecieve;
    private Message message;
    private EditText txtMessage;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        userSend = intent.getExtras().getString("FROM");
        userRecieve = intent.getExtras().getString("TO");

        // HELLO Con chó Việt

        //Listening...
        try {
            mSocket = IO.socket(ServerApplication.CHAT_SERVER_URL);
            mSocket.connect();
            mSocket.on(userSend, onNewMessage);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


        //Text Message
        txtMessage = (EditText) findViewById(R.id.inputMsg);

        ///Adapter
        listViewMessages = (ListView) findViewById(R.id.list_view_messages);
        listMessages = new ArrayList<Message>();
        adapter = new MessagesListAdapter(this, listMessages);
        listViewMessages.setAdapter(adapter);
        ///

        //Gson
        gson = new Gson();

        //Send Data
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    message = new Message(userRecieve,txtMessage.getText().toString(),false);
                    appendMessage(new Message(userSend,txtMessage.getText().toString(),true));
                    JSONObject obj = new JSONObject(gson.toJson(message));
                    txtMessage.setText("");
                    mSocket.emit("SendToServer",obj);
                }
                catch (Exception e)
                {}
            }
        });
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
                        message.setFromName(userRecieve);
                        appendMessage(message);
                    } catch (JSONException e) {
                        return;
                    }


                }
            });
        }
    };

}
