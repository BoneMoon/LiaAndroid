package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import adapter.CustomAdapter;
import adapter.CustomAdapterKit;
import retrofit.Item;
import retrofit.JsonPedidos;
import retrofit.Kit;
import retrofit.Reserva;
import retrofit.RetrofitClientInstance;
import retrofit.linhaCarrinho;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

public class ReservaActivity extends AppCompatActivity {

    ListView item;
    ListView k;

    List<Item> linha;
    List<Kit> linhaKit;

    EditText motivo;
    TextView data1;
    TextView data2;
    EditText curso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        item = findViewById(R.id.listaItem);
        k = findViewById(R.id.listaKit);

        motivo = findViewById(R.id.txtMotivo);
        data1 = findViewById(R.id.txtData1);
        data2 = findViewById(R.id.txtData2);
        curso = findViewById(R.id.txtCurso);

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String data_inicio = preferences.getString("data1", "");
        String data_fim = preferences.getString("data2", "");

        data1.setText(data_inicio);
        data2.setText(data_fim);

        getItem();
        getKit();
    }

    private void getKit() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<linhaCarrinho> carrinhoCall = service.getReservaLinhas(token, userId);

        carrinhoCall.enqueue(new Callback<linhaCarrinho>() {
            @Override
            public void onResponse(Call<linhaCarrinho> call, Response<linhaCarrinho> response) {
                if(response.body() != null){
                    linhaKit = response.body().getKits();
                    CustomAdapterKit kit = new CustomAdapterKit(getApplicationContext(), linhaKit);
                    k.setAdapter(kit);
                }else{
                    Toast.makeText(ReservaActivity.this, "Não há Kits!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<linhaCarrinho> call, Throwable t) {
                Toast.makeText(ReservaActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getItem() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<linhaCarrinho> carrinhoCall = service.getReservaLinhas(token, userId);

        carrinhoCall.enqueue(new Callback<linhaCarrinho>() {
            @Override
            public void onResponse(Call<linhaCarrinho> call, Response<linhaCarrinho> response) {
                if(response.body() != null){
                    linha = response.body().getItens();
                    CustomAdapter itens_adapter = new CustomAdapter(getApplicationContext(), linha);
                    item.setAdapter(itens_adapter);
                }else{
                    Toast.makeText(ReservaActivity.this, "Não há itens!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<linhaCarrinho> call, Throwable t) {
                Toast.makeText(ReservaActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnReserva(View view) {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);

        //Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio);
        //Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(dataFim);
        //Log.i("tag", date1.);
        Reserva reserva = new Reserva(motivo.getText().toString(), data1.getText().toString(), data2.getText().toString(), curso.getText().toString());
        Call<Reserva> reservaCall = service.postReserva(token, userId, reserva);

        reservaCall.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                if (response.body() != null){
                    Toast.makeText(ReservaActivity.this, "Reserva feita com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReservaActivity.this, ItemActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ReservaActivity.this, "Tem que preencher todos os campos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Toast.makeText(ReservaActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
