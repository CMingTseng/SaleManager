package tvcompany.salemanager.controller.login;

import android.os.StrictMode;

import java.util.ArrayList;

import API.ServiceGenerator;
import API.ServiceInterface;
import retrofit2.Call;
import tvcompany.salemanager.model.Product;
import tvcompany.salemanager.model.Status;


public class ProductController {
    private ServiceInterface service;
    public ProductController()
    {
        service = ServiceGenerator.GetInstance();
    }

    public boolean addProduct(Product product)
    {
        Call<Status> status = service.addProduct(product);
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Status str = status.execute().body();
            if(str.getStatus().equals("Success"))
            {
                return true;
            }
            return false;
        }
        catch (Exception ex){
            return false;
        }
    }

    public ArrayList<Product> getListProduct()
    {
        Call<ArrayList<Product>> listProduct = service.getListProduct();
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            ArrayList<Product> lst = listProduct.execute().body();
            return lst;
        }
        catch (Exception ex){

        }
        return new ArrayList<Product>();
    }

    public boolean updateProduct(Product product)
    {
        Call<Status> call = service.updateProduct(product);
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            Status status = call.execute().body();
            if(status.getStatus().equals("Success"))
            {
                return  true;
            }
        }
        catch (Exception ex){

        }
        return false;
    }

}
