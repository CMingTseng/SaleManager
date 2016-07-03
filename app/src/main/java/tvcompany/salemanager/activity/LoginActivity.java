package tvcompany.salemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import tvcompany.salemanager.R;
import tvcompany.salemanager.controller.login.LoginController;
import tvcompany.salemanager.controller.login.UploadFileController;
import tvcompany.salemanager.controller.login.UserController;
import tvcompany.salemanager.database.DatabaseManager;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.library.SaveFile;
import tvcompany.salemanager.library.SharedConstant;
import tvcompany.salemanager.model.User;


public class LoginActivity extends AppCompatActivity {
    private LoginController loginController;
    private UserController userController;
    private UploadFileController uploadController;
    private DatabaseManager db;
    private SaveFile saveFile;
    private CheckBox remember_me;
    private TextView txtRegister;
    private TextView txtForget;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        final EditText txtName = (EditText) findViewById(R.id.editAccountLogin);
        final EditText txtPassword = (EditText) findViewById(R.id.editPassLogin);
         imageView=(ImageView) findViewById(R.id.iconLogin);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        remember_me = (CheckBox) findViewById(R.id.remember_me);
        SharedPreferences sp = getSharedPreferences(
                SharedConstant.LOGIN_STORE, Context.MODE_PRIVATE);
        boolean logined = sp.getBoolean(SharedConstant.LOGINED_STAFF, false);
        final String username = sp.getString(SharedConstant.LOGIN_USERNAME, null);
        String password = sp.getString(SharedConstant.LOGIN_PASSWORD, null);

        //---------------------------------
        db= new DatabaseManager(LoginActivity.this);
        loginController = new LoginController();
        userController= new UserController();
        uploadController= new UploadFileController();
        //-----------------------------------

        if (username != null && password != null) {
            txtName.setText(username);
            txtPassword.setText(password);
            try {
                String s= db.imageLink(username);
                Picasso.with(LoginActivity.this).load(new File(db.imageLink(username))).into(imageView);
            }
            catch (Exception e){
                String ex= e.toString();
            }
        } else {
            txtName.setText("");
            txtPassword.setText("");
        }

        //Textview to link
        txtRegister = (TextView)  findViewById(R.id.txtRegister);
        SpannableString content = new SpannableString("Đăng ký");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        txtRegister.setText(content);

        txtForget = (TextView)  findViewById(R.id.txtForget);
        SpannableString content2 = new SpannableString("Quên mật khẩu");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        txtForget.setText(content2);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(k);
            }
        });
        //End text

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String userName = txtName.getText().toString().trim();
                    String password = txtPassword.getText().toString();
                    String result =  loginController.CheckLogin(userName, password);
                    if (!result.equals("")) {
                        SharedPreferences sp = LoginActivity.this.getSharedPreferences(
                                SharedConstant.LOGIN_STORE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();

                        // Logined
                        editor.putBoolean(SharedConstant.LOGINED_STAFF, true);
                        editor.commit();
                        editor.putString(SharedConstant.LOGIN_USERNAME,
                                userName);
                        editor.putString(SharedConstant.LOGIN_PASSWORD,
                                password);
                        GlobalValue.USERNAME = userName;
                        GlobalValue.PASSWORD = password;
                        GlobalValue.ID = result;
                        // Save passwork
                        if (remember_me.isChecked()) {
                            editor.putBoolean(SharedConstant.KEEP_LOGIN, true);
                        } else {
                            editor.putBoolean(SharedConstant.KEEP_LOGIN, false);
                        }
                        editor.commit();
                        try {
                            //if(!txtName.getText().toString().equals(username)){
                                User user= userController.GetUser(userName);
                                user.setImage(user.getImage().replace("::","/"));
                                user.setImage(new SaveFile(LoginActivity.this).SaveImage(user.getImage()));
                                db.InserProfile(user);
                           // }
                            //Intent k = new Intent(LoginActivity.this, ShopActivity.class);
                            Intent k = new Intent(LoginActivity.this, ListShopActivity.class);
                            startActivity(k);
                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, "Không thể kết nối tới máy chủ", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Không thể kết nối tới máy chủ", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Không thể kết nối tới máy chủ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}