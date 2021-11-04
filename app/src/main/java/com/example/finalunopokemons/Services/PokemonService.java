package com.example.finalunopokemons.Services;

import com.example.finalunopokemons.Clases.Entrenador;
import com.example.finalunopokemons.Clases.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonService {
    @GET("N00022599")
    Call<List<Pokemon>> allPokemons();

    @GET("id")
    Call<Pokemon> GetPokemon(@Query("id") String id);
}
