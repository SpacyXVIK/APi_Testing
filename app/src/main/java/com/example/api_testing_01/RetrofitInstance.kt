package com.example.api_testing_01

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    //https://api.restful-api.dev/objects

    //https://dummyjson.com/quotes

    private fun getInstance(): Retrofit {


        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun getApiService():ApiService{
        return getInstance().create(ApiService::class.java)
    }
}