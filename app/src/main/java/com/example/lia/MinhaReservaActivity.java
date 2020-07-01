package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import adapter.CustomAdapterReserva;
import retrofit.JsonPedidos;
import retrofit.MyReserva;
import retrofit.Reserva;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

/**
 * Minha reserva activity
 */
public class MinhaReservaActivity extends AppCompatActivity {
    /**
     * MyReserva myReserva
     */
    MyReserva myReserva;

    /**
     * @param savedInstanceState
     * No início desta atividade vamos chamar a função getReservas()
     * que nos vai returnar as reservas feitas por o utilizador
     * que esta logado
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_reserva);

        getReservas();
    }

    /**
     * Método getReservas() -> receber as reservas feitas pelo utilizador
     * Para isso começamos por ir buscar o token e o userId com o SharedPreferences
     * Depois caso a resposta for feita com sucesso associamos a resposta ao tipo de returno que
     * dá e de seguida associamos ao customAdapter correspondente
     *
     */
    private void getReservas() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<MyReserva> minhaReservaCall = service.getMinhaReserva(token, userId);

        minhaReservaCall.enqueue(new Callback<MyReserva>() {
            @Override
            public void onResponse(Call<MyReserva> call, Response<MyReserva> response) {
                if (response.body() != null){
                    myReserva = response.body();
                    CustomAdapterReserva reserva_adapter = new CustomAdapterReserva(getApplicationContext(), myReserva.getReserva());
                    ((ListView) findViewById(R.id.minha_reserva_lista)).setAdapter(reserva_adapter);
                    Toast.makeText(MinhaReservaActivity.this, "As minhas reservas!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MinhaReservaActivity.this, "Não tem Reservas!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MyReserva> call, Throwable t) {
                Toast.makeText(MinhaReservaActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
