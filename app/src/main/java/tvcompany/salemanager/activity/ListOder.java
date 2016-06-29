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
        for (int i = 0;i<1000;i++){
            Group group = new Group(0,"http://i.telegraph.co.uk/multimedia/archive/03589/Wellcome_Image_Awa_3589699k.jpg",String.valueOf(i),null,null);
            list.add(group);
        }
        adapter = new ListOderAdapter(list,ListOder.this);
        lv.setAdapter(adapter);
    }
}
