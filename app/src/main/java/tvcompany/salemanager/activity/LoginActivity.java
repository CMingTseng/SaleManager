package tvcompany.salemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import tvcompany.salemanager.API.ServiceAPI;
import java.util.List;

import API.ServiceAPI;
import API.ServiceGenerator;
import retrofit2.Call;
import tvcompany.salemanager.R;
import tvcompany.salemanager.controller.login.LoginController;
import tvcompany.salemanager.model.Status;
import tvcompany.salemanager.model.User;


public class LoginActivity extends AppCompatActivity {
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        final EditText txtName = (EditText) findViewById(R.id.editAccountLogin);
        final EditText txtPassword = (EditText) findViewById(R.id.editPassLogin);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        loginController = new LoginController();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String userName = txtName.getText().toString();
                    String password = txtPassword.getText().toString();
                    if(loginController.CheckLogin(userName,password))
                    {
                        Toast.makeText(LoginActivity.this,"OK",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                }

            }
        });

    }
}