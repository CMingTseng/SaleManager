package tvcompany.salemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.TestAdapter;
import tvcompany.salemanager.controller.login.ProductController;
import tvcompany.salemanager.controller.login.ShopController;
import tvcompany.salemanager.model.Product;

public class ListProductFlagment extends Fragment {
    private List<Product> listShop;
    private ListView lv;

    private TestAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_product_layout, container, false);
        lv = (ListView) rootView.findViewById(R.id.listProduct);
        try {
            listShop =new ProductController().getListProduct();
            for(int i = 0;i<1000;i++)
            {
                listShop.add(listShop.get(1));
            }
        }catch (Exception e)
        {

        }
        adapter = new TestAdapter(listShop,this,20,10,inflater);
        lv.setAdapter(adapter);

        return rootView;
    }
}
