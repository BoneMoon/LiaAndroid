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
import retrofit.Item;
import retrofit.ItemAtributo;
import retrofit.JsonPedidos;
import retrofit.KitId;
import retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

public class getKitActivity extends AppCompatActivity {

    private TextView nameKit;
    private TextView descricao;
    private TextView nItem;

    private Integer idK;
    private String token;

    List<Item> itens;

    private String item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_kit);

        nameKit = findViewById(R.id.nameKit);
        descricao = findViewById(R.id.desc);
        nItem = findViewById(R.id.nItem);

        Intent i = getIntent();
        idK = i.getIntExtra("idK", 0);

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
                Log.i("tag", t.toString());
                Toast.makeText(getKitActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void btnCarrinho(View view) {
    }
}
