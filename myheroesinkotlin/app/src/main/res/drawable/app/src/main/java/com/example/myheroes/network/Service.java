package com.example.myheroes.network;

import com.example.myheroes.model.HeroResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("/")
    Call<HeroResults> getHeroes(
            @Query( "hero" ) String hero,
            @Query( "rapidapi-key" ) String apiKey
    );
}
