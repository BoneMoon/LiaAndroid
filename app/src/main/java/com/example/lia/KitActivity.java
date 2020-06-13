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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;

import adapter.CustomAdapter;
import adapter.CustomAdapterKit;
import retrofit.ItemAtributo;
import retrofit.JsonPedidos;
import retrofit.Kit;
import retrofit.KitsAtributos;
import retrofit.RetrofitClientInstance;
import retrofit.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

public class KitActivity extends AppCompatActivity {
    KitsAtributos kits;
    DatePickerDialog picker;
    DatePickerDialog picker2;
    EditText data1;
    EditText data2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kit);

        getKit();
        getKitId();

        data1 = findViewById(R.id.dataPicker1);
        data2 = findViewById(R.id.dataPicker2);

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

                picker2 = new DatePickerDialog(KitActivity.this,
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

    public void getKitId(){
        final ListView kit = findViewById(R.id.listaKit);

        kit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String nome = ((CustomAdapterKit)kit.getAdapter()).getItem(position).getName();
                int idK = ((CustomAdapterKit)kit.getAdapter()).getItem(position).getId();
                //Log.i("tag", idK);

                Intent i = new Intent(KitActivity.this, getKitActivity.class);

                i.putExtra("nome", nome);
                i.putExtra("idK", idK);

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

            case R.id.kits:
                return true;

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
            Toast.makeText(KitActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        } else if (data2.getText().toString().equals("")){
            Toast.makeText(KitActivity.this, "As datas têm que estar preenchidas!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(KitActivity.this, "Pesquisa com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
}
