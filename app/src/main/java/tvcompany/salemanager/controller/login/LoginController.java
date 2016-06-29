package tvcompany.salemanager.controller.login;

import android.os.StrictMode;

import java.util.List;

import API.ServiceGenerator;
import retrofit2.Call;
import tvcompany.salemanager.model.Status;
import tvcompany.salemanager.model.User;

/**
 * Created by MtViet on 27/06/2016.
 */
public class LoginController {
    public String CheckLogin(String userName,String password)
    {
        Call<Status> status = ServiceGenerator.GetInstance().CheckLogin(userName,password);
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Status str = status.execute().body();
            if(!str.getStatus().equals(""))
            {
                return str.getStatus();
            }
            return "";
        }
        catch (Exception ex){
            return "";
        }
    }
}
