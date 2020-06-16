package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import adapter.CustomAdapter;
import adapter.CustomAdapterKit;
import retrofit.Item;
import retrofit.ItemAtributo;
import retrofit.JsonPedidos;
import retrofit.Kit;
import retrofit.KitsAtributos;
import retrofit.RetrofitClientInstance;
import retrofit.linhaCarrinho;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

public class CarrinhoActivity extends AppCompatActivity {
    ItemAtributo itens;
    KitsAtributos kits;

    List<Item> linha;
    List<Kit> linhaKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        getItem();
        getKit();
    }

    private void getKit() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<linhaCarrinho> carrinhoCall = service.getCarrinho(token, userId);

        carrinhoCall.enqueue(new Callback<linhaCarrinho>() {
            @Override
            public void onResponse(Call<linhaCarrinho> call, Response<linhaCarrinho> response) {
                if(response.body() != null){
                    linhaKit = response.body().getKits();
                    CustomAdapterKit kit = new CustomAdapterKit(getApplicationContext(), linhaKit);
                    ((ListView) findViewById(R.id.listaKit)).setAdapter(kit);
                }else{
                    Toast.makeText(CarrinhoActivity.this, "Não há Kits!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<linhaCarrinho> call, Throwable t) {
                Toast.makeText(CarrinhoActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getItem() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<linhaCarrinho> carrinhoCall = service.getCarrinho(token, userId);

        carrinhoCall.enqueue(new Callback<linhaCarrinho>() {
            @Override
            public void onResponse(Call<linhaCarrinho> call, Response<linhaCarrinho> response) {
                if(response.body() != null){
                    linha = response.body().getItens();
                    CustomAdapter itens_adapter = new CustomAdapter(getApplicationContext(), linha);
                    ((ListView) findViewById(R.id.listaItem)).setAdapter(itens_adapter);

                }else{
                    Toast.makeText(CarrinhoActivity.this, "Não há itens!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<linhaCarrinho> call, Throwable t) {
                Toast.makeText(CarrinhoActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
