package retrofit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * interface Json pedidos
 * Esta classe é onde vamos fazer os vários caminhos da nossa API
 * Para isso em primeiro lugar pomos o tipo de pedido
 * (GET, POST OU DELETE) e dentro dos parentisis
 * o caminho
 */
public interface JsonPedidos {
    /**
     * Post users call.
     *
     * @param users the users
     * @return the call
     */
    @POST("login")
    Call<Users> PostUsers(
            @Body
                    Users users
    );

    /**
     * Post logout call.
     *
     * @param token the token
     * @return the call
     */
    @POST("logout")
    Call<Users> PostLogout(
            @Header("Authorization")
                    String token
    );

    /**
     * Get item call.
     *
     * @param token the token
     * @param id    the id
     * @return the call
     */
    @GET("item")
    Call<ItemAtributo> GetItem(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id
    );

    /**
     * Get kit call.
     *
     * @param token the token
     * @param id    the id
     * @return the call
     */
    @GET("kit")
    Call<KitsAtributos> GetKit(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id
    );

    /**
     * Gets item id.
     *
     * @param token the token
     * @param id    the id
     * @return the item id
     */
    @GET("item/{id}")
    Call<Atributo> getItemId(
            @Header("Authorization")
                    String token,
            @Path("id")
                    Integer id
    );

    /**
     * Gets kit id.
     *
     * @param token the token
     * @param id    the id
     * @return the kit id
     */
    @GET("kit/{id}")
    Call<KitId> getKitId(
            @Header("Authorization")
                    String token,
            @Path("id")
                    Integer id
    );

    /**
     * Gets carrinho.
     *
     * @param token the token
     * @param id    the id
     * @return the carrinho
     */
    @GET("carrinho")
    Call<linhaCarrinho> getCarrinho(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id
    );

    /**
     * Post carrinho call.
     *
     * @param token        the token
     * @param idUser       the id user
     * @param id_atributos the id atributos
     * @param id           the id
     * @return the call
     */
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

    /**
     * Delete carrinho call.
     *
     * @param token       the token
     * @param idUser      the id user
     * @param idAtributos the id atributos
     * @param id          the id
     * @return the call
     */
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

    /**
     * Gets reserva linhas.
     *
     * @param token the token
     * @param id    the id
     * @return the reserva linhas
     */
    @GET("reserva")
    Call<linhaCarrinho> getReservaLinhas(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id
    );

    /**
     * Post reserva call.
     *
     * @param token   the token
     * @param id      the id
     * @param reserva the reserva
     * @return the call
     */
    @POST("reserva")
    Call<Reserva> postReserva(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id,
            @Body
                    Reserva reserva
    );

    /**
     * Post search call.
     *
     * @param token  the token
     * @param id     the id
     * @param search the search
     * @return the call
     */
    @POST("search")
    Call<linhaCarrinho> postSearch(
            @Header("Authorization")
                    String token,
            @Header("UserId")
                    Integer id,
            @Body
                    Search search
    );

    /**
     * Gets minha reserva.
     *
     * @param token the token
     * @param id    the id
     * @return the minha reserva
     */
    @GET("reserva/{id}")
    Call<MyReserva> getMinhaReserva(
            @Header("Authorization")
                    String token,
            @Path("id")
                Integer id
    );


}
