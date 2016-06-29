package tvcompany.salemanager.activity;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ListShopAdapter;
import tvcompany.salemanager.adapter.ProductAdapter;
import tvcompany.salemanager.controller.login.ShopController;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.model.Contact;
import tvcompany.salemanager.model.Shop;

/**
 * Created by MtViet on 29/06/2016.
 */
public class ListShopActivity extends Activity {
    private ListShopAdapter adapter;
    private List<Shop> listShop;
    private ListView listViewContact;
    private ShopController shopController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_product);
        shopController = new ShopController();
        try {
            listShop = shopController.getListShop(GlobalValue.ID);
        }catch (Exception e)
        {
            listShop = new ArrayList<Shop>();
        }

//        Contact contact= new Contact("Gandalf","Phạm Mạnh Tùng","0163.567.2888", BitmapFactory.decodeResource(getResources(), R.mipmap.icon));
//        Contact contact2= new Contact("MtViet","Mai Thanh Việt","0164.632.2112", BitmapFactory.decodeResource(getResources(), R.mipmap.vietfuck));
//        listContact.add(contact);
//        listContact.add(contact2);
        ///Adapter
        listViewContact = (ListView) findViewById(R.id.listProduct);
        adapter = new ListShopAdapter(ListShopActivity.this, listShop);
        listViewContact.setAdapter(adapter);

    }
}
