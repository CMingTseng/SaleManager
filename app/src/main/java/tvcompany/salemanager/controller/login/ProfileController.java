package tvcompany.salemanager.controller.login;

import android.os.StrictMode;

import API.ServiceGenerator;
import retrofit2.Call;
import tvcompany.salemanager.model.Status;

/**
 * Created by Administrator on 01/07/2016.
 */
public class ProfileController {
    public String GetUser(String userName,String password)
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
