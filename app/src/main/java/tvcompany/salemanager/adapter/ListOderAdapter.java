package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.model.Group;
import tvcompany.salemanager.model.Shop;

/**
 * Created by Administrator on 6/29/2016.
 */
public class ListOderAdapter extends BaseAdapter {
    List<Shop> list;
    Activity context;
    private int count;
    private int stepNumber;
    private int startCount;

    public ListOderAdapter(List<Shop> list, Activity context, int stepNumber, int startCount) {
        this.list = list;
        this.context = context;
        this.stepNumber = stepNumber;
        this.count = this.startCount;
        this.stepNumber = stepNumber;
        this.startCount = Math.min(startCount, list.size());
    }

    public boolean showMore(){
        if(count == list.size()) {
            return true;
        }else{
            count = Math.min(count + stepNumber, list.size()); //don't go past the end
            notifyDataSetChanged(); //the count size has changed, so notify the super of the change
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_shop, parent, false);
        Shop group = (Shop) list.get(position);
        ViewHolder holder = new ViewHolder();
        Display display = context.getWindowManager().getDefaultDisplay();
        holder.shopName = (TextView) view.findViewById(R.id.shopDetailName);
        holder.shopID = (TextView) view.findViewById(R.id.shopDetailID);
        holder.manager = (TextView) view.findViewById(R.id.shopManager);

        holder.imgView = (ImageView) view.findViewById(R.id.iconshop);
        Picasso.with(context).load(GlobalValue.CONFIG + group.getImage().replace("::","/")).into(holder.imgView);
        holder.shopName.setText(group.getShopName());
        holder.shopID.setText(group.getId());
        holder.manager.setText(GlobalValue.USERNAME);
        return view;
    }
    public class ViewHolder{
        ImageView imgView;
        FrameLayout frameLayout;
        Shop shop;
        TextView shopName,shopID,manager;
    }
}
