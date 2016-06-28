package tvcompany.salemanager.controller.login;

import android.os.StrictMode;

import API.ServiceGenerator;
import API.ServiceInterface;
import retrofit2.Call;
import tvcompany.salemanager.model.Product;
import tvcompany.salemanager.model.Status;

/**
 * Created by MtViet on 28/06/2016.
 */
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
}
