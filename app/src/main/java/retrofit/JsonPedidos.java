package retrofit;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import java.util.ArrayList;
import java.util.Collection;

public interface JsonPedidos {
    @POST("login")
    Call<Users> PostUsers(
            @Body
                    Users users
    );

    @POST("logout")
    Call<Users> PostLogout(
            @Header("Authorization")
                    String token
    );

    @GET("item")
    Call<ItemAtributo> GetItem(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id
    );
}
