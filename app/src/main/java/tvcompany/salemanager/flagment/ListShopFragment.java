package tvcompany.salemanager.flagment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ListShopAdapterFlg;
import tvcompany.salemanager.controller.login.ShopController;
import tvcompany.salemanager.model.Shop;


public class ListShopFragment extends Fragment {
    private List<Shop> listShop;
    private ListView lv;

    private ListShopAdapterFlg adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_shop_layout, container, false);
        lv = (ListView) rootView.findViewById(R.id.listShop);
        try {
            listShop =new ShopController().getListShop("57713f3a3893b0f02813f08b");
        }catch (Exception e)
        {
            listShop = new ArrayList<>();
        }
        adapter = new ListShopAdapterFlg(listShop,this,20,10,inflater);
        lv.setAdapter(adapter);

        return rootView;
    }
}

