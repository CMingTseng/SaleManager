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
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

//            Log.d("THIS", data.getData().getPath());

        retrofit2.Call<okhttp3.ResponseBody> req = git.postImage(body, name);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) { }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public Bitmap getRetrofitImage(Context ct) {
        Call<ResponseBody> call = git.getImageDetails();
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            return  DownloadImage(call.execute().body(),ct);

        }
        catch (Exception ex){
            result= ex.toString();
        }
        return  null;
    }
    private Bitmap DownloadImage(ResponseBody body,Context ct) {

        try {
            Log.d("DownloadImage", "Reading and writing file");
            InputStream in = null;
            FileOutputStream out = null;

            try {
                in = body.byteStream();
                out = new FileOutputStream(ct.getExternalFilesDir(null) + File.separator + "AndroidTutorialPoint.jpg");
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            }
            catch (IOException e) {
                Log.d("DownloadImage",e.toString());

            }
            finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }

            int width, height;

            Bitmap bMap = BitmapFactory.decodeFile(ct.getExternalFilesDir(null) + File.separator + "AndroidTutorialPoint.jpg");
            width = 2*bMap.getWidth();
            height = 6*bMap.getHeight();
            Bitmap bMap2 = Bitmap.createScaledBitmap(bMap, width, height, false);

            return bMap2;

        } catch (IOException e) {
            Log.d("DownloadImage",e.toString());
            return null;
        }
    }

}
