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

/**
 * Carrinho activity
 */
public class CarrinhoActivity extends AppCompatActivity {
    /**
     * ItemAtributo Itens
     */
    ItemAtributo itens;
    /**
     * KitsAtributos Kits
     */
    KitsAtributos kits;

    /**
     * List of itens linha
     */
    List<Item> linha;

    /**
     * List of kits linhaKit
     */
    List<Kit> linhaKit;

    /**
     * ListView item
     */
    ListView item;

    /**
     * ListView k
     */
    ListView k;

    /**
     * String token
     */
    String token;

    private Integer idK;
    private Integer idAtributo;
    private Integer idItem;

    /**
     * @param savedInstanceState
     * Este método é chamado assim que a atividade começa,
     * por isso mesmo é aqui que chamamos ambos os métodos
     * getItem() e getKit()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        item = findViewById(R.id.listaItem);
        k = findViewById(R.id.listaKit);

        getItem();
        getKit();
    }

    /**
     * Método para receber os kits
     * Como estou a receber linhaCarrinho que é duas listas uma de items e outra de kits
     * Em primeiro vou buscar o token e o userId ao SharedPreference
     * De seguida como estou a receber duas listas em primeiro vou receber a lista dos kits,
     * para isso vou à resposta e vou buscar os kits
     * Depois ponho a lista dos kits no customAdapter
     *
     * Por último chamo o método deleteKit() para que a lista esteja atualizada conforme
     * o número de kits
     */
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

    /**
     * Método para receber os items
     * Como estou a receber linhaCarrinho que é duas listas uma de items e outra de kits
     * Em primeiro vou buscar o token e o userId ao SharedPreference
     * De seguida como estou a receber duas listas mas quero receber os items,
     * para isso vou à resposta e vou buscar os items
     * Depois ponho a lista dos items no customAdapter
     *
     * Por último chamo o método deleteKit() para que a lista esteja atualizada conforme
     * o número de kits
     */
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

    /**
     * Método para apagar item
     * Para apagarmos o item vamos ter que carrega no item que queremos apagar
     * Para isso em primeiro lugar pomos um setOnItemClickListener() na lista
     * Logo depois vamos buscar novamente ao SharedPreferences o token e o userId
     * De seguida vamos buscar o idAtributo e o idItem corresponde à posição da lista
     * Caso a resposta seja positiva aparece um Toast e vamos fazer o start da atividade para atualizar a lista
     */
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

    /**
     * Método para apagar kit
     * Para apagarmos o item vamos ter que carrega no item que queremos apagar
     * Para isso em primeiro lugar pomos um setOnItemClickListener() na lista
     * Logo depois vamos buscar novamente ao SharedPreferences o token e o userId
     * De seguida vamos buscar o idAtributo e o idItem corresponde à posição da lista
     * Caso a resposta seja positiva aparece um Toast e vamos fazer o start da atividade para atualizar a lista
     */
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
