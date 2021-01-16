package com.example.tinderapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tinderapp.databinding.ActivityFavoriteBinding
import com.example.tinderapp.viewmodel.MainActivityViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var activityFavoriteMainBinding: ActivityFavoriteBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       activityFavoriteMainBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = activityFavoriteMainBinding.root
        setContentView(view)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


    }


}