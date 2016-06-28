package tvcompany.salemanager.controller.login;

import android.os.StrictMode;

import API.ServiceGenerator;
import retrofit2.Call;
import tvcompany.salemanager.model.Shop;
import tvcompany.salemanager.model.Status;

/**
 * Created by MtViet on 28/06/2016.
 */
public class ShopController {
    public boolean AddShop(Shop shop)
    {
        Call<Status> status = ServiceGenerator.GetInstance().addShop(shop);
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
