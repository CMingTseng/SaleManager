package tvcompany.salemanager.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.socket.client.On;
import tvcompany.salemanager.R;
import tvcompany.salemanager.controller.login.ShopController;
import tvcompany.salemanager.controller.login.UploadFileController;
import tvcompany.salemanager.controller.login.UserController;
import tvcompany.salemanager.database.DatabaseManager;
import tvcompany.salemanager.library.MD5;
import tvcompany.salemanager.library.SaveFile;
import tvcompany.salemanager.library.ValidString;
import tvcompany.salemanager.model.Contact;
import tvcompany.salemanager.model.Shop;
import tvcompany.salemanager.model.User;

/**
 * Created by Administrator on 16/06/2016.
 */
public class ProfileActivity extends Activity {

    public static final int PICK_IMAGE = 100;
    private Button btn_Save,btn_Change;
    private User user = null;
    private Bitmap bm = null;
    private EditText account,oldPass, pass, passAccess, fullName, email, phone;
    private ValidString valid;
    private UserController userController;
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;
    private TextView txtTilte;
    private DatabaseManager db;
    private boolean flag=true;
    Bitmap bitmapIcon;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        db= new DatabaseManager(ProfileActivity.this);
        txtTilte=(TextView) findViewById(R.id.txt_Title);
        txtTilte.setText("Thông tin tài khoản");
        imageView = (ImageView) findViewById(R.id.iconRegister);
        account = (EditText) findViewById(R.id.editAccount);
        //account.setEnabled(false);
        oldPass=(EditText) findViewById(R.id.edit_OldPass);
        oldPass.setVisibility(View.GONE);
        pass = (EditText) findViewById(R.id.editPass);
        pass.setVisibility(View.GONE);
        passAccess = (EditText) findViewById(R.id.editPassAccess);
        passAccess.setVisibility(View.GONE);
        btn_Change=(Button) findViewById(R.id.btnChange);
        btn_Change.setVisibility(View.VISIBLE);

        fullName = (EditText) findViewById(R.id.editFullName);
        email = (EditText) findViewById(R.id.editEmail);
        phone = (EditText) findViewById(R.id.editPhone);
        btn_Save = (Button) findViewById(R.id.btnRegister);
        btn_Save.setText("Lưu Thông Tin");
        valid = new ValidString();
        userController = new UserController();
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });
        //Contact ct= db.GetContact();
        final User user= db.GetContact();
        if(user!=null){
            Picasso.with(ProfileActivity.this).load(new File(db.imageLink(user.getUserName()))).into(imageView);
            account.setText(user.getUserName());
            account.setTextColor(Color.BLACK);
            email.setText(user.getEmail());
            fullName.setText(user.getFullName());
            phone.setText(user.getPhoneNumber());

        }
        btn_Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag) {
                    pass.setVisibility(View.VISIBLE);
                    passAccess.setVisibility(View.VISIBLE);
                    oldPass.setVisibility(View.VISIBLE);
                    flag=false;
                }
                else{
                    pass.setVisibility(View.GONE);
                    passAccess.setVisibility(View.GONE);
                    oldPass.setVisibility(View.GONE);
                    flag= true;
                }
            }
        });
        Button btnSave= (Button) findViewById(R.id.btnSave);
        if (!pass.getText().toString().equals(passAccess.getText().toString())) {
            Toast.makeText(ProfileActivity.this, "Xác nhận mật khẩu sai!", Toast.LENGTH_LONG).show();
        }
        else if(oldPass.getText().toString().equals(user.getPassWord())){
            Toast.makeText(ProfileActivity.this, "Mật khẩu cũ không đúng!", Toast.LENGTH_LONG).show();
        }
        else{
            user.setUserName(valid.ReplaceToValidString(account.getText().toString().trim()));
            if(!flag){
                user.setPassWord(pass.getText().toString().trim());
            }
            user.setFullName(fullName.getText().toString().trim());
            user.setNote("");
            user.setEmail(email.getText().toString().trim());
            user.setPhoneNumber(phone.getText().toString().trim());
            user.setActive(false);
            MD5 md5 = new MD5();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String datestr = dateFormat.format(date);
            try {
                if(bm == null)
                {
                    user.setImage("");
                }
                else
                {
                    String image = account.getText().toString() + "::" + md5.getMD5(account.getText().toString() + datestr) + ".jpg";
                    user.setImage(image);
                }

            } catch (Exception ex) {

            }
            user.setParent(account.getText().toString());
            user.setValid(true);

            if (userController.AddUser(user)) {
                // new ShopController().AddShop(//new Shop("",user.getUserName(),user.getUserName(),
                //         new Date().toString(),200,200,"",userController.GetUserID(user.getUserName()),"","",true));
                new ShopController().AddShop(new Shop());
                if (bm != null) {
                    new UploadFileController().uploadFile(bm, user.getImage());
                }
                Toast.makeText(ProfileActivity.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(ProfileActivity.this, "Tài khoản đã tồn tại. Vui lòng chọn tài khoản khác!", Toast.LENGTH_LONG).show();
                account.setSelection(account.getText().length() -1 );
            }
        }

//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//
//                    //user.setImage(user.getImage().replace("::","/"));
//                    //user.setImage(new SaveFile(ProfileActivity.this).SaveImage(user.getImage()));
//                    //db.InserProfile(user);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                //db.InserProfile(contact);
//            }
//        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//            Cursor cursor = getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//            bitmapIcon= BitmapFactory.decodeFile(picturePath);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
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
            BitmapFactory.Options bfOptions = new BitmapFactory.Options();
            bfOptions.inDither = false;
            bfOptions.inPurgeable = true;
            bfOptions.inInputShareable = true;
            bfOptions.inTempStorage = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileInputStream fs = null;
            try {
                fs = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                //TODO do something intelligent
                e.printStackTrace();
            }
            try {
                bm = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
                imageView.setImageBitmap(bm);
            } catch (Exception ex) {
            }

        }


    }


}
