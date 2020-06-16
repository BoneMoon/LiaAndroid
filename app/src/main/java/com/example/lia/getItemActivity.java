package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Atributo;
import retrofit.IdItemKit;
import retrofit.JsonPedidos;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

public class getItemActivity extends AppCompatActivity {

    private TextView nome;
    private TextView marca;
    private TextView modelo;
    private TextView descricao;

    private Integer idItem;
    private Integer idAtributo;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_item);

        nome = findViewById(R.id.nameItem);
        marca = findViewById(R.id.marca);
        modelo = findViewById(R.id.modelo);
        descricao = findViewById(R.id.descricao);

        Intent i = getIntent();
        idItem = i.getIntExtra("idItem", 0);
        idAtributo = i.getIntExtra("idAtributo", 0);

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        token = preferences.getString("apitoken", "api");

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        final Call<Atributo> getAtributo = service.getItemId(token, idItem);
        getAtributo.enqueue(new Callback<Atributo>() {
            @Override
            public void onResponse(Call<Atributo> call, Response<Atributo> response) {
                if (response.body() != null){
                    Intent i = getIntent();
                    nome.setText(i.getStringExtra("name"));

                    marca.setText(response.body().getMarca());
                    modelo.setText(response.body().getModelo());
                    descricao.setText(response.body().getDescricao());

                }else {
                    Toast.makeText(getItemActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Atributo> call, Throwable t) {
                Toast.makeText(getItemActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void btnCarrinho(View view) {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        token = preferences.getString("apitoken", "api");
        Integer userId = preferences.getInt("userid", 0);

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<String> postCarrinho = service.postCarrinho(token, userId, idAtributo, new IdItemKit(idItem));

        postCarrinho.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if ( response.body() != null){
                    Toast.makeText(getItemActivity.this, "Item adicionado ao carrinho", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getItemActivity.this, "Item já adicionado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getItemActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
