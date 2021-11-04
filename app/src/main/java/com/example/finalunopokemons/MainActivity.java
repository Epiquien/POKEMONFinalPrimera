package com.example.finalunopokemons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalunopokemons.Clases.Entrenador;
import com.example.finalunopokemons.Services.EntrenadorService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView nombreEntrenador;
    TextView puebloEntrenador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegistroEntrenador = findViewById(R.id.btnRegistraEntrenador);
        Button btnVerPokemons = findViewById(R.id.btnVerPokemons);

        nombreEntrenador = findViewById(R.id.tvNombreEntrenador);
        puebloEntrenador = findViewById(R.id.tvPuebloEntrenador);

        Entrenador entrenador = new Entrenador();


        btnRegistroEntrenador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), RegistroEntrenadorActivity.class);
                startActivity(intent);
            }
        });


        btnVerPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), VerPokemonsActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/entrenador/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EntrenadorService service =retrofit.create(EntrenadorService.class);

       service.getEntrenador().enqueue(new Callback<Entrenador>() {
           @Override
           public void onResponse(Call<Entrenador> call, Response<Entrenador> response) {
               nombreEntrenador.setText(entrenador.getNombres());
               puebloEntrenador.setText(entrenador.getPueblo());
                response.body();
           }

           @Override
           public void onFailure(Call<Entrenador> call, Throwable t) {

           }
       });

    }


}