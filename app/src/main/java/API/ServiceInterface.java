package API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tvcompany.salemanager.model.User;

public interface ServiceInterface {
    @GET("/listUsers")
    Call<List<User>> getUser();
}
