package tvcompany.salemanager.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import API.ServiceInterface;
import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ContactAdapter;
import tvcompany.salemanager.adapter.GridViewAdapter;
import tvcompany.salemanager.adapter.ProductAdapter;
import tvcompany.salemanager.adapter.ProductRecyclerViewAdapter;
import tvcompany.salemanager.model.Contact;
import tvcompany.salemanager.model.User;

/**
 * Created by Administrator on 27/06/2016.
 */
public class ProductSearch extends AppCompatActivity {
    private ProductAdapter adapter;
    private List<Contact> listContact;
    private ListView listViewContact;
    private boolean flag = true;
    private RecyclerView recyclerView;
    ProductRecyclerViewAdapter recyclerViewAdapter;
    ViewStub listview, gridview;
    private ListView lists;
    private GridView grids;
    private boolean list_visibile = true;
    private GridViewAdapter gridViewAdapter;
    private Toolbar mToolbar;
    public ProductSearch() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_layout);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listContact = new ArrayList<Contact>();
        Contact contact = new Contact("Gandalf", "Phạm Mạnh Tùng", "0163.567.2888", BitmapFactory.decodeResource(getResources(), R.mipmap.icon));
        Contact contact2 = new Contact("MtViet", "Mai Thanh Việt", "0164.632.2112", BitmapFactory.decodeResource(getResources(), R.mipmap.vietfuck));
        listContact.add(contact);
        listContact.add(contact2);
        ///Adapter
//        listViewContact = (ListView) findViewById(R.id.listProduct);
//        listViewContact.setTranscriptMode(ListView.TRANSCRIPT_MODE_DISABLED);
//
//        adapter = new ProductAdapter(ProductSearch.this, listContact);
//        listViewContact.setAdapter(adapter);
//
//        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });


//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerViewAdapter= new ProductRecyclerViewAdapter(ProductSearch.this,listContact);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(recyclerViewAdapter);

        listview = (ViewStub) findViewById(R.id.list_view);
        gridview = (ViewStub) findViewById(R.id.grid_view);


        //inflate the layouts
        listview.inflate();
        gridview.inflate();

        grids = (GridView) findViewById(R.id.grid);
        lists = (ListView) findViewById(R.id.list);

        //adapter = new ProductAdapter(ProductSearch.this, listContact);
        //lists.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //Item Exit
        if (id == R.id.action_search) {
            System.exit(0);
        }

        //Item Change View
        else if(id == R.id.action_settings) {
            changeView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void changeView() {

        //if the current view is the listview, passes to gridview
        if (list_visibile) {
            listview.setVisibility(View.GONE);
            gridview.setVisibility(View.VISIBLE);
            list_visibile = false;
            setAdapters();
        } else {
            gridview.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
            list_visibile = true;
            setAdapters();
        }

    }

    /**
     * Method to set the adapters for the view
     */
    private void setAdapters() {

        if (list_visibile) {
            adapter = new ProductAdapter(ProductSearch.this, listContact);
            lists.setAdapter(adapter);
        } else {
            gridViewAdapter = new GridViewAdapter(ProductSearch.this, R.layout.item_list_gird_layout, listContact);
            grids.setAdapter(gridViewAdapter);
        }

    }
}