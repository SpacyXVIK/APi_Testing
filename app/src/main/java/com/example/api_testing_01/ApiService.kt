package com.example.api_testing_01

import com.example.api_testing_01.model.postproductsItem
import retrofit2.http.GET

interface ApiService {

    //https://api.restful-api.dev/objects

    //https://dummyjson.com/quotes

    @GET("products")
    suspend fun getProducts(): List<postproductsItem>


}