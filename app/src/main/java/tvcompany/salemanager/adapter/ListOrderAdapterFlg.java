package tvcompany.salemanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import tvcompany.salemanager.R;
import tvcompany.salemanager.activity.ProductActivity;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.model.Product;


public class ListOrderAdapterFlg extends BaseAdapter {
    private List<Product> list;
    private Fragment context;
    private LayoutInflater inflater;
    private int count;
    private int stepNumber;
    private int startCount;
    private Product product;
    private ViewHolder holder;
    private TableRow tableRow;

    private EditText editNumberOrder;
    private TextView txtNumberOrder;
    private Button btn;
    public ListOrderAdapterFlg(List<Product> list, Fragment context, int stepNumber, int startCount, LayoutInflater inflater) {
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
        convertView = inflater.inflate(R.layout.item_list_order, parent, false);
        product = list.get(position);
        holder = new ViewHolder();

        holder.productName = (TextView) convertView.findViewById(R.id.productDetailName);
        holder.productID = (TextView) convertView.findViewById(R.id.productDetailID);
        holder.productPrice = (TextView) convertView.findViewById(R.id.productPrice);
        holder.frameLayout = (FrameLayout) convertView.findViewById(R.id.order);
        final View finalConvertView = convertView;
        final boolean[] flag = {true};
        final TableRow tableRows = (TableRow) finalConvertView.findViewById(R.id.orderDetails);
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag[0]) {
                    tableRows.setVisibility(View.VISIBLE);
                    flag[0] = false;
                } else {
                    tableRows.setVisibility(View.GONE);
                    flag[0] = true;
                }
            }
        });
        btn= (Button) convertView.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNumberOrder=(EditText) finalConvertView.findViewById(R.id.editNumberOrders);
                //txtNumberOrder=(TextView) finalConvertView.findViewById(R.id.counter_values);
                if(!editNumberOrder.getText().toString().equals("")){
                    tableRows.setVisibility(View.GONE);
                    flag[0] = true;
                   // txtNumberOrder.setText(editNumberOrder.getText().toString());
                }
                else{
                    Toast.makeText(context.getActivity(),"Bạn cần nhập số lượng đặt hàng",Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.imgView = (ImageView) finalConvertView.findViewById(R.id.iconshop);
        try {
            Picasso.with(context.getActivity()).load(GlobalValue.CONFIG + product.getImage().replace("::","/")).into(holder.imgView);
        }
        catch (Exception e){}
        holder.productName.setText(product.getProductName());
        holder.productID.setText("ID: "+product.getID());
        holder.productPrice.setText("100,000 VNĐ");
        return finalConvertView;
    }
    public class ViewHolder{
        ImageView imgView;
        FrameLayout frameLayout;
        TextView productName,productID,productPrice;
    }
}
