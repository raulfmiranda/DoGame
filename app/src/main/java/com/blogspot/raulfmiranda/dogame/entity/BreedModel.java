package com.blogspot.raulfmiranda.dogame.entity;

import android.util.Log;

import com.blogspot.raulfmiranda.dogame.Util;
import com.blogspot.raulfmiranda.dogame.entity.remote.DogAPI;
import com.blogspot.raulfmiranda.dogame.viewdog.ViewDog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedModel implements ViewDog.Model, Callback<BreedResponse> {

    private String TAG = Util.Companion.getTAG();

    private ViewDog.Presenter presenter;
    private DogAPI.Companion dogAPI = DogAPI.Companion;

    public BreedModel(ViewDog.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestBreeds() {
        dogAPI.getAllBreeds(this);
    }

    @Override
    public void onResponse(Call<BreedResponse> call, Response<BreedResponse> response) {
        if (response.body() != null && response.body().getStatus().equals("success")) {
            presenter.setBreeds(response.body().getBreeds());
        }
    }

    @Override
    public void onFailure(Call<BreedResponse> call, Throwable t) {
        String erroMsg = t.getMessage();
        Log.d(TAG, erroMsg);
    }
}
