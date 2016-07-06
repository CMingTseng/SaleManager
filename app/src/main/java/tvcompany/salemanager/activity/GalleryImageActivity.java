package tvcompany.salemanager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.CustomPagerAdapter;


/**
 * Created by Administrator on 05/07/2016.
 */
public class GalleryImageActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_show);

        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        RatingBar rate=(RatingBar) findViewById(R.id.ratingBar);
        TextView txtInStock= (TextView) findViewById(R.id.txt_InStock) ;
        SpannableString content2 = new SpannableString("Còn Hàng");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        txtInStock.setText(content2);

        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        indicator.setStrokeWidth(2 * density);

    }

}