package com.blogspot.raulfmiranda.dogame.entity.remote

import com.blogspot.raulfmiranda.dogame.entity.BreedResponse
import com.blogspot.raulfmiranda.dogame.entity.DogResponse
import retrofit2.Call
import retrofit2.http.GET

interface DogResponseService {
    @GET("breeds/image/random")
    fun getRandomDogResponse(): Call<DogResponse>

    @GET("breeds/list/all")
    fun getAllBreeds(): Call<BreedResponse>
}