package com.example.myheroes.veiwmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myheroes.model.HeroResults;
import com.example.myheroes.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroViewModel extends AndroidViewModel {

    private RetrofitInstance retrofitInstance = new RetrofitInstance();

    private MutableLiveData<HeroResults> resultLiveData = new MutableLiveData<>();


    public HeroViewModel(@NonNull Application application) {

        super( application );

    }


    public void getHeroRepo() {

        retrofitInstance.getRepositories().enqueue( new Callback<HeroResults>() {

            @Override
            public void onResponse(Call<HeroResults> call, Response<HeroResults> response) {
                Log.d( "TAG", "onResponse: " );
                resultLiveData.setValue( response.body() );

            }

            @Override
            public void onFailure(Call<HeroResults> call, Throwable t) {

            }

        } );
    }

    public void getHeroRepo(String hero) {

        retrofitInstance.getRepositories( hero ).enqueue( new Callback<HeroResults>() {

            @Override
            public void onResponse(Call<HeroResults> call, Response<HeroResults> response) {

                resultLiveData.setValue( response.body() );

            }

            @Override
            public void onFailure(Call<HeroResults> call, Throwable t) {
            }

        } );
    }

    public MutableLiveData<HeroResults> getResultLiveData() {

        return resultLiveData;

    }
}
