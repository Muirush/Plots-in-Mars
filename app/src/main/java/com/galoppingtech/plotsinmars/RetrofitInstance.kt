package com.galoppingtech.plotsinmars


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api :estateAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://mars.udacity.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(estateAPI::class.java)
    }
}