package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import adapter.CustomAdapter;
import adapter.CustomAdapterKit;
import retrofit.ItemAtributo;
import retrofit.JsonPedidos;
import retrofit.Kit;
import retrofit.KitsAtributos;
import retrofit.RetrofitClientInstance;
import retrofit.Search;
import retrofit.Users;
import retrofit.linhaCarrinho;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

/**
 * Kit activity
 */
public class KitActivity extends AppCompatActivity {
    /**
     * KitsAtributos Kits
     */
    KitsAtributos kits;
    /**
     * DatePickerDialog picker
     */
    DatePickerDialog picker;
    /**
     * DatePickerDialog picker2
     */
    DatePickerDialog picker2;
    /**
     * EditText data1
     */
    EditText data1;
    /**
     * EditText data2
     */
    EditText data2;

    /**
     * List linhaKit of Kit
     */
    List<Kit> linhaKit;

    ListView a;

    String value;

    /**
     * @param savedInstanceState
     * Este método é chamado assim que a atividade começa,
     * por isso mesmo é aqui que chamamos ambos os métodos
     * geKit() e getKitId()
     *
     * Além disso é aqui onde eu faço que quando carrega no EditText para pôr
     * as datas aparece um calendário para escolher as datas.
     *
     * E ainda vamos buscar as datas da atividade do Item usando o SharedPreferences
     * e caso esteja preenchidas vão aparecer nesta atividade
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kit);

        getKit();
        getKitId();

        data1 = findViewById(R.id.dataPicker1);
        data2 = findViewById(R.id.dataPicker2);
        //a = findViewById(R.id.listaKit);

        data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int moth = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(KitActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                data1.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        }, year, moth, day);
                picker.show();
            }
        });

        data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int moth = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker2 = new DatePickerDialog(KitActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                data2.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        }, year, moth, day);
                picker2.show();
            }
        });

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String data_inicio = preferences.getString("data1", "");
        String data_fim = preferences.getString("data2", "");

        data1.setText(data_inicio);
        data2.setText(data_fim);

        //checkDates();
    }

    /*private void checkDates() {
        data1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                value = data1.getText().toString();
                //a.setAdapter(null);
                //a.invalidate();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }*/

    /**
     * Método Get kit->  método onde recebo todos os kits
     * Para começar vou buscar ao SharedPreferences o token e o userId
     * Logo depois a resposta vai ser um KitAtricuto que é uma List<Kit>
     * Por isso depois é só pôr a resposta no CustomAdapter
     */
    public void getKit(){
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);

        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<KitsAtributos> kitCall = service.GetKit(token, userId);

