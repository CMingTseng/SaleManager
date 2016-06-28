package tvcompany.salemanager.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ExpandListAdapter;
import tvcompany.salemanager.model.Child;
import tvcompany.salemanager.model.Group;

public class OderActivity extends AppCompatActivity {
    private ExpandListAdapter ExpAdapter;
    private ArrayList<Group> ExpListItems  = new ArrayList<Group>();
    private ExpandableListView ExpandList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listoder);
        Child child = new Child("so luong");
        ArrayList<Child> ChildList = new ArrayList<Child>();
        ChildList.add(child);
        String text = "DEMO_TV";
        for (int i =0;i<300;i++){
            Group group = new Group(R.drawable.images,text+i,text+i,text+i,ChildList);
            ExpListItems.add(group);

        }
        Log.d("GroupLisst",String.valueOf(ExpListItems.size()));
        ExpandList = (ExpandableListView) findViewById(R.id.exp_list);
        ExpAdapter = new ExpandListAdapter(getApplicationContext(), ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
    }
}
