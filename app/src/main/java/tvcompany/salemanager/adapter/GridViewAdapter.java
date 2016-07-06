package tvcompany.salemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.model.Contact;

/**
 * Created by Administrator on 06/07/2016.
 */

public class GridViewAdapter extends ArrayAdapter<Contact> {

    public GridViewAdapter(Context context, int layoutResourceId, List<Contact> data) {
        super(context, layoutResourceId, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View curView = convertView;

        if (curView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            curView = inflater.inflate(R.layout.item_list_gird_layout, null);

        }
        ImageView imgView= (ImageView) curView.findViewById(R.id.iconProductGrid);
        if(position%2==0){
            // set ảnh nếu
            Picasso.with(getContext()).load(R.mipmap.icon).into(imgView);
        }
        else{
            Picasso.with(getContext()).load(R.mipmap.vietfuck).into(imgView);
        }
        return curView;
    }
}
