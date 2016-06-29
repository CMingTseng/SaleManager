package tvcompany.salemanager.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ListOderAdapter;
import tvcompany.salemanager.model.Group;

public class ListOder extends AppCompatActivity implements AbsListView.OnScrollListener{
    ArrayList<Group> list;
    ListOderAdapter adapter;
    ListView lv;
    private ProgressBar progressBar;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_oder);
        lv = (ListView) findViewById(R.id.lv_oder);
        mHandler = new Handler();
        View footer = getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
        lv.addFooterView(footer);
        progressBar = (ProgressBar) footer.findViewById(R.id.progressBar);
        list =new ArrayList<Group>();
        for (int i = 0;i<10000;i++){
            Group group = new Group(R.drawable.image,"http://cdn.comedia.coccoc.com/2016-06-12/f8/d3/403f718a172533c52d85ee21d2da.jpg",String.valueOf(i),null,null);
            list.add(group);
        }
        adapter = new ListOderAdapter(list,ListOder.this,20,10);
        lv.setAdapter(adapter);
        lv.setOnScrollListener(this); //listen for a scroll movement to the bottom
        progressBar.setVisibility((20 < list.size())? View.VISIBLE : View.GONE);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem + visibleItemCount == totalItemCount && !adapter.endReached() && !hasCallback){ //check if we've reached the bottom
            mHandler.postDelayed(showMore, 300);
            hasCallback = true;
        }
    }
    private boolean hasCallback;
    private Runnable showMore = new Runnable(){
        public void run(){
            boolean noMoreToShow = adapter.showMore(); //show more views and find out if
            progressBar.setVisibility(noMoreToShow? View.GONE : View.VISIBLE);
            hasCallback = false;
        }
    };
}
