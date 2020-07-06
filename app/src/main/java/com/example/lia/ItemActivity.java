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
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import adapter.CustomAdapter;
import retrofit.Item;
import retrofit.ItemAtributo;
import retrofit.JsonPedidos;
import retrofit.Kit;
import retrofit.RetrofitClientInstance;
import retrofit.Search;
import retrofit.Users;
import retrofit.linhaCarrinho;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

/**
 * Item activity
 */
public class ItemActivity extends AppCompatActivity {
    /**
     * ItemAtributo Itens
     */
    ItemAtributo itens;
    /**
     * DatePickerDialog picker1
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
     * List linhaItem of items
     */
    List<Item> linhaItem;

    EditText pesquisaNome;

    /**
     * @param savedInstanceState
     * Este método é chamado assim que a atividade começa,
     * por isso mesmo é aqui que chamamos ambos os métodos
     * getItem() e getItemID()
     *
     * Além disso é aqui onde eu faço que quando carrega no EditText para pôr
     * as datas aparece um calendário para escolher as datas
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getItem();
        getItemID();

        data1 = findViewById(R.id.dataPicker1);
        data2 = findViewById(R.id.dataPicker2);
        pesquisaNome = findViewById(R.id.pesquisaNome);


        data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int moth = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(ItemActivity.this,
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

                picker2 = new DatePickerDialog(ItemActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                data2.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        }, year, moth, day);
                picker2.show();
            }
        });
    }

    private void checkDates() {
        data1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = data1.getText().toString();
                itens.getItensatributos().clear();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        data2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                itens.getItensatributos().clear();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Método Get item -> método onde recebo todos os itens
     * Para começar vou buscar ao SharedPreferences o token e o userId
     * Logo depois a resposta vai ser um ItemAtributo que é uma List<Item>
     * Por isso depois é só pôr a resposta no CustomAdapter
     */
    public void getItem(){
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);
        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<ItemAtributo> itemCall = service.GetItem(token, userId);

        itemCall.enqueue(new Callback<ItemAtributo>() {
            @Override
            public void onResponse(Call<ItemAtributo> call, Response<ItemAtributo> response) {
                if(response.body() != null){
                    itens = response.body();
                    CustomAdapter itens_adapter = new CustomAdapter(getApplicationContext(), itens.getItensatributos());
                    ((ListView) findViewById(R.id.lista)).setAdapter(itens_adapter);
                    checkDates();
                }else{
                    Toast.makeText(ItemActivity.this, "Não há itens!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemAtributo> call, Throwable t) {
                Toast.makeText(ItemActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Método Get item id -> método que serve para quando carregarmos num item qualquer
     * irmos para outra atividade onde vamos ver os detalhes do item seleionado
     *
     * Para isso a primeira coisa a fazer é por um setOnItemClickListener() na lista
     * De seguida para o item naquela posição vamos guardar o valor no nome, id e o
     * id atributo
     * Depois seguimos para outra atividade
     */
    public void getItemID(){
        final ListView item = findViewById(R.id.lista);

        item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = ((CustomAdapter)item.getAdapter()).getItem(position).getName();
                Integer idItem = ((CustomAdapter)item.getAdapter()).getItem(position).getId();
                Integer idAtributo = ((CustomAdapter)item.getAdapter()).getItem(position).getId_atributos();

                Intent i = new Intent(ItemActivity.this, getItemActivity.class);

                i.putExtra("name", name);
                i.putExtra("idItem", idItem);
                i.putExtra("idAtributo", idAtributo);

                startActivity(i);
            }
        });
    }

    /**
     * @param menu
     * Escolher o menu que vai aparecer
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * @param item
     * Escolher das diferentes opções do menu e o que acontece quando lá carregamos
     *
     * Primeiramente fazemos um switch() e depois case() onde vamos ver caso ele carregue
     * no menu que tem o id o que acontece
     *
     * no Logout:
     * O utilizador volta à atividade do inicio que neste caso é onde se encontra o login
     *
     * no Kits e carrinho:
     * Quando carrega nalgum destes o utilizador vai para outra atividade
     *
     * na Reserva e minha_reserva:
     * Antes de puder it para alguma das atividades vamos verificar o seu
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
                            Intent intent = new Intent(ItemActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Users> call, Throwable t) {
                            Toast.makeText(ItemActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                return true;

            case R.id.kits:
                Intent intent = new Intent(ItemActivity.this, KitActivity.class);
                startActivity(intent);
                return true;

            case R.id.carrinho:
                Intent i = new Intent(ItemActivity.this, CarrinhoActivity.class);
                startActivity(i);
                return true;

            case R.id.reserva:
                SharedPreferences preferencesGrupo = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                Integer grupoId = preferencesGrupo.getInt("grupoid", 0);

                if( grupoId == 2 || grupoId == 11){
                    Toast.makeText(ItemActivity.this, "Não tem permissões para esta funcionalidade!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent in = new Intent(ItemActivity.this, ReservaActivity.class);
                    startActivity(in);
                }
                return true;

            case R.id.minha_reserva:
                SharedPreferences preferencesGrupoId = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                Integer grupo = preferencesGrupoId.getInt("grupoid", 0);
                if( grupo == 2 || grupo == 11){
                    Toast.makeText(ItemActivity.this, "Não tem permissões para esta funcionalidade!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent inte = new Intent(ItemActivity.this, MinhaReservaActivity.class);
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
            Toast.makeText(ItemActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        } else if (data2.getText().toString().equals("")){
            Toast.makeText(ItemActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String token = preferences.getString("apitoken", "");
        Integer userId = preferences.getInt("userid", 0);
        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Call<linhaCarrinho> searchCall = service.postSearch(token, userId, new Search(data1.getText().toString(), data2.getText().toString()));

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("data1", data1.getText().toString());
        editor.putString("data2", data2.getText().toString());
        editor.apply();

        searchCall.enqueue(new Callback<linhaCarrinho>() {
            @Override
            public void onResponse(Call<linhaCarrinho> call, Response<linhaCarrinho> response) {
                if(response.body() != null){
                    linhaItem = response.body().getItens();
                    CustomAdapter itens_adapter = new CustomAdapter(getApplicationContext(), linhaItem);
                    ((ListView) findViewById(R.id.lista)).setAdapter(itens_adapter);
                    Toast.makeText(ItemActivity.this, "Pesquisa com sucesso!", Toast.LENGTH_SHORT).show();
                    checkDates();
                }else{
                    Toast.makeText(ItemActivity.this, "Não há itens!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<linhaCarrinho> call, Throwable t) {
                Toast.makeText(ItemActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnPesquisaNome(View view) {
        String nome = pesquisaNome.getText().toString();
        List<Item> item;
        item = new ArrayList<>();
        
        for (int i = 0; i < linhaItem.size(); i++){
            if(linhaItem.get(i).getName().toLowerCase().contains(nome.toLowerCase())){
                item.add(linhaItem.get(i));
            }
        }
        CustomAdapter itens_adapter = new CustomAdapter(getApplicationContext(), item);
        ((ListView) findViewById(R.id.lista)).setAdapter(itens_adapter);
    }
}
