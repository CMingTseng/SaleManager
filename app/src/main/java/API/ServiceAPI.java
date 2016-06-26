package API;

import android.os.StrictMode;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tvcompany.salemanager.model.User;

/**
 * Created by Administrator on 25/06/2016.
 */
public class ServiceAPI {
    private ServiceInterface git;
    private String result="";

    public ServiceAPI() {
        this.git =  ServiceGenerator.createService(ServiceInterface.class);

    }

    public String getUser() {
        //binding.username.getText().toString()
        Call<List<User>> call = git.getUser();
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            List<User> user = call.execute().body();
            result="Nhận :Size: "+ user.size();
        }
        catch (Exception ex){
            result= ex.toString();
        }
        return result;
    }

    public void uploadFile(){}

}
