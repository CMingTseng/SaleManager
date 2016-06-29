package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private int count;
    private int stepNumber;
    private int startCount;

    public ListOderAdapter(ArrayList<Group> list, Activity context, int stepNumber, int startCount) {
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
        View view = inflater.inflate(R.layout.listodercustom, parent, false);
        Group group = (Group) list.get(position);
        ViewHolder holder = new ViewHolder();
        Display display = context.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        holder.tv = (TextView) view.findViewById(R.id.tv_number_image);
        holder.imageView = (ImageView) view.findViewById(R.id.image_oder);
        Picasso.with(context).load(group.getText1()).resize(width,holder.imageView.getHeight()).into(holder.imageView);
        holder.tv.setText(group.getText2());
        return view;
    }
    public class ViewHolder{
        ImageView imageView;
        TextView tv;
    }
}
