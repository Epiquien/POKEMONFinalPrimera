package com.example.finalunopokemons.Services;

import com.example.finalunopokemons.Clases.Entrenador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EntrenadorService {

    @POST("N00022599")
    Call<Entrenador> createEntrenador(@Body Entrenador entrenador);

    @GET("N00022599")
    Call<Entrenador> getEntrenador();

}
