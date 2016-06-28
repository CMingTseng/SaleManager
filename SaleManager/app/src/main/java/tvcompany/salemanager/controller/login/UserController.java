package tvcompany.salemanager.controller.login;

import android.os.StrictMode;

import API.ServiceGenerator;
import API.ServiceInterface;
import retrofit2.Call;
import tvcompany.salemanager.model.Status;
import tvcompany.salemanager.model.User;

public class UserController {

    private ServiceInterface service;

    public UserController() {
        service = ServiceGenerator.GetInstance();
    }

    public String GetUserID(String account) {
        Call<Status> status = service.getUserID(account);
        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Status str = status.execute().body();
            return str.getStatus();
        } catch (Exception ex) {
            return "";
        }
    }

    public boolean AddUser(User user) {
        try {
            Call<Status> call = service.addUser(user);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Status status = call.execute().body();
            if (status.getStatus().equals("Success")) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
}
