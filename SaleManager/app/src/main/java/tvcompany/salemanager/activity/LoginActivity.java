package tvcompany.salemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import tvcompany.salemanager.R;
import tvcompany.salemanager.controller.login.LoginController;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.library.SharedConstant;



public class LoginActivity extends AppCompatActivity {
    private LoginController loginController;
    private CheckBox remember_me;
    private TextView txtRegister;
    private TextView txtForget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        final EditText txtName = (EditText) findViewById(R.id.editAccountLogin);
        final EditText txtPassword = (EditText) findViewById(R.id.editPassLogin);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        remember_me = (CheckBox) findViewById(R.id.remember_me);
        SharedPreferences sp = getSharedPreferences(
                SharedConstant.LOGIN_STORE, Context.MODE_PRIVATE);
        boolean logined = sp.getBoolean(SharedConstant.LOGINED_STAFF, false);
        final String username = sp.getString(SharedConstant.LOGIN_USERNAME, null);
        String password = sp.getString(SharedConstant.LOGIN_PASSWORD, null);
        if (username != null && password != null) {
            txtName.setText(username);
            txtPassword.setText(password);
        } else {
            txtName.setText("");
            txtPassword.setText("");
        }

        loginController = new LoginController();

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
                    if (loginController.CheckLogin(userName, password)) {
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
                        GlobalValue.USERNAME = username;
                        GlobalValue.PASSWORD = password;
                        // Save passwork
                        if (remember_me.isChecked()) {
                            editor.putBoolean(SharedConstant.KEEP_LOGIN, true);
                        } else {
                            editor.putBoolean(SharedConstant.KEEP_LOGIN, false);
                        }
                        editor.commit();
                        try {
                            Intent k = new Intent(LoginActivity.this, ShopActivity.class);
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