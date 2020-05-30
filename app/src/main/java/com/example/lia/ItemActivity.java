package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import retrofit.JsonPedidos;
import retrofit.RetrofitClientInstance;
import retrofit.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lia.MainActivity.SHARED_PREFS;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
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