        kitCall.enqueue(new Callback<KitsAtributos>() {
            @Override
            public void onResponse(Call<KitsAtributos> call, Response<KitsAtributos> response) {
                if(response.body() != null){
                    kits = response.body();
                    CustomAdapterKit kits_adapter = new CustomAdapterKit(getApplicationContext(), kits.getKitsatributos());
                    ((ListView) findViewById(R.id.listaKit)).setAdapter(kits_adapter);
                }else{
                    Toast.makeText(KitActivity.this, "Não há kits!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KitsAtributos> call, Throwable t) {
                Toast.makeText(KitActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Métod Get kit id -> método que serve para quando carregarmos num kit qualquer
     * irmos para outra atividade onde vamos ver os detalhes do kit seleionado
     *
     * Para isso a primeira coisa a fazer é por um setOnItemClickListener() na lista
     * De seguida para o kit naquela posição vamos guardar o valor no nome, id e o
     * id atributo
     * Depois seguimos para outra atividade
     */
    public void getKitId(){
        final ListView kit = findViewById(R.id.listaKit);

        kit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String nome = ((CustomAdapterKit)kit.getAdapter()).getItem(position).getName();
                int idK = ((CustomAdapterKit)kit.getAdapter()).getItem(position).getId();
                int idAtributo = ((CustomAdapterKit)kit.getAdapter()).getItem(position).getId_atributos();


                Intent i = new Intent(KitActivity.this, getKitActivity.class);

                i.putExtra("nome", nome);
                i.putExtra("idK", idK);
                i.putExtra("id_atributos", idAtributo);

                startActivity(i);
            }
        });
    }

    /**
     * @param menu
     * Escolher o menu que vai aparecer, escolhendo o layout
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_kit, menu);
        return true;
    }

    /**
     * @param item
     * Escolher das diferentes opções do menu e o que acontece quando lá carregamos
     *
     * Primeiramente fazemos um switch() e depois case() onde vamos ver caso ele carregue
     * no menu que tem o id o que acontece
     *      *
     * no Logout:
     * O utilizador volta à atividade do inicio que neste caso é onde se encontra o login
     *
     * no Item e carrinho:
     * Quando carrega nalgum destes o utilizador vai para outra atividade
     *
     * na Reserva e minha_reserva:
     * Antes de puder ir para alguma das atividades vamos verificar o seu
     * grupoId pois caso ele esteja no grupo default o utilizador não pode
     * reserva e como não pode reservar também não tem reservas
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                String token = preferences.getString("apitoken", "");

                if(token != null){
                    JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
                    Call<Users> logoutCall = service.PostLogout(token);
                    logoutCall.enqueue(new Callback<Users>() {
                        @Override
                        public void onResponse(Call<Users> call, Response<Users> response) {
                            Toast.makeText(getApplicationContext(), "Logout com sucesso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(KitActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Users> call, Throwable t) {
                            Toast.makeText(KitActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                return true;

            case R.id.item:
                Intent intent = new Intent(KitActivity.this, ItemActivity.class);
                startActivity(intent);
                return true;

            case R.id.carrinho:
                Intent i = new Intent(KitActivity.this, CarrinhoActivity.class);
                startActivity(i);
                return true;

            case R.id.reserva:
                SharedPreferences preferencesGrupo = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                Integer grupoId = preferencesGrupo.getInt("grupoid", 0);

                if( grupoId == 2 || grupoId == 11){
                    Toast.makeText(KitActivity.this, "Não tem permissões para esta funcionalidade!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent in = new Intent(KitActivity.this, ReservaActivity.class);
                    startActivity(in);
                }
                return true;

            case R.id.minha_reserva:
                SharedPreferences preferencesGrupoId = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                Integer grupo = preferencesGrupoId.getInt("grupoid", 0);
                if( grupo == 2 || grupo == 11){
                    Toast.makeText(KitActivity.this, "Não tem permissões para esta funcionalidade!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent inte = new Intent(KitActivity.this, MinhaReservaActivity.class);
                    startActivity(inte);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Btn pesquisa-> fazer pesquisa por datas
     *
     * Para fazer a pesquisa ambas as datas têm que estar preenchida, logo
     * se não tiveram vai aparecer um Toast
     *
     * No método onCreate() vamos buscar as datas que estão na atividade do item
     *
     * Como no carrinho esta resposta também returna duas lista, items e kits.
     * Para só ser os itens vamos à resposta e getItems()
     *
     * Depois quando estiverem as datas vamos fazer um update à lista dos items, onde
     * vão só aprecer os itens que estão disponíveis para reserva entre as datas que
     * o utilizador pôs
     *
     * @param view the view
     */
    public void btnPesquisa(View view) {
        if (data1.getText().toString().equals("")){
            Toast.makeText(KitActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        } else if (data2.getText().toString().equals("")){
            Toast.makeText(KitActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);
        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<linhaCarrinho> searchCall = service.postSearch(token, userId, new Search(data1.getText().toString(), data2.getText().toString()));

        searchCall.enqueue(new Callback<linhaCarrinho>() {
            @Override
            public void onResponse(Call<linhaCarrinho> call, Response<linhaCarrinho> response) {
                if(response.body() != null){
                    linhaKit = response.body().getKits();
                    CustomAdapterKit kits_adapter = new CustomAdapterKit(getApplicationContext(), linhaKit);
                    ((ListView) findViewById(R.id.listaKit)).setAdapter(kits_adapter);

                    Toast.makeText(KitActivity.this, "Pesquisa com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(KitActivity.this, "Não há Kits!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<linhaCarrinho> call, Throwable t) {
                Log.i("tag", t.toString());
                Toast.makeText(KitActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
