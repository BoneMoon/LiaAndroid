package retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

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
}
