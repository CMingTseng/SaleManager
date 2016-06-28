package tvcompany.salemanager.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import API.ServiceAPI;
import tvcompany.salemanager.R;
import tvcompany.salemanager.controller.login.UploadFileController;


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
                String s = "viet::c4261e05e1151b36f9e89aa0068f5e07.jpg";
                Bitmap bm= new UploadFileController().getImage(s.replace("::","/"));
                ImageView imageView=(ImageView) findViewById(R.id.imageViewId);
                imageView.setImageBitmap(bm);

            }
        });
    }


}
