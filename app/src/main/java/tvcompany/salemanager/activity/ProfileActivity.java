package tvcompany.salemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import tvcompany.salemanager.R;
import tvcompany.salemanager.database.DatabaseManager;
import tvcompany.salemanager.model.Contact;

/**
 * Created by Administrator on 16/06/2016.
 */
public class ProfileActivity extends Activity {


    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;
    private DatabaseManager db;
    Bitmap bitmapIcon;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        db= new DatabaseManager(ProfileActivity.this);
        imageView = (ImageView) findViewById(R.id.iconProfiles);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        final EditText f_Name=(EditText) findViewById(R.id.editName);
        final EditText f_Phone=(EditText) findViewById(R.id.editPhone);
        EditText f_Address=(EditText) findViewById(R.id.editAddress);
        //Contact ct= db.GetContact();
        Contact ct= new Contact();
        if(ct!=null){
            imageView.setImageBitmap(ct.getI_image());
            f_Name.setText(ct.getS_fullName());
            f_Phone.setText(ct.getS_Phone());
            f_Address.setText("TVCompany");
        }
        Button btnSave= (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lưu vào
                Contact contact= new Contact();
                contact.setS_name("gandalf");
                contact.setS_fullName(f_Name.getText().toString());
                contact.setS_Phone(f_Phone.getText().toString());
                contact.setI_image(bitmapIcon);
                //db.InserProfile(contact);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            bitmapIcon= BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }


}
