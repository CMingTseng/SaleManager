package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.activity.ProductActivity;
import tvcompany.salemanager.activity.ShopActivity;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.model.Product;
import tvcompany.salemanager.model.Shop;


public class ListProductAdapter1 extends RecyclerView.Adapter<ListProductAdapter1.MyViewHolder> {

    private List<Product> listProduct;
    private Fragment context;
    private ImageView imgView;
    private FrameLayout frameLayout;
    private TextView shopName,shopID,manager;
    private Product product;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(final View view) {
            super(view);
            // khai bao thuộc trên layout item ở đây
            imgView = (ImageView) view.findViewById(R.id.iconshop);
            frameLayout = (FrameLayout) view.findViewById(R.id.editShop);
            shopID = (TextView) view.findViewById(R.id.shopDetailID);
            shopName = (TextView) view.findViewById(R.id.shopDetailName);
            manager = (TextView) view.findViewById(R.id.shopManager);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    product = listProduct.get(position);
                    Intent i = new Intent(context.getActivity(), ShopActivity.class);
                    i.putExtra("Product", product);
                    context.startActivity(i);
                }
            });
        }

    }

    public ListProductAdapter1(Fragment context, List<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_shop, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        product = listProduct.get(position);
        shopName.setText(product.getProductName());
        shopID.setText(product.getID());
        manager.setText(GlobalValue.USERNAME);
        try
        {
            if(!product.getImage().equals(""))
            {
                Picasso.with(context.getActivity()).load(GlobalValue.CONFIG + product.getImage().replace("::","/")).into(imgView);
            }

        }
        catch (Exception e)
        {}
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

}
