package com.example.tinderapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinderapp.model.ServicesSetterGetter
import com.example.tinderapp.repository.MainActivityRepository
import com.example.tinderapp.view.MainActivity

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<ServicesSetterGetter>? = null

     var context = this@MainActivityViewModel

    fun getUser() : LiveData<ServicesSetterGetter>? {
        servicesLiveData = MainActivityRepository.getServicesApiCall()
        return servicesLiveData
    }

    fun insertData(context: Context, id : Int? ,name: String?, profilePic: String?, age: Int?, distance: Int?) {
        if (id != null) {
            if (name != null) {
                if (profilePic != null) {
                    if (age != null) {
                        if (distance != null) {
                            MainActivityRepository.insertData(context, id, name, profilePic,age,distance)
                        }
                    }
                }
            }
        }

    }

}