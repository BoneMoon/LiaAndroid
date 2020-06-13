package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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


import java.util.Calendar;

import adapter.CustomAdapter;
import retrofit.ItemAtributo;
import retrofit.JsonPedidos;
import retrofit.RetrofitClientInstance;
import retrofit.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

public class ItemActivity extends AppCompatActivity {
    ItemAtributo itens;
    DatePickerDialog picker;
    DatePickerDialog picker2;
    EditText data1;
    EditText data2;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getItem();
        getItemID();

        data1 = findViewById(R.id.dataPicker1);
        data2 = findViewById(R.id.dataPicker2);


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
                                data1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
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
                                data2.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, moth, day);
                picker2.show();
            }
        });
    }

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

    public void getItemID(){
        final ListView item = findViewById(R.id.lista);

        item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = ((CustomAdapter)item.getAdapter()).getItem(position).getName();
                Integer idItem = ((CustomAdapter)item.getAdapter()).getItem(position).getId();

                Intent i = new Intent(ItemActivity.this, getItemActivity.class);

                i.putExtra("name", name);
                i.putExtra("idItem", idItem);

                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

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

            case R.id.item:
                return true;

            case R.id.kits:
                Intent intent = new Intent(ItemActivity.this, KitActivity.class);
                startActivity(intent);

            case R.id.carrinho:
                return true;

            case R.id.reserva:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btnPesquisa(View view) {
        if (data1.getText().toString().equals("")){
            Toast.makeText(ItemActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        } else if (data2.getText().toString().equals("")){
            Toast.makeText(ItemActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ItemActivity.this, "Pesquisa com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
}
