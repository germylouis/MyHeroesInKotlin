package com.example.myheroesinkotlin.network

import com.example.myheroesinkotlin.model.HeroResults
import com.example.myheroesinkotlin.util.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance() {


    val test = Constants.BASE_URL
    private var service: Service

    init {
        service = createService(getInstance())
    }

    private fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    fun getRepositories(): Call<HeroResults?>? {

        return service.getHeroes("Spiderman", Constants.KEY)
    }

    fun getRepositories(hero: String): Call<HeroResults?>? {
        return service.getHeroes(hero, Constants.KEY)
    }

}