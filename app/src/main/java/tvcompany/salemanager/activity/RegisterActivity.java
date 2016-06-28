package tvcompany.salemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
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

import API.ServiceAPI;
import API.ServiceGenerator;
import API.ServiceInterface;
import tvcompany.salemanager.R;
import tvcompany.salemanager.controller.login.ShopController;
import tvcompany.salemanager.controller.login.UploadFileController;
import tvcompany.salemanager.controller.login.UserController;
import tvcompany.salemanager.library.MD5;
import tvcompany.salemanager.library.ValidString;
import tvcompany.salemanager.model.Order;
import tvcompany.salemanager.model.Shop;
import tvcompany.salemanager.model.User;

public class RegisterActivity extends Activity {
    public static final int PICK_IMAGE = 100;
    private ImageView imageView;
    private Button btn_Save;
    private User user = null;
    private Bitmap bm = null;
    private EditText account, pass, passAccess, fullName, email, phone;
    private ValidString valid;
    private UserController userController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        imageView = (ImageView) findViewById(R.id.iconRegister);
        account = (EditText) findViewById(R.id.editAccount);
        pass = (EditText) findViewById(R.id.editPass);
        passAccess = (EditText) findViewById(R.id.editPassAccess);
        fullName = (EditText) findViewById(R.id.editFullName);
        email = (EditText) findViewById(R.id.editEmail);
        phone = (EditText) findViewById(R.id.editPhone);
        btn_Save = (Button) findViewById(R.id.btnRegister);
        valid = new ValidString();
        userController = new UserController();
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!valid.CheckValidLengthRegex(account.getText().toString()))
                {
                    Toast.makeText(RegisterActivity.this,  "Tên tài khoản phải chứa ít nhất 6 ký tự và không có dấu", Toast.LENGTH_LONG).show();
                }
                else
                if(!valid.CheckSpecialCharacter(account.getText().toString()))
                {
                    Toast.makeText(RegisterActivity.this,  "Tên tài khoản không được chưa ký tự đặc biệt", Toast.LENGTH_LONG).show();
                }
                else
                if(!valid.CheckValidLength(pass.getText().toString()))
                {
                    Toast.makeText(RegisterActivity.this,  "Mật khẩu phải chứa ít nhất 6 ký tự", Toast.LENGTH_LONG).show();
                }
                else
                if (!pass.getText().toString().equals(passAccess.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Xác nhận mật khẩu sai!", Toast.LENGTH_LONG).show();
                } else {
                    user = new User();
                    user.setUserName(valid.ReplaceToValidString(account.getText().toString().trim()));
                    user.setPassWord(pass.getText().toString().trim());
                    user.setFullName(fullName.getText().toString().trim());
                    user.setNote("");
                    user.setEmail(email.getText().toString().trim());
                    user.setPhoneNumber(phone.getText().toString().trim());
                    user.setActive(false);
                    MD5 md5 = new MD5();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String datestr = dateFormat.format(date);

                    user.setCreateDate(datestr);
                    try {
                        if(bm == null)
                        {
                            user.setImage("");
                        }
                        else
                        {
                            String image = account.getText().toString() + "::" + md5.getMD5(account.getText().toString() + datestr) + ".jpg";
                            user.setImage(image);
                        }

                    } catch (Exception ex) {

                    }
                    user.setParent(account.getText().toString());
                    user.setValid(true);

                    if (userController.AddUser(user)) {
                        new ShopController().AddShop(new Shop(user.getUserName(),user.getUserName(),
                                new Date().toString(),200,200,"",userController.GetUserID(user.getUserName()),"","",true));
                        if (bm != null) {
                            new UploadFileController().uploadFile(bm, user.getImage());
                        }
                        Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại. Vui lòng chọn tài khoản khác!", Toast.LENGTH_LONG).show();
                        account.setSelection(account.getText().length() -1 );
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
