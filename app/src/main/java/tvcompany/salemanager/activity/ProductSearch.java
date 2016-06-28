package tvcompany.salemanager.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import API.ServiceInterface;
import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ContactAdapter;
import tvcompany.salemanager.adapter.ProductAdapter;
import tvcompany.salemanager.model.Contact;
import tvcompany.salemanager.model.User;

/**
 * Created by Administrator on 27/06/2016.
 */
public class ProductSearch extends Activity {
    private ProductAdapter adapter;
    private List<Contact> listContact;
    private ListView listViewContact;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_product);

        listContact = new ArrayList<Contact>();
        Contact contact= new Contact("Gandalf","Phạm Mạnh Tùng","0163.567.2888", BitmapFactory.decodeResource(getResources(), R.mipmap.icon));
        Contact contact2= new Contact("MtViet","Mai Thanh Việt","0164.632.2112", BitmapFactory.decodeResource(getResources(), R.mipmap.vietfuck));
        listContact.add(contact);
        listContact.add(contact2);
        ///Adapter
        listViewContact = (ListView) findViewById(R.id.listProduct);
        adapter = new ProductAdapter(ProductSearch.this, listContact);
        listViewContact.setAdapter(adapter);
    }
}
