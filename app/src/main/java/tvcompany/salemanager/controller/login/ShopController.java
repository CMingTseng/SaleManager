package tvcompany.salemanager.controller.login;

import android.os.StrictMode;

import API.ServiceGenerator;
import API.ServiceInterface;
import retrofit2.Call;
import tvcompany.salemanager.model.Shop;
import tvcompany.salemanager.model.Status;

public class ShopController {

    private ServiceInterface service;
    public ShopController()
    {
        service =ServiceGenerator.GetInstance();
    }

    public boolean AddShop(Shop shop)
    {
        Call<Status> status = service.addShop(shop);
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
