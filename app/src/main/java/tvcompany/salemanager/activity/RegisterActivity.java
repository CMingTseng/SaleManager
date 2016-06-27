package tvcompany.salemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import API.ServiceAPI;
import API.ServiceGenerator;
import API.ServiceInterface;
import tvcompany.salemanager.R;
import tvcompany.salemanager.library.MD5;
import tvcompany.salemanager.model.Order;
import tvcompany.salemanager.model.User;

public class RegisterActivity extends Activity {
    public static final int PICK_IMAGE = 100;
    ServiceInterface service;
    private ImageView imageView;
    private Button btn_Save;
    private User user=null;
    private EditText account, pass, passAccess,fullName,email, phone;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        imageView=(ImageView) findViewById(R.id.iconRegister);
        account=(EditText)findViewById(R.id.editAccount);
        pass=(EditText)findViewById(R.id.editPass);
        passAccess=(EditText)findViewById(R.id.editPassAccess);
        fullName=(EditText)findViewById(R.id.editFullName);
        email=(EditText)findViewById(R.id.editEmail);
        phone=(EditText)findViewById(R.id.editPhone);
        btn_Save=(Button) findViewById(R.id.btnRegister);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(pass.getText().toString().equals(passAccess.getText().toString())){
                    user = new User();
                    user.setUserName(account.getText().toString());
                    user.setPassWord(account.getText().toString());
                    user.setFullName(fullName.getText().toString());
                    user.setNote("");
                    user.setEmail(email.getText().toString());
                    user.setPhoneNumber(phone.getText().toString());
                    MD5 md5= new MD5();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();

                    String datestr = dateFormat.format(date);
                    user.setCreateDate(datestr);
                    try{
                        String image= account.getText().toString()+ "::" +md5.getMD5(account.getText().toString()+datestr)+".jpg";
                        user.setImage(image);
                    }catch (Exception ex){

                    }
                    user.setParent(account.getText().toString());
                    user.setValid(true);
                    ServiceAPI serviceAPI= new ServiceAPI();
                    serviceAPI.AddUser(user);

                }


            }
        });
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
