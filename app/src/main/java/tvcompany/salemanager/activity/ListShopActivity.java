package tvcompany.salemanager.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ListShopAdapter;
import tvcompany.salemanager.adapter.ProductRecyclerViewAdapter;
import tvcompany.salemanager.controller.login.ShopController;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.model.Contact;
import tvcompany.salemanager.model.Shop;


public class ListShopActivity extends AppCompatActivity {
    private ListShopAdapter adapter;
    private List<Shop> listShop;
    private RecyclerView recyclerView;
    private ShopController shopController;
    private ListShopAdapter recyclerViewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_shop_layout);
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

//        recyclerView = (RecyclerView) findViewById(R.id.recycler_listshop);
//        recyclerViewAdapter= new ListShopAdapter(ListShopActivity.this,listShop);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        //recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
