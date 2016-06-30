package tvcompany.salemanager.controller.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
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

    public File saveBitmapToFile(File file){
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE=75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }

}
