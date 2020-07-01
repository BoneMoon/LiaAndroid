package com.example.lia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.JsonPedidos;
import retrofit.RetrofitClientInstance;
import retrofit.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Main activity
 */
public class MainActivity extends AppCompatActivity {
    /**
     * SHARED_PREFS
     */
    public static final String SHARED_PREFS = "token";
    /**
     * EditText Email
     */
    EditText email;
    /**
     * EditText Password
     */
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPass);
    }

    /**
     * Btn login.
     * Este método serve para fazer login
     *
     * Para isso começamos por criar um novo utilizador com o email e a password
     * que o utilizador põe na atividade
     *
     * @param view the view
     */
    public void btnLogin(View view) {
        JsonPedidos service = RetrofitClientInstance.getRetrofitInstance().create(JsonPedidos.class);
        Users user = new Users(email.getText().toString(), password.getText().toString());
        Call<Users> usersCall = service.PostUsers(user);

        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.body() != null){
                    Users user = response.body();
                    Toast.makeText(MainActivity.this, "Login com sucesso!", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("userid", user.getId());
                    editor.putString("apitoken", user.getApi_token());
                    editor.putInt("grupoid", user.getGrupo_id());
                    editor.apply();
                    Intent i = new Intent(MainActivity.this, ItemActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
