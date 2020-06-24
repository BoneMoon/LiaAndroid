package retrofit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @GET("carrinho")
    Call<linhaCarrinho> getCarrinho(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id
    );

    @POST("carrinho/{id_atributos}")
    Call<String> postCarrinho(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer idUser,
            @Path("id_atributos")
                    Integer id_atributos,
            @Body()
                    IdItemKit id
    );

    @DELETE("carrinho/{id}")
    Call<ResponseBody> deleteCarrinho(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer idUser,
            @Header("IdAtributos")
                    Integer idAtributos,
            @Path("id")
                    Integer id
    );

    @GET("reserva")
    Call<linhaCarrinho> getReservaLinhas(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id
    );

    @POST("reserva")
    Call<Reserva> postReserva(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id,
            @Body
                    Reserva reserva
    );

    @POST("search")
    Call<linhaCarrinho> postSearch(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id,
            @Body
                    Search search
    );

}
