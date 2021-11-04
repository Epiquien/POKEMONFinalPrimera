package com.example.finalunopokemons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalunopokemons.Clases.Pokemon;
import com.example.finalunopokemons.Services.EntrenadorService;
import com.example.finalunopokemons.Services.PokemonService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class DetallePokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pokemon);
        Pokemon pokemon = new Pokemon();
        TextView tvNombreDetalle = findViewById(R.id.tvNombreDetalle);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/entrenador/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService service =retrofit.create(PokemonService.class);

        service.GetPokemon("id").enqueue(new Callback<Pokemon>() {
        @Override
        public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
            tvNombreDetalle.setText(pokemon.getNombre());
            response.body();

        }

        @Override
        public void onFailure(Call<Pokemon> call, Throwable t) {

        }
    });


        Button btnUbicaciones = findViewById(R.id.btnVerUbicaciones);

        btnUbicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}