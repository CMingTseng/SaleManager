package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.model.Contact;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Contact> contactItems;
    public ProductAdapter(Context context, List<Contact> navDrawerItems) {
        this.context = context;
        this.contactItems = navDrawerItems;
    }
    @Override
    public int getCount() {
        return contactItems.size();
    }
    @Override
    public Object getItem(int position) {
        return contactItems.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contact m = contactItems.get(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.item_list_layout,
                null);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.iconProductList);
        if(position%2==0){
            // set ảnh nếu có
            imgView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon));
        }
        else{
            imgView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.vietfuck));
        }
        return convertView;
    }
}
