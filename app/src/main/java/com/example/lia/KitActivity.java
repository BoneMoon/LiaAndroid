package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kit);

        getKit();
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
                    CustomAdapterKit kits_adapter = new CustomAdapterKit(getApplicationContext(), kits.getKitsatrobutos());
                    ((ListView) findViewById(R.id.lista)).setAdapter(kits_adapter);
                }else{
                    Toast.makeText(KitActivity.this, "Não há itens!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KitsAtributos> call, Throwable t) {
                Toast.makeText(KitActivity.this, "Fail", Toast.LENGTH_SHORT).show();
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
}
