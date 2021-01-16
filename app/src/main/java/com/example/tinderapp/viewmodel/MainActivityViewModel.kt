package com.example.tinderapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinderapp.model.Profile
import com.example.tinderapp.model.ServicesSetterGetter
import com.example.tinderapp.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<ServicesSetterGetter>? = null

    fun getUser() : LiveData<ServicesSetterGetter>? {
        servicesLiveData = MainActivityRepository.getServicesApiCall()
        return servicesLiveData
    }
     var context = this@MainActivityViewModel



    fun insertData(context: Context,name: String?, profilePic: String?, age: String?, distance: String?) {

            if (name != null) {
                if (profilePic != null) {
                    if (age != null) {
                        if (distance != null) {
                            MainActivityRepository.insertData(context,name, profilePic,age,distance)
                        }
                    }
                }
            }


    }


    fun getListData(context:Context): List<Profile> {
        return MainActivityRepository.getDbData(context)
    }

}