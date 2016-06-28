package tvcompany.salemanager.controller.login;

import android.os.StrictMode;

import API.ServiceGenerator;
import retrofit2.Call;
import tvcompany.salemanager.model.Status;

/**
 * Created by MtViet on 28/06/2016.
 */
public class UserController {
    public String GetUserID(String account)
    {
        Call<Status> status = ServiceGenerator.GetInstance().getUserID(account);
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Status str = status.execute().body();
            return str.getStatus();
        }
        catch (Exception ex){
            return "";
        }
    }
}
