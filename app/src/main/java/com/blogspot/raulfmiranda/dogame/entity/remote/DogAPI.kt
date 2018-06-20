package com.blogspot.raulfmiranda.dogame.entity.remote

import android.util.Log
import com.blogspot.raulfmiranda.dogame.Util
import com.blogspot.raulfmiranda.dogame.entity.DogResponse
import retrofit2.Callback

class DogAPI {
//    API Reference:
//    https://dog.ceo/api/breed/hound/list

    companion object {
        private val baseUrl = "https://dog.ceo/api/"
        private val TAG = Util.TAG

//      https://dog.ceo/api/breeds/image/random
        fun getRandomDogResponse(callback: Callback<DogResponse?>) {
            try {
                val call = RetrofitInitializer(baseUrl).dogResponseService().getRandomDogResponse()
                call.enqueue(callback)

            } catch (e: Exception) {
                Log.d(TAG, e.message)
                e.printStackTrace()
            }
        }
    }

}