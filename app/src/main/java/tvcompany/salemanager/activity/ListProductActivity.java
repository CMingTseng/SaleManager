package tvcompany.salemanager.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ListProductAdapter;
import tvcompany.salemanager.controller.login.ProductController;
import tvcompany.salemanager.model.Group;
import tvcompany.salemanager.model.Product;

public class ListProductActivity extends AppCompatActivity implements AbsListView.OnScrollListener{
    private ArrayList<Group> list;
    private ListProductAdapter adapter;
    private ListView lv;
    private ProgressBar progressBar;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_product_layout);
        lv = (ListView) findViewById(R.id.listProduct);
        mHandler = new Handler();
        View footer = getLayoutInflater().inflate(R.layout.progress_bar_footer, null);
        lv.addFooterView(footer);
        progressBar = (ProgressBar) footer.findViewById(R.id.progressBar);
        list =new ArrayList<Group>();
        List<Product> listShop = new ProductController().getListProduct();
        adapter = new ListProductAdapter(listShop,ListProductActivity.this,20,10);
        lv.setAdapter(adapter);
        lv.setOnScrollListener(this);
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
            boolean noMoreToShow = adapter.showMore();
            progressBar.setVisibility(noMoreToShow? View.GONE : View.VISIBLE);
            hasCallback = false;
        }
    };
}
