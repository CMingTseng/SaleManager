package tvcompany.salemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import API.ServiceAPI;
import API.ServiceGenerator;
import API.ServiceInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tvcompany.salemanager.R;
import tvcompany.salemanager.database.DatabaseManager;

/**
 * Created by Administrator on 26/06/2016.
 */
public class RegisterActivity extends Activity {
    public static final int PICK_IMAGE = 100;
    ServiceInterface service;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_file);
         service=  ServiceGenerator.createService(ServiceInterface.class);
        Button btn = (Button) findViewById(R.id.btn_upload);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {

            android.net.Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            android.database.Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null)
                return;

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            File file = new File(filePath);

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("/upload", file.getName(), reqFile);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

//            Log.d("THIS", data.getData().getPath());

            retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(body, name);
            req.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) { }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}
