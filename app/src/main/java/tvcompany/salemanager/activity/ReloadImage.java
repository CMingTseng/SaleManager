package tvcompany.salemanager.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import API.ServiceAPI;
import API.ServiceInterface;
import okhttp3.ResponseBody;
import tvcompany.salemanager.R;


/**
 * Created by Administrator on 27/06/2016.
 */
public class ReloadImage extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_file);

        Button btn= (Button)findViewById(R.id.RetrofitImage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceAPI api= new ServiceAPI();
                Bitmap bm= api.getRetrofitImage(ReloadImage.this);
                //ImageView imageView=(ImageView) findViewById(R.id.imageViewId);
                //imageView.setImageBitmap(bm);

            }
        });
    }


}
