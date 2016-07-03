package tvcompany.salemanager.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.activity.ShopActivity;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.model.Shop;


public class ListShopAdapterFlg extends BaseAdapter {
    private List<Shop> list;
    private Fragment context;
    private LayoutInflater inflater;
    private int count;
    private int stepNumber;
    private int startCount;
    private Shop shop;
    private ViewHolder holder;
    public ListShopAdapterFlg(List<Shop> list, Fragment context, int stepNumber, int startCount, LayoutInflater inflater) {
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
        shop = list.get(position);
        holder = new ViewHolder();
        holder.shopName = (TextView) view.findViewById(R.id.shopDetailName);
        holder.shopID = (TextView) view.findViewById(R.id.shopDetailID);
        holder.manager = (TextView) view.findViewById(R.id.shopManager);
        holder.frameLayout = (FrameLayout) view.findViewById(R.id.editShop);
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop = list.get(position);
                Intent i= new Intent(context.getActivity(), ShopActivity.class);
                i.putExtra("Shop",  shop);
                context.startActivity(i);
            }
        });

        holder.imgView = (ImageView) view.findViewById(R.id.iconshop);
        try {
            Picasso.with(context.getActivity()).load(GlobalValue.CONFIG + shop.getImage().replace("::","/")).into(holder.imgView);
        }
        catch (Exception e){}
        holder.shopName.setText(shop.getShopName());
        holder.shopID.setText("ID: "+shop.getId());
        holder.manager.setText("Quản lý: MtViet");
        return view;
    }
    public class ViewHolder{
        ImageView imgView;
        FrameLayout frameLayout;
        TextView shopName,shopID,manager;
    }
}
