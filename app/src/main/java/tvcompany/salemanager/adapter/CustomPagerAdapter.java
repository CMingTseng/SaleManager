package tvcompany.salemanager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import tvcompany.salemanager.R;

/**
 * Created by Administrator on 05/07/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {
    private String[] imageIDs = {
            "http://192.168.0.56:3000/uploads/Mtviet1/Screenshot_2016-05-28-07-39-50.png",
            "http://192.168.0.56:3000/uploads/viet/393d41c5b7310a1ad4a1e9f47a8a09ef.jpg",
            "http://192.168.0.56:3000/uploads/Mtviet1/Screenshot_2016-05-28-07-39-50.png",
            "http://192.168.0.56:3000/uploads/viet/393d41c5b7310a1ad4a1e9f47a8a09ef.jpg",
            "http://192.168.0.56:3000/uploads/Mtviet1/Screenshot_2016-05-28-07-39-50.png",
            "http://192.168.0.56:3000/uploads/viet/393d41c5b7310a1ad4a1e9f47a8a09ef.jpg"
    };
    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return imageIDs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        try {
            Picasso.with(mContext)
                    .load(imageIDs[position])
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}