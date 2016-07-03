package tvcompany.salemanager.flagment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ListOrderAdapterFlg;
import tvcompany.salemanager.controller.login.ProductController;
import tvcompany.salemanager.model.Product;


public class ListOrderFlagment extends Fragment {
    private List<Product> listProduct;
    private ListView lv;

    private ListOrderAdapterFlg adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_order_layout, container, false);
        lv = (ListView) rootView.findViewById(R.id.listOrder);
        try {
            listProduct =new ProductController().getListProduct();
            for(int i = 0;i<1000;i++)
            {
                listProduct.add(listProduct.get(1));
            }
        }catch (Exception e)
        {

        }
        adapter = new ListOrderAdapterFlg(listProduct,this,20,10,inflater);
        lv.setAdapter(adapter);

        return rootView;
    }
}
