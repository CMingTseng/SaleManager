package tvcompany.salemanager.library;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import tvcompany.salemanager.controller.login.UploadFileController;

/**
 * Created by MtViet on 01/07/2016.
 */
public class ImageHandle {
    public static final int PICK_IMAGE = 100;
    public Bitmap handleImage(int requestCode, int resultCode, Intent data, AppCompatActivity activity,ImageView imageView)
    {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            android.net.Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            android.database.Cursor cursor = activity.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null)
                return null;

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            File file = new File(filePath);
            BitmapFactory.Options bfOptions = new BitmapFactory.Options();
            bfOptions.inDither = false;
            bfOptions.inPurgeable = true;
            bfOptions.inInputShareable = true;
            bfOptions.inTempStorage = new byte[32 * 1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileInputStream fs = null;
            try {
                fs = new FileInputStream(new UploadFileController().saveBitmapToFile(file));

                //imageView.setImageBitmap(bm);
                Picasso.with(activity).load(file).into(imageView);
                return BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
            } catch (Exception e) {
                //TODO do something intelligent
                e.printStackTrace();
            }
        }
        return null;
    }

}
