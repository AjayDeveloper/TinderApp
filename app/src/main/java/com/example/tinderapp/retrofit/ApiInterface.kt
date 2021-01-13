package com.example.tinderapp.retrofit

import com.example.tinderapp.model.Profile
import com.example.tinderapp.model.ServicesSetterGetter
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
interface ApiInterface {
    @GET("services")
    fun getServices(): Call<ServicesSetterGetter>

    @GET("profiles")
    fun getProfiles(): Call<List<Profile>>

    companion object {
        operator fun invoke(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl("https://api.simplifiedcoding.in/course-apis/tinder/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}