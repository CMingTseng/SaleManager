package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.activity.ShopActivity;
import tvcompany.salemanager.model.Contact;
import tvcompany.salemanager.model.Shop;

/**
 * Created by MtViet on 29/06/2016.
 */
public class ListShopAdapter extends BaseAdapter {
    private Context context;
    boolean flag=true;
    private List<Shop> contactItems;
    public ListShopAdapter(Context context, List<Shop> navDrawerItems) {
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

        final Shop shop = contactItems.get(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.item_list_shop,
                null);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.iconshop);
        FrameLayout fr= (FrameLayout) convertView.findViewById(R.id.editShop);

        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xử lý xem chi tiets của hàng ở đây
                Intent i= new Intent(context, ShopActivity.class);
                i.putExtra("Shop",shop);
                context.startActivity(i);
            }
        });
        if(position%2==0){
            // set ảnh nếu có
           // imgView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon));
        }
        else{
           // imgView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.vietfuck));
        }
        return convertView;
    }
}
