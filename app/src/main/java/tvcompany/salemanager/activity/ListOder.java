package tvcompany.salemanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ListOderAdapter;
import tvcompany.salemanager.model.Group;

public class ListOder extends AppCompatActivity {
    ArrayList<Group> list;
    ListOderAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_oder);
        lv = (ListView) findViewById(R.id.lv_oder);
        list =new ArrayList<Group>();
        for (int i = 0;i<50;i++){
            Group group = new Group(0,"http://cdn.comedia.coccoc.com/2016-06-12/f8/d3/403f718a172533c52d85ee21d2da.jpg",null,null,null);
            list.add(group);
        }
        adapter = new ListOderAdapter(list,ListOder.this);
        lv.setAdapter(adapter);
    }
}
