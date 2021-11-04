package com.example.finalunopokemons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.finalunopokemons.Adapters.PokemonAdapter;
import com.example.finalunopokemons.Clases.Pokemon;
import com.example.finalunopokemons.Services.PokemonService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerPokemonsActivity extends AppCompatActivity {
    ImageView imagenPokemon;
    //Button btnVerDetallePokemon;
   // ImageButton btnImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pokemons);

        imagenPokemon = findViewById(R.id.imgPokemon);


        RecyclerView rv =findViewById(R.id.rvPalabra);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/pokemons/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService service = retrofit.create(PokemonService.class);

        service.allPokemons().enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                PokemonAdapter adapter = new PokemonAdapter(response.body());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

            }
        });



//        btnVerDetallePokemon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(getApplicationContext(), DetallePokemonActivity.class);
//                startActivity(intent);
//            }
//        });

    }
}