package tvcompany.salemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import API.ServiceAPI;
import API.ServiceGenerator;
import API.ServiceInterface;
import tvcompany.salemanager.R;

public class RegisterActivity extends Activity {
    public static final int PICK_IMAGE = 100;
    ServiceInterface service;
    private ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        imageView=(ImageView) findViewById(R.id.iconRegister);

        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {

//            ServiceAPI api= new ServiceAPI();
//            api.uploadFile(data,this);

            android.net.Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            android.database.Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null)
                return;

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            File file = new File(filePath);
            BitmapFactory.Options bfOptions=new BitmapFactory.Options();
            bfOptions.inDither=false;
            bfOptions.inPurgeable=true;
            bfOptions.inInputShareable=true;
            bfOptions.inTempStorage=new byte[32 * 1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileInputStream fs=null;
            try {
                fs = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                //TODO do something intelligent
                e.printStackTrace();
            }
            try {
                imageView.setImageBitmap(BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions));
            }
            catch (Exception ex){}
        }
    }
}
