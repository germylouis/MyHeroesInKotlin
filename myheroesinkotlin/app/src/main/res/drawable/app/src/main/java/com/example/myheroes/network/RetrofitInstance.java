package com.example.myheroes.network;

import android.util.Log;

import com.example.myheroes.model.HeroResults;
import com.example.myheroes.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private final String BASE_URL = Constants.BASE_URL;
    private Service service;

    public RetrofitInstance() {
        service = createService( getInstance() );
    }


    private Retrofit getInstance() {

        return new Retrofit
                .Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

    }

    private Service createService(Retrofit retrofit) {

        return retrofit.create( Service.class );

    }

    public Call<HeroResults> getRepositories() {

        Log.d( "tag", "getRepositories: " + service.getHeroes( "Spiderman", Constants.KEY ) );

        return service.getHeroes( "Spiderman", Constants.KEY );

    }

    public Call<HeroResults> getRepositories(String hero) {

        Log.d( "tag", "getRepositories: " + service.getHeroes( "Spiderman", Constants.KEY ) );

        return service.getHeroes( hero, Constants.KEY );

    }

    public Call<HeroResults> getHeroRepo(String movie, String key) {

        return service.getHeroes( movie, key );

    }

}
