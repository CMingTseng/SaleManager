package tvcompany.salemanager.FragMent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.ListShopAdapter;
import tvcompany.salemanager.controller.login.ShopController;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.model.Shop;

/**
 * Created by Administrator on 7/1/2016.
 */
public class ListShopFragMent extends Fragment {
    private List<Shop> listShop;
    private RecyclerView recyclerView;
    private ShopController shopController;
    private ListShopAdapter recyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_shop_layout, container, false);
        shopController = new ShopController();
//        try {
//            listShop = shopController.getListShop(GlobalValue.ID);
//        }catch (Exception e)
//        {
//            listShop = new ArrayList<Shop>();
//        }
        listShop = new ArrayList<Shop>();
        for (int i = 0;i<2;i++){
            Shop sh = new Shop();
            sh.setShopName("Duong");
            listShop.add(sh);
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_listshop);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter= new ListShopAdapter(getActivity(),listShop);
        recyclerView.setAdapter(recyclerViewAdapter);
        return rootView;
    }
}
