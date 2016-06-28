package API;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tvcompany.salemanager.activity.RegisterActivity;
import tvcompany.salemanager.model.Imagessss;
import tvcompany.salemanager.model.Order;
import tvcompany.salemanager.model.Status;
import tvcompany.salemanager.model.User;

/**
 * Created by Administrator on 25/06/2016.
 */
public class ServiceAPI {
    private ServiceInterface git;
    private String result="";
    private  Bitmap bmGet=null;
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

    // thêm mới user
    public String AddUser(User user) {

        Call<Status> call =  git.addUser(user);
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
           Status status = call.execute().body();
            result=status.getStatus();


        }
        catch (Exception ex){
            result= ex.toString();
        }
        return result;
    }
    // up ảnh lên server
    public void uploadFile(Bitmap bm,String fileName){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] data2= null;
        try {
            bm.compress(Bitmap.CompressFormat.JPEG, 50, bos);
            data2 = bos.toByteArray();
        }
        catch (Exception ex){

        }
        RequestBody reqFile = RequestBody.create(MediaType.parse("application/octet-stream"), data2);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", fileName, reqFile);
        retrofit2.Call<okhttp3.ResponseBody> req = git.postImage(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) { }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    // get ảnh từ server về
//    public Bitmap getRetrofitImage(Context ct) {
//        retrofit2.Call<okhttp3.ResponseBody> call = git.getImageDetails();
//        try{
//            if (android.os.Build.VERSION.SDK_INT > 9) {
//                StrictMode.ThreadPolicy policy =
//                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(policy);
//            }
//            return BitmapFactory.decodeStream(call.execute().body().byteStream());
//        }
//        catch (Exception ex){
//
//        }
//        return  null;
//    }

}
