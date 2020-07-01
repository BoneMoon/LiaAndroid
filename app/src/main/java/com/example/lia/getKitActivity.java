package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import adapter.CustomAdapter;
import retrofit.Atributo;
import retrofit.IdItemKit;
import retrofit.Item;
import retrofit.ItemAtributo;
import retrofit.JsonPedidos;
import retrofit.KitId;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

/**
 * Get kit activity
 */
public class getKitActivity extends AppCompatActivity {

    private TextView nameKit;
    private TextView descricao;
    private TextView nItem;

    private Integer idK;
    private Integer idAtributo;

    private String token;

    /**
     * List<Item> itens
     */
    List<Item> itens;

    private String item;


    /**
     * @param savedInstanceState
     * Neste método vamos começar por ir buscar ao XML aos ids correspondentes
     * a cada TextView
     * Logo depois com a ajuda do putExtra() vamos buscar o idItem e o idAtributo
     * à atividade do Item
     * De seguida na resposta vamos buscar o queremos à resposta que neste caso é a descrição
     * e vamos támbém buscar a lista dos items que esta associado a esse kit
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_kit);

        nameKit = findViewById(R.id.nameKit);
        descricao = findViewById(R.id.desc);
        nItem = findViewById(R.id.nItem);

        Intent i = getIntent();
        idK = i.getIntExtra("idK", 0);
        idAtributo = i.getIntExtra("id_atributos", 0);

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        token = preferences.getString("apitoken", "api");

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        final Call<KitId> getKitId = service.getKitId(token, idK);

        getKitId.enqueue(new Callback<KitId>() {
            @Override
            public void onResponse(Call<KitId> call, Response<KitId> response) {
                if(response.body() != null){
                    Intent i = getIntent();
                    nameKit.setText(i.getStringExtra("nome"));
                    descricao.setText(response.body().getAtributo().getDescricao());

                    itens = response.body().getItens();

                    if(itens.isEmpty()){
                        nItem.setText("Não contém nenhum item");
                    }else{
                            for (int j = 0; j < itens.size(); j++){
                                item = itens.get(j).getName() + ", " + itens.get(j++).getName();
                                nItem.setText(item);
                            }
                    }
                }else {
                    Toast.makeText(getKitActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KitId> call, Throwable t) {
                Toast.makeText(getKitActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * Btn carrinho.
     * Este método serve para o utilizador adicionar o item ao carrinho
     * Para isso começamos ir buscar as datas e verificar se estão preenchidas
     * E depois quando a resposta for positiva quer dizer que o kit foi adicionado
     * @param view the view
     */
    public void btnCarrinho(View view) {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        token = preferences.getString("apitoken", "api");
        Integer userId = preferences.getInt("userid", 0);

        String data_inicio = preferences.getString("data1", "");
        String data_fim = preferences.getString("data2", "");

        if(data_inicio.equals("")){
            Toast.makeText(getKitActivity.this, "Datas têm que ser preenchidas", Toast.LENGTH_SHORT).show();
        } else if(data_fim.equals("")) {
            Toast.makeText(getKitActivity.this, "Datas têm que ser preenchidas", Toast.LENGTH_SHORT).show();
        }else{
            JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
            Call<String> postCarrinho = service.postCarrinho(token, userId, idAtributo, new IdItemKit(idK));

            postCarrinho.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if ( response.body() != null){
                        Toast.makeText(getKitActivity.this, "Kit adicionado ao carrinho", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getKitActivity.this, "Kit já adicionado", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getKitActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
