package com.blogspot.raulfmiranda.dogame.entity;

import android.util.Log;

import com.blogspot.raulfmiranda.dogame.ProjectConstants;
import com.blogspot.raulfmiranda.dogame.entity.remote.DogAPI;
import com.blogspot.raulfmiranda.dogame.quiz.Quiz;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogameModel implements Callback<DogResponse> {

    private String TAG = ProjectConstants.Companion.getTAG();
    private DogAPI.Companion dogAPI = DogAPI.Companion;
    private Quiz.Presenter presenter;

    public DogameModel(Quiz.Presenter presenter) {
        this.presenter = presenter;
    }

    public void getRandomDog() {
        dogAPI.getRandomDogResponse(this);
    }

    // Dog API onResponse
    @Override
    public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {

        if(response.body() != null && response.body().getStatus().equals("success")) {
            DogResponse dogResponse = new DogResponse(response.body().getStatus(), response.body().getImageUrl());
            Dog dog = dogResponse.toDog();
            presenter.randomDogSuccessfullyRetrieved(dog);
        }
    }

    // Dog API onFailure
    @Override
    public void onFailure(Call<DogResponse> call, Throwable t) {
        String erroMsg = t.getMessage();
        Log.d(TAG, erroMsg);
    }
}
