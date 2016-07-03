package tvcompany.salemanager.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by Administrator on 01/07/2016.
 */
public class SaveFile {
    Context ct;
    public SaveFile(Context ctx){
        this.ct= ctx;
    }

    public void createDirectoryAndSaveFile(Context context, String fileName)
    {
        String he= fileName;
        try {
            Target target = new Target() {
                @Override
                public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            File file = new File(
                                    Environment.getExternalStorageDirectory().getPath()
                                            + "/lololololo.jpg");
                            try {
                                file.createNewFile();
                                FileOutputStream ostream = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                                ostream.close();

                                Toast.makeText(ct,"Luuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu",Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            };
            String s= GlobalValue.CONFIG + fileName;
            Picasso.with(context)
                    .load("http://192.168.0.16:3000/uploads/viet/d28d1d1de22c9f5c2b3fda51e6075554.jpg")
                    .into(target);
        }catch (Exception ex){

        }


    }
    public static void imageDownload(Context ctx, String url){
        Picasso.with(ctx)
                .load("http://blog.concretesolutions.com.br/wp-content/uploads/2015/04/Android1.png")
                .into(getTarget(url));
    }

    //target to save
    private static Target getTarget(final String url){
        String s=Environment.getExternalStorageDirectory().getPath() + "/" + url;
        Target target = new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/tung.jpg");
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }
    public String SaveImage(String urlImage) throws IOException {
        String result="";
        URL url = new URL(GlobalValue.CONFIG +urlImage);
        InputStream input = url.openStream();
        try {
            String s[]=urlImage.split("/");
            File direct = new File(Environment.getExternalStorageDirectory() + "/SaleManager");

            if (!direct.exists()) {
                File wallpaperDirectory = new File(GlobalValue.FILE_IMAGE + "SaleManager");
                wallpaperDirectory.mkdirs();
            }
            File direct2 = new File(Environment.getExternalStorageDirectory() + "/SaleManager/"+ s[0]);

            if (!direct2.exists()) {
                File wallpaperDirectory2 = new File(GlobalValue.FILE_IMAGE + "SaleManager/"+s[0]);
                wallpaperDirectory2.mkdirs();
            }
            result = GlobalValue.FILE_IMAGE + "SaleManager/"+s[0]+"/"+ s[1];
            File file = new File(new File(GlobalValue.FILE_IMAGE + "SaleManager/"+s[0]), s[1]);
            if (file.exists()) {
                file.delete();
            }

            OutputStream output = new FileOutputStream (file);
            try {
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                    output.write(buffer, 0, bytesRead);
                }
            } finally {
                output.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
        return result;
    }
}
