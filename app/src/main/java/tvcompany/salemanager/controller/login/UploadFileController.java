package tvcompany.salemanager.controller.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

import java.io.ByteArrayOutputStream;

import API.ServiceGenerator;
import API.ServiceInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFileController {

    private ServiceInterface service;

    public UploadFileController()
    {
        service = ServiceGenerator.GetInstance();
    }

    public void uploadFile(Bitmap bm, String fileName){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] data2= null;
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 50, bos);
            data2 = bos.toByteArray();
        }
        catch (Exception ex){

        }
        RequestBody reqFile = RequestBody.create(MediaType.parse("application/octet-stream"), data2);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", fileName, reqFile);
        retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(body);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) { }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public Bitmap getImage(String fileName) {
        retrofit2.Call<okhttp3.ResponseBody> call = service.getImage(fileName);
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            return BitmapFactory.decodeStream(call.execute().body().byteStream());
        }
        catch (Exception ex){

        }
        return  null;
    }

}
