package com.example.myheroesinkotlin.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

import com.example.myheroesinkotlin.model.HeroResults

interface Service {

    @GET("/")
    fun getHeroes(
        @Query("hero") hero: String?,
        @Query("rapidapi-key") apiKey: String?
    ): Call<HeroResults?>?
}