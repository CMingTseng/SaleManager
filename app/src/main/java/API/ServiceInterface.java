package API;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import tvcompany.salemanager.model.Order;
import tvcompany.salemanager.model.Status;
import tvcompany.salemanager.model.User;

public interface ServiceInterface {
    @GET("/listUsers")
    Call<List<User>> getUser();

    @Multipart
    @POST("/")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);

    @GET("/GetAccount")
    Call<User> getFuck(@Query("account") String account);

    @Headers("Content-Type: application/json")
    @POST("/AddUser")
    Call<Status> addUser(@Body User user);

    @PUT("/UpdateUser")
    Call<Status> UpdateUser(@Body User user);

    @PUT("/DeleteUser")
    Call<Status> DeleteUser(@Query("account") String user);

    @GET("/uploads/Screenshot_2016-05-28-07-39-50.png")
    Call<ResponseBody> getImageDetails();

    @POST("/AddOrder")
    Call<Status> newOrder(@Body Order order);
}
