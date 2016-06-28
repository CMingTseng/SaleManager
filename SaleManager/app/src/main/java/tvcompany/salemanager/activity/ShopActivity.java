package tvcompany.salemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import tvcompany.salemanager.R;
import tvcompany.salemanager.controller.login.ShopController;
import tvcompany.salemanager.controller.login.UploadFileController;
import tvcompany.salemanager.controller.login.UserController;
import tvcompany.salemanager.library.GlobalValue;
import tvcompany.salemanager.library.MD5;
import tvcompany.salemanager.library.ValidString;
import tvcompany.salemanager.model.Shop;

public class ShopActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 100;
    private ImageView imageView;
    private Button btn_Save;
    private Shop shop = null;
    private Bitmap bm = null;
    private EditText shopID, shopName, shopAddress, shopNote;
    private ValidString valid;
    ShopController shopController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newshop_layout);

        imageView = (ImageView) findViewById(R.id.iconNewShop);
        shopID = (EditText) findViewById(R.id.shopId);
        shopName = (EditText) findViewById(R.id.shopName);
        shopAddress = (EditText) findViewById(R.id.shopAddress);
        shopNote = (EditText) findViewById(R.id.shopNote);
        btn_Save = (Button) findViewById(R.id.btnSaveShop);
        valid = new ValidString();
        shopController = new ShopController();
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!valid.CheckValidLengthRegex(shopID.getText().toString()))
                {
                    Toast.makeText(ShopActivity.this,  "Mã cửa hàng phải chứa ít nhất 6 ký tự và không có dấu", Toast.LENGTH_LONG).show();
                }
                else
                if(!valid.CheckSpecialCharacter(shopID.getText().toString()))
                {
                    Toast.makeText(ShopActivity.this,  "Mã cửa hàng không được chưa ký tự đặc biệt", Toast.LENGTH_LONG).show();
                }
                if(!valid.CheckValidLength(shopName.getText().toString()))
                {
                    Toast.makeText(ShopActivity.this,  "Tên cửa hàng phải chứa ít nhất 6 ký tự", Toast.LENGTH_LONG).show();
                }
                else
                {
                    shop = new Shop();
                    shop.setId(valid.ReplaceToValidString(shopID.getText().toString().trim()));
                    shop.setShopName(shopName.getText().toString().trim());
                    shop.setAddress(shopAddress.getText().toString().trim());
                    shop.setNote(shopNote.getText().toString().trim());
                    shop.setValid(true);
                    shop.setManager("");
                    shop.setLongitude(200);
                    shop.setLatitude(200);
                    shop.setManager(new UserController().GetUserID(GlobalValue.USERNAME));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String datestr = dateFormat.format(date);
                    shop.setCreateDate(datestr);
                    try {
                        if(bm == null)
                        {
                            shop.setImage("");
                        }
                        else
                        {
                            MD5 md5 = new MD5();
                            String image = GlobalValue.USERNAME + "::" + md5.getMD5(shop.getId()  + datestr) + ".jpg";
                            shop.setImage(image);
                        }

                    } catch (Exception ex) {

                    }
                    if (shopController.AddShop(shop)) {
                        if (bm != null) {
                            new UploadFileController().uploadFile(bm,shop.getImage());
                        }
                        Toast.makeText(ShopActivity.this, "Đăng ký cửa hàng thành công!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(ShopActivity.this, "Cửa hàng đã tồn tại. Vui lòng nhập lại mã cửa hàng!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
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
            BitmapFactory.Options bfOptions = new BitmapFactory.Options();
            bfOptions.inDither = false;
            bfOptions.inPurgeable = true;
            bfOptions.inInputShareable = true;
            bfOptions.inTempStorage = new byte[32 * 1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileInputStream fs = null;
            try {
                fs = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                //TODO do something intelligent
                e.printStackTrace();
            }
            try {
                bm = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
                imageView.setImageBitmap(bm);
            } catch (Exception ex) {
            }
        }
    }
}
