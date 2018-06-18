package com.blogspot.raulfmiranda.dogame.entity.remote

import com.blogspot.raulfmiranda.dogame.entity.DogResponse
import retrofit2.Call
import retrofit2.http.GET

interface DogResponseService {
    @GET("breeds/image/random")
    fun getRandomDogResponse(): Call<DogResponse>
}