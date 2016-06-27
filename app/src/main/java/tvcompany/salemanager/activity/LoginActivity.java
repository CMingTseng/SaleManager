package tvcompany.salemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import tvcompany.salemanager.API.ServiceAPI;
import java.util.List;

import API.ServiceAPI;
import tvcompany.salemanager.R;
import tvcompany.salemanager.model.User;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        final EditText txtName=(EditText) findViewById(R.id.editAccountLogin);
        final EditText txtPassword= (EditText) findViewById(R.id.editPassLogin);
        Button btnLogin= (Button) findViewById(R.id.sign_in_button);

        ServiceAPI api= new ServiceAPI();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from= txtName.getText().toString();
                String to= txtPassword.getText().toString();

            }
        });

    }
}