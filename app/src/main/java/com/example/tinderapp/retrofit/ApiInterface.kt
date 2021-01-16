package com.example.tinderapp.retrofit

import com.example.tinderapp.model.Profile
import com.example.tinderapp.model.ServicesSetterGetter
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiInterface {
    @GET("?results=10")
    fun getServices(): Call<ServicesSetterGetter>

}