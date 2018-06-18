package com.blogspot.raulfmiranda.dogame.entity.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer(baseUrl: String) {
    private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun dogResponseService() = retrofit.create(DogResponseService::class.java)
}