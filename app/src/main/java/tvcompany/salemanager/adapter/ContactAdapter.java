package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.model.Contact;

/**
 * Created by Administrator on 15/06/2016.
 */

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private List<Contact> contactItems;
    public ContactAdapter(Context context, List<Contact> navDrawerItems) {


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
        convertView = mInflater.inflate(R.layout.list_row_contact,
                    null);

        ImageView imgView = (ImageView) convertView.findViewById(R.id.iconView);
        // set ảnh nếu có
        imgView.setImageBitmap(m.getI_image());

        TextView name = (TextView) convertView.findViewById(R.id.txtName);
        TextView fullName = (TextView) convertView.findViewById(R.id.txtFullName);
        name.setText(m.getS_name());
        fullName.setText(m.getS_fullName());
        return convertView;
    }
}