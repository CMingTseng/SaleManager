package tvcompany.salemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tvcompany.salemanager.R;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText txtFrom=(EditText) findViewById(R.id.txtFrom);
        final EditText txtTo= (EditText) findViewById(R.id.txtTo);
        Button btnLogin= (Button) findViewById(R.id.sign_in_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from= txtFrom.getText().toString();
                String to= txtTo.getText().toString();
                Intent intent= new Intent(LoginActivity.this, ChatActivity.class);
                intent.putExtra("FROM", from);
                intent.putExtra("TO",to);
                LoginActivity.this.startActivity(intent);
            }
        });

    }
}