package tvcompany.salemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.activity.ShopActivity;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.model.Contact;
import tvcompany.salemanager.model.Shop;


public class ListShopAdapter extends RecyclerView.Adapter<ListShopAdapter.MyViewHolder> {

    private Context context;
    boolean flag = false;
    private List<Shop> listShop;
    private ImageView imgView;
    private FrameLayout frameLayout;
    private TableRow tableRow;
    private Shop shop;
    private TextView shopName,shopID,manager;
    public class MyViewHolder extends RecyclerView.ViewHolder{

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
                    int position  =   getAdapterPosition();
                    shop = listShop.get(position);
                    Intent i= new Intent(context, ShopActivity.class);
                    i.putExtra("Shop",shop);
                    context.startActivity(i);
                }
            });

        }

    }
    public ListShopAdapter(Context context, List<Shop> listShop) {
        this.context = context;
        this.listShop = listShop;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_shop, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        shop = listShop.get(position);
        shopName.setText(shop.getShopName());
        shopID.setText(shop.getId());
        manager.setText(GlobalValue.USERNAME);
//        if (position % 2 == 0) {
//            // set ảnh nếu có
//            imgView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon));
//        } else {
//            imgView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.vietfuck));
//        }

    }

    @Override
    public int getItemCount() {
        return listShop.size();
    }

}