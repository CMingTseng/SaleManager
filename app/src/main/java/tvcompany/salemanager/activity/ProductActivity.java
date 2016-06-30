package tvcompany.salemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tvcompany.salemanager.R;
import tvcompany.salemanager.adapter.MultiSelectionSpinner;
import tvcompany.salemanager.controller.login.ProductController;
import tvcompany.salemanager.controller.login.UploadFileController;
import tvcompany.salemanager.library.MD5;
import tvcompany.salemanager.library.ValidString;
import tvcompany.salemanager.model.Product;

public class ProductActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 100;
    private ImageView imageView;
    private Button product_btnAdd;
    private Product product = null;
    private Bitmap bm = null;
    private EditText productID, productName, productPurchase, productOrder,productNote;
    private Spinner productShop,productGroup;
    private ValidString valid;
    private ProductController productController;

    private PopupWindow pw;
    private boolean expanded;
    public static boolean[] checkSelected;
    MultiSelectionSpinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newproduct_layout);

        imageView = (ImageView) findViewById(R.id.iconProduct);
        productID = (EditText) findViewById(R.id.productID);
        productName = (EditText) findViewById(R.id.productName);
        productPurchase = (EditText) findViewById(R.id.productPurchase);
        productOrder = (EditText) findViewById(R.id.productOrder);
        productNote = (EditText) findViewById(R.id.productNote);
        product_btnAdd = (Button) findViewById(R.id.btnProductAdd);
        valid = new ValidString();
        productController = new ProductController();

        // Spinner
        ArrayList<String> list= new ArrayList<String>();
        list.add("Nhóm hàng");
        list.add("VietFuck");//ObjectId("57722707cbd7d1ec020e90ce")
        list.add("Gandalf");
        productShop = (Spinner) findViewById(R.id.product_shop);
        ArrayAdapter adapter=new ArrayAdapter<String>(ProductActivity.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productShop.setAdapter(adapter);

        spinner = (MultiSelectionSpinner) findViewById(R.id.mySpinner1);
        spinner.setItems(list);

        //--------------------------------------------------
        product_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!valid.CheckValidLengthRegex(productID.getText().toString()))
                {
                    Toast.makeText(ProductActivity.this,  "Mã hàng hóa phải chứa ít nhất 6 ký tự và không có dấu", Toast.LENGTH_LONG).show();
                }
                else
                if(!valid.CheckSpecialCharacter(productID.getText().toString()))
                {
                    Toast.makeText(ProductActivity.this,  "Mã hàng hóa không được chưa ký tự đặc biệt", Toast.LENGTH_LONG).show();
                }
                if(!valid.CheckValidLength(productName.getText().toString()))
                {
                    Toast.makeText(ProductActivity.this,  "Tên hàng hóa phải chứa ít nhất 6 ký tự", Toast.LENGTH_LONG).show();
                }
                else
                {
                    product = new Product();
                    product.setID(valid.ReplaceToValidString(productID.getText().toString().trim()));
                    product.setProductName(productName.getText().toString().trim());
                    product.setMoneyPurchase(Double.parseDouble(productPurchase.getText().toString().trim()));
                    product.setMoneyOrder(Double.parseDouble(productOrder.getText().toString().trim()));
                    product.setShopId("57722707cbd7d1ec020e90ce");//ObjectId("")
                    product.setGroupProduct("577127cc16d8341ed074a3b1");
                    product.setNote(productNote.getText().toString().trim());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String datestr = dateFormat.format(date);
                    product.setCreateDate(datestr);
                    try {
                        if(bm == null)
                        {
                            product.setImage("");
                        }
                        else
                        {
                            MD5 md5 = new MD5();
                            //String image = GlobalValue.USERNAME + "::" + md5.getMD5(product.getID() + datestr) + ".jpg";
                            String image = "viet" + "::" + md5.getMD5(product.getID() + datestr) + ".jpg";
                            product.setImage(image);

                        }

                    } catch (Exception ex) {

                    }
                    if (productController.addProduct(product)) {
                        if (bm != null) {
                            new UploadFileController().uploadFile(bm,product.getImage());
                        }
                        Toast.makeText(ProductActivity.this, "Thêm mới hàng hóa thành công!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(ProductActivity.this, "Hàng hóa đã tồn tại. Vui lòng nhập lại mã hàng hóa!", Toast.LENGTH_LONG).show();
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
