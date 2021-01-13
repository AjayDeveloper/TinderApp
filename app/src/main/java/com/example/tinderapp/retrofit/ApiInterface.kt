package com.example.tinderapp.retrofit

import com.example.tinderapp.model.ServicesSetterGetter
import retrofit2.Call
import retrofit2.http.GET
interface ApiInterface {
    @GET("services")
    fun getServices() : Call<ServicesSetterGetter>
}