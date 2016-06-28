package tvcompany.salemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

//import tvcompany.salemanager.API.ServiceAPI;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import API.ServiceAPI;
import API.ServiceGenerator;
import retrofit2.Call;
import tvcompany.salemanager.R;
import tvcompany.salemanager.controller.login.LoginController;
import tvcompany.salemanager.library.SharedConstant;
import tvcompany.salemanager.model.Status;
import tvcompany.salemanager.model.User;


public class LoginActivity extends AppCompatActivity {
    private LoginController loginController;
    private CheckBox remember_me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        final EditText txtName = (EditText) findViewById(R.id.editAccountLogin);
        final EditText txtPassword = (EditText) findViewById(R.id.editPassLogin);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        remember_me=(CheckBox) findViewById(R.id.remember_me);
        SharedPreferences sp = getSharedPreferences(
                SharedConstant.LOGIN_STORE, Context.MODE_PRIVATE);
        boolean logined = sp.getBoolean(SharedConstant.LOGINED_STAFF, false);
        String username = sp.getString(SharedConstant.LOGIN_USERNAME, null);
        String password = sp.getString(SharedConstant.LOGIN_PASSWORD, null);
        if (username != null && password != null) {
            txtName.setText(username);
            txtPassword.setText(password);
        } else {
            txtName.setText("");
            txtPassword.setText("");
        }

        loginController = new LoginController();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String userName = txtName.getText().toString().trim();
                    String password = txtPassword.getText().toString();
                    if (loginController.CheckLogin(userName, password)) {
                        Toast.makeText(LoginActivity.this, "OK", Toast.LENGTH_LONG).show();
                        SharedPreferences sp  = LoginActivity.this.getSharedPreferences(
                                SharedConstant.LOGIN_STORE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();

                        // Logined
                        editor.putBoolean(SharedConstant.LOGINED_STAFF, true);
                        editor.commit();

                        // Save passwork
                        if (remember_me.isChecked()) {
                            editor.putString(SharedConstant.LOGIN_USERNAME,
                                    userName);
                            editor.putString(SharedConstant.LOGIN_PASSWORD,
                                    password);
                            editor.commit();
                        } else {
                            editor.putString(SharedConstant.LOGIN_USERNAME, "");
                            editor.putString(SharedConstant.LOGIN_PASSWORD, "");
                            editor.commit();
                        }
                    }

                } catch (Exception e) {
                }

            }
        });

    }
}