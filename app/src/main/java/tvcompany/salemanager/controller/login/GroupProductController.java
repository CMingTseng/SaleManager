package tvcompany.salemanager.controller.login;

import android.os.StrictMode;

import java.util.ArrayList;
import java.util.List;

import API.ServiceGenerator;
import API.ServiceInterface;
import retrofit2.Call;
import tvcompany.salemanager.model.GroupProduct;
import tvcompany.salemanager.model.Status;

/**
 * Created by MtViet on 30/06/2016.
 */
public class GroupProductController {
    private ServiceInterface service;
    public GroupProductController()
    {
        service = ServiceGenerator.GetInstance();
    }

    public ArrayList<GroupProduct> getProductGroup()
    {
        Call<ArrayList<GroupProduct>> groupProduct = service.getGroupProduct();
        try{
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            ArrayList<GroupProduct> list = groupProduct.execute().body();
            return list;
        }
        catch (Exception ex){

        }
        return new ArrayList<GroupProduct>();
    }
}
