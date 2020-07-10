package retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client instance
 * Esta classe é uma das classes mais importantes para o Retrofit
 * é onde vamos pôr o URL da nossa API
 */
public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://liaandroid.herokuapp.com/api/";

    /**
     * Gets retrofit instance.
     *
     * @return the retrofit instance
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
