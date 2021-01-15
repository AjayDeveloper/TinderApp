package com.example.tinderapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import com.example.tinderapp.database.AppDb
import com.example.tinderapp.model.Profile
import com.example.tinderapp.model.ServicesSetterGetter
import com.example.tinderapp.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    private val serviceSetterGetter = MutableLiveData<ServicesSetterGetter>()
    private var appDb : AppDb? = null
    private var mainViewModel: LiveData<Profile>? = null

    fun getServicesApiCall(): MutableLiveData<ServicesSetterGetter> {

        val call = RetrofitClient.apiInterface.getServices()

        call.enqueue(object: Callback<ServicesSetterGetter> {
            override fun onFailure(call: Call<ServicesSetterGetter>, t: Throwable) {

                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<ServicesSetterGetter>,
                response: Response<ServicesSetterGetter>
            ) {

                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                val msg = data!!.message

                serviceSetterGetter.value = ServicesSetterGetter(msg)
            }
        })

        return serviceSetterGetter
    }


    fun initializeDB(context: Context) : AppDb {
        return AppDb.getDatabase(context)!!
    }


    fun insertData(context: Context, id : Int,name: String, profilePic: String, age: Int, distance: Int) {
        appDb = initializeDB(context)
        CoroutineScope(IO).launch {
            val profileDetails = Profile(id,name, profilePic,age,distance)
            appDb!!.profileDao().saveProfile(profileDetails)
        }
    }

}