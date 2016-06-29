package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tvcompany.salemanager.R;
import tvcompany.salemanager.model.Group;

/**
 * Created by Administrator on 6/29/2016.
 */
public class ListOderAdapter extends BaseAdapter {
    ArrayList<Group> list;
    Activity context;

    public ListOderAdapter(ArrayList<Group> list, Activity context) {
        this.list = list;
        this.context = context;
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
        View view = inflater.inflate(R.layout.listodercustom, parent, false);
        Group group = (Group) list.get(position);
        ViewHolder holder = new ViewHolder();
        holder.imageView = (ImageView) view.findViewById(R.id.image_oder);
        Picasso.with(context).load(group.getText1()).into(holder.imageView);
        return view;
    }
    public class ViewHolder{
        ImageView imageView;
    }
}
