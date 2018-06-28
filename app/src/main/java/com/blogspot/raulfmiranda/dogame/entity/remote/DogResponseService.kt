package com.blogspot.raulfmiranda.dogame.entity.remote

import com.blogspot.raulfmiranda.dogame.entity.BreedResponse
import com.blogspot.raulfmiranda.dogame.entity.DogResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogResponseService {
    @GET("breeds/image/random")
    fun getRandomDogResponse(): Call<DogResponse>

    @GET("breeds/list/all")
    fun getAllBreeds(): Call<BreedResponse>

    @GET("breed/{breed}/images/random")
    fun getRandomDogResponseByBreed(@Path("breed") breed: String): Call<DogResponse>

    @GET("breed/{breed}/{subreed}/images/random")
    fun getRandomDogResponseBySubBreed(@Path("breed") breed: String, @Path("subreed") subreed: String): Call<DogResponse>
}