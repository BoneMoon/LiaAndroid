package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import adapter.CustomAdapter;
import adapter.CustomAdapterKit;
import okhttp3.ResponseBody;
import retrofit.IdItemKit;
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

    ListView item;
    ListView k;

    String token;
    private Integer idK;
    private Integer idAtributo;
    private Integer idItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        item = findViewById(R.id.listaItem);
        k = findViewById(R.id.listaKit);



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
                    k.setAdapter(kit);
                    deleteKit();
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
                    item.setAdapter(itens_adapter);
                    deleteItem();
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

    public void deleteItem(){
        item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                token = preferences.getString("apitoken", "api");
                Integer userId = preferences.getInt("userid", 0);

                idAtributo = linha.get(position).getId_atributos();
                idItem = linha.get(position).getId();

                JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
                Call<ResponseBody> deleteCall = service.deleteCarrinho(token, userId, idAtributo, idItem);

                deleteCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(CarrinhoActivity.this, "Item apagado!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CarrinhoActivity.this, CarrinhoActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(CarrinhoActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public void deleteKit(){
        k.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                token = preferences.getString("apitoken", "api");
                Integer userId = preferences.getInt("userid", 0);

                idAtributo = linhaKit.get(position).getId_atributos();
                idK = linhaKit.get(position).getId();

                JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
                Call<ResponseBody> deleteCall = service.deleteCarrinho(token, userId, idAtributo, idK);

                deleteCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(CarrinhoActivity.this, "Kit apagado!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CarrinhoActivity.this, CarrinhoActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(CarrinhoActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}
