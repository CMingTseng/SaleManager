package tvcompany.salemanager.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;
import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ContactAdapter;
import tvcompany.salemanager.model.Contact;
import tvcompany.salemanager.model.Message;

/**
 * Created by Administrator on 15/06/2016.
 */
public class ContactActivity extends Fragment {

    private ContactAdapter adapter;
    private List<Contact> listContact;
    private ListView listViewContact;
    private Socket mSocket;
    private Button btnSend;
    private String userSend, userRecieve;
    private Message message;
    private EditText txtMessage;
    private Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_layout, container, false);


        // Get list danh bạ gán vào Adapter
        // 2 nguồn lấy: trong SQLite hoặc lấy từ SERVER
        listContact = new ArrayList<Contact>();
        //Contact contact= new Contact("Gandalf","Phạm Mạnh Tùng","0163.567.2888", BitmapFactory.decodeResource(getResources(), R.mipmap.icon));
        //Contact contact2= new Contact("MtViet","Mai Thanh Việt","0164.632.2112", BitmapFactory.decodeResource(getResources(), R.mipmap.vietfuck));
        //listContact.add(contact);
        //listContact.add(contact2);
        ///Adapter
        listViewContact = (ListView) rootView.findViewById(R.id.listContact);
        adapter = new ContactAdapter(getActivity(), listContact);
        listViewContact.setAdapter(adapter);
        ///
        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selItem = (Contact) listViewContact.getSelectedItem(); //

            }
        });
        return rootView;
    }

}
