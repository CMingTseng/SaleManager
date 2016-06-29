package tvcompany.salemanager.activity;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.SpinnerAdapter;
import tvcompany.salemanager.model.Group;

public class SpinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        ArrayList<Group> list = new ArrayList<Group>();
        for (int i = 0;i<15;i++){
            Group group = new Group(0,"Tv"+i,null,null,null);
            list.add(group);
            Log.d("Spinnerlog",String.valueOf(list.get(i).getText1()));
        }
        Resources rs = getResources();
        Spinner spinner = (Spinner) findViewById(R.id.spinner_dm);
        final SpinnerAdapter adapter = new SpinnerAdapter(SpinnerActivity.this,list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Group group = (Group) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),group.getText1(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
