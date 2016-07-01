package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.activity.ProductActivity;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.model.Product;


public class TestAdapter extends BaseAdapter {
    private List<Product> list;
    private Fragment context;
    private LayoutInflater inflater;
    private int count;
    private int stepNumber;
    private int startCount;
    private Product product;
    private ViewHolder holder;
    public TestAdapter(List<Product> list, Fragment context, int stepNumber, int startCount,LayoutInflater inflater) {
        this.list = list;
        this.context = context;
        this.stepNumber = stepNumber;
        this.count = this.startCount;
        this.stepNumber = stepNumber;
        this.startCount = Math.min(startCount, list.size());
        this.inflater = inflater;
    }

    public boolean showMore(){
        if(count == list.size()) {
            return true;
        }else{
            count = Math.min(count + stepNumber, list.size());
            notifyDataSetChanged();
            return endReached();
        }
    }

    public boolean endReached(){
        return count == list.size();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_list_shop, parent, false);
        product = list.get(position);
        holder = new ViewHolder();
        holder.shopName = (TextView) view.findViewById(R.id.shopDetailName);
        holder.shopID = (TextView) view.findViewById(R.id.shopDetailID);
        holder.manager = (TextView) view.findViewById(R.id.shopManager);
        holder.frameLayout = (FrameLayout) view.findViewById(R.id.editShop);
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product = list.get(position);
                Intent i= new Intent(context.getActivity(), ProductActivity.class);
                i.putExtra("Product",  product);
                context.startActivity(i);
            }
        });

        holder.imgView = (ImageView) view.findViewById(R.id.iconshop);
        try {
            Picasso.with(context.getActivity()).load(GlobalValue.CONFIG + product.getImage().replace("::","/")).into(holder.imgView);
        }
        catch (Exception e){}
        holder.shopName.setText(product.getProductName());
        holder.shopID.setText(product.getID());
        holder.manager.setText(GlobalValue.USERNAME);
        return view;
    }
    public class ViewHolder{
        ImageView imgView;
        FrameLayout frameLayout;
        TextView shopName,shopID,manager;
    }
}
