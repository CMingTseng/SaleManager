package tvcompany.salemanager.activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import API.ServiceGenerator;
import API.ServiceInterface;
import retrofit2.Call;
import tvcompany.salemanager.R;
import tvcompany.salemanager.model.Book;
import tvcompany.salemanager.model.Status;
import tvcompany.salemanager.model.Status1;
import tvcompany.salemanager.model.Status2;
import tvcompany.salemanager.model.Status3;
import tvcompany.salemanager.model.User1;

public class InitChatActivity extends AppCompatActivity {
    private ServiceInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        service = ServiceGenerator.GetInstance();
        final EditText txtFrom = (EditText) findViewById(R.id.txtFrom);
        txtFrom.setText("ad7c097a288a94fb1094ae3376e2be9d");
        final EditText txtTo = (EditText) findViewById(R.id.txtTo);
        Button btn = (Button) findViewById(R.id.sign_in_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sign up

//                User1 user = new User1("1112","22",txtFrom.getText().toString(),"42",new SimpleDateFormat(
//                        "yyyy.MM.dd HH:mm:ss").format(new java.util.Date()),"5","6");
//                Call<Status1> status = service.addUser1(user,"tenten");
//                try{
//                    if (android.os.Build.VERSION.SDK_INT > 9) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                    }
//                    Status1 str = status.execute().body();
//                    String s = "";
//                    Toast.makeText(InitChatActivity.this, str.getDescription(), Toast.LENGTH_LONG).show();
//                }
//                catch (Exception ex){
//                    String s = "";
//                }

                //login

//                Call<Status1> status1 = service.login(txtFrom.getText().toString(),txtTo.getText().toString(),"iphone");
//                try{
//                    if (android.os.Build.VERSION.SDK_INT > 9) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                    }
//                    Status1 str = status1.execute().body();
//                    String s = "";
//                    Toast.makeText(InitChatActivity.this, str.getSession_id(), Toast.LENGTH_LONG).show();
//                }
//                catch (Exception ex){
//                    String s = "";
//                }

                //get profile

//                Call<Status2> status1 = service.getprofile("dbe1609b87ef519a9a219042679ae50c");
//                try {
//                    if (android.os.Build.VERSION.SDK_INT > 9) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                    }
//                    Status2 str = status1.execute().body();
//                    String s = "";
//                    Toast.makeText(InitChatActivity.this, "", Toast.LENGTH_LONG).show();
//                } catch (Exception ex) {
//                    String s = "";
//                }

                //update profile
//                User1 user = new User1("1112", "22", txtFrom.getText().toString(), "42", new SimpleDateFormat(
//                        "yyyy.MM.dd HH:mm:ss").format(new java.util.Date()), "5", "6");
//                Call<Status1> status = service.updateprofile(user, "ad7jc097a288a94fb1094ae3376e2be9d");
//                try {
//                    if (android.os.Build.VERSION.SDK_INT > 9) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                    }
//                    Status1 str = status.execute().body();
//                    String s = "";
//                    Toast.makeText(InitChatActivity.this, str.getDescription(), Toast.LENGTH_LONG).show();
//                } catch (Exception ex) {
//                    String s = "";
//                }
                //change password
//                Call<Status1> status = service.changepassword("ad7c097a288a94fb1094ae3376e2be9d",txtFrom.getText().toString(),
//                        txtTo.getText().toString());
//                try {
//                    if (android.os.Build.VERSION.SDK_INT > 9) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                    }
//                    Status1 str = status.execute().body();
//                    String s = "";
//                    Toast.makeText(InitChatActivity.this, str.getDescription(), Toast.LENGTH_LONG).show();
//                } catch (Exception ex) {
//                    String s = "";
//                }
                //lougout

//                Call<Status1> status = service.logout("ad7c097a288a94fb1094ae3376e2be9d");
//                try {
//                    if (android.os.Build.VERSION.SDK_INT > 9) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                    }
//                    Status1 str = status.execute().body();
//                    String s = "";
//                    Toast.makeText(InitChatActivity.this, str.getDescription(), Toast.LENGTH_LONG).show();
//                } catch (Exception ex) {
//                    String s = "";
//                }

//                Call<Status1> status = service.forgotpassword("viet.ptit.17@gmail.com");
//                try {
//                    if (android.os.Build.VERSION.SDK_INT > 9) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                    }
//                    Status1 str = status.execute().body();
//                    String s = "";
//                    Toast.makeText(InitChatActivity.this, str.getDescription(), Toast.LENGTH_LONG).show();
//                } catch (Exception ex) {
//                    String s = "";
//                }

                //add book
//                Book book = new Book("33","44","55","66",77,88,"99","11","22");
//                Call<Status1> status = service.addbook(book,"dbe1609b87ef519a9a219042679ae50c");
//                try {
//                    if (android.os.Build.VERSION.SDK_INT > 9) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                    }
//                    Status1 str = status.execute().body();
//                    String s = "";
//                    Toast.makeText(InitChatActivity.this, str.getDescription(), Toast.LENGTH_LONG).show();
//                } catch (Exception ex) {
//                    String s = "";
//                }

//                Call<Status3> status1 = service.getinfo("dbe1609b87ef519a9a219042679ae50c", "4d1008888b6a48baffbdce6d6a3de594");
//                try {
//                    if (android.os.Build.VERSION.SDK_INT > 9) {
//                        StrictMode.ThreadPolicy policy =
//                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                    }
//                    Status3 str = status1.execute().body();
//                    String s = "";
//                    Toast.makeText(InitChatActivity.this, str.getCode()+"", Toast.LENGTH_LONG).show();
//                } catch (Exception ex) {
//                    String s = "";
//                }

                Call<Status3> status1 = service.getAllBook("dbe1609b87ef519a9a219042679ae50c");
                try {
                    if (android.os.Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy =
                                new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                    }
                    Status3 str = status1.execute().body();
                    String s = "";
                    Toast.makeText(InitChatActivity.this, str.getCode()+"", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    String s = "";
                }

            }
        });
    }
}
