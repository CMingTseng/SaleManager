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
    //private MainViewModel viewModel;
    private String result="";

    public ServiceAPI() {
        //this.viewModel = viewModel;
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
            result="Nhận :"+ user.get(0).get_id()+" Size: "+ user.size();
        }
        catch (Exception ex){
            result= ex.toString();
        }


//        Call call = git.getUser();
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Response<List<User>> response) {
//                List<User> model = response.body();
//
//                if (model == null) {
//                    //404 or the response cannot be converted to User.
//                    ResponseBody responseBody = response.errorBody();
//                    if (responseBody != null) {
//                        try {
//                           result="responseBody = " + responseBody.string();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        result= "responseBody  = null";
//                    }
//                } else {
//                    //200
//                    result="Nhận :"+ model.get(0).get_id()+" Size: "+ model.size();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                result= "t = " + t.getMessage();
//                //viewModel.setPb(false);
//            }
//        });
        return result;
    }

}
