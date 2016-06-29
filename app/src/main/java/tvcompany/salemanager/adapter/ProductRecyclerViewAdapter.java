package tvcompany.salemanager.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.model.Contact;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    boolean flag = false;
    private List<Contact> contactItems;
    private ImageView imgView;
    private FrameLayout frameLayout;
    private TableRow tableRow;
    private Button btn;
    private EditText editNumberOrder;
    private TextView txtNumberOrder;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, year, genre;

        public MyViewHolder(final View view) {
            super(view);
            // khai bao thuộc trên layout item ở đây
            imgView = (ImageView) view.findViewById(R.id.iconProductList);
            btn= (Button) view.findViewById(R.id.button);
            frameLayout = (FrameLayout) view.findViewById(R.id.cart);

            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tableRow = (TableRow) view.findViewById(R.id.orderDetail);
                    if (flag) {
                        tableRow.setVisibility(View.VISIBLE);
                        flag = false;
                    } else {
                        tableRow.setVisibility(View.GONE);
                        flag = true;
                    }
                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tableRow = (TableRow) view.findViewById(R.id.orderDetail);
                    editNumberOrder=(EditText) view.findViewById(R.id.editNumberOrder);
                    txtNumberOrder=(TextView) view.findViewById(R.id.counter_value);
                    if(!editNumberOrder.getText().toString().equals("")){
                        tableRow.setVisibility(View.GONE);
                        flag= true;
                        txtNumberOrder.setText(editNumberOrder.getText().toString());
                    }
                    else{
                        Toast.makeText(context,"Bạn cần nhập số lượng đặt hàng",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }
    public ProductRecyclerViewAdapter(Context context, List<Contact> listContact) {
        this.context = context;
        this.contactItems = listContact;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (position % 2 == 0) {
            // set ảnh nếu có
            imgView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon));
        } else {
            imgView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.vietfuck));
        }

    }

    @Override
    public int getItemCount() {
        return contactItems.size();
    }

}