package retrofit;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("kit")
    Call<KitsAtributos> GetKit(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id
    );

    @GET("item/{id}")
    Call<Atributo> getItemId(
            @Header("Authorization")
                    String token,
            @Path("id")
                    Integer id
    );

    @GET("kit/{id}")
    Call<KitId> getKitId(
            @Header("Authorization")
                    String token,
            @Path("id")
                    Integer id
    );
}
