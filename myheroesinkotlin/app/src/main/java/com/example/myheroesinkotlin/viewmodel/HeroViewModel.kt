package com.example.myheroesinkotlin.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myheroesinkotlin.model.HeroResults
import com.example.myheroesinkotlin.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroViewModel (application: Application) : AndroidViewModel(application){
    private var retrofitInstance = RetrofitInstance()
    private var resultLiveData = MutableLiveData<HeroResults>()


    fun getHeroRepo(){
        retrofitInstance.getRepositories()?.enqueue(object : Callback<HeroResults?> {
            override fun onResponse(
                call: Call<HeroResults?>,
                response: Response<HeroResults?>
            ) {
                Log.d("TAG", "onResponse: ")
                resultLiveData.value = response.body()
            }

            override fun onFailure(
                call: Call<HeroResults?>,
                t: Throwable
            ) {
            }
        })

    }


    fun getHeroRepo(hero: String) {
        retrofitInstance.getRepositories(hero)?.enqueue(object : Callback<HeroResults?> {
            override fun onResponse(
                call: Call<HeroResults?>,
                response: Response<HeroResults?>
            ) {
                resultLiveData.value = response.body()
            }

            override fun onFailure(
                call: Call<HeroResults?>,
                t: Throwable
            ) {
            }
        })
    }

    fun getResultLiveData () : MutableLiveData<HeroResults>{
        return resultLiveData
    }

}