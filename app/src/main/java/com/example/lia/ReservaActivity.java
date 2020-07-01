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

/**
 * Reserva activity
 */
public class ReservaActivity extends AppCompatActivity {

    /**
     * ListView Item
     */
    ListView item;
    /**
     * ListView k
     */
    ListView k;

    /**
     *  List<Item> linha
     */
    List<Item> linha;
    /**
     * List<Kit> linhaKit
     */
    List<Kit> linhaKit;

    /**
     * EditText motivo
     */
    EditText motivo;
    /**
     * TextView data1
     */
    TextView data1;
    /**
     * TextView data2
     */
    TextView data2;
    /**
     * EditText curso
     */
    EditText curso;


    /**
     * @param savedInstanceState
     * Neste método vamos buscar os ids dos EditText e TextView
     * correspondentes ao XML da atividade.
     * Depois com o SharedPrefences vamos buscar as datas de inicio
     * e fim que já estão definidas quando se pesquisa na atividade do Item
     * ou do Kit e pomos nas TextView correspondentes desta atividade
     *
     * Por último chamados duas funções getItem() e getKit() que nos vão dar
     * os items e os kits que o utlizador está a reservar
     */
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

    /**
     * Método getKit() -> método onde vamos receber o carrinho de compras
     * Caso o utilizador mude de datas o carrinho é elminado.
     *
     * Tal como o carrinho este método usa a mesma coisa.
     * A resposta que vamos receber vai ter duas lista uma de items e outra de kits
     * Portanto aqui vamos buscar a lista dos kits e pomos no customAdapter correspondete
     */
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

    /**
     * Método getKit() -> método onde vamos receber o carrinho de compras
     * Caso o utilizador mude de datas o carrinho é elminado.
     *
     * Tal como o carrinho este método usa a mesma coisa.
     * A resposta que vamos receber vai ter duas lista uma de items e outra de kits
     * Portanto aqui vamos buscar a lista dos items e pomos no customAdapter correspondete
     */
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

    /**
     * Btn reserva
     * É com este método que vamos fazer a reserva
     *
     * Para isso começamos por ir buscar ao SharedPreferences a apitoken e o userId
     * Depois vamos verificar se as datas estão preenchidas
     * De seguida criamos uma nova reserva com os valores que o utilizador pôs
     * na atividade
     * Por último se a reserva foi feita com sucesso aparece um Toast e o utilizador é
     * reencaminhado para outra atividade
     *
     * @param view the view
     */
    public void btnReserva(View view) {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);

        if(data1.getText().toString().equals("")){
            Toast.makeText(ReservaActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        }else if (data2.getText().toString().equals("")){
            Toast.makeText(ReservaActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        }

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
