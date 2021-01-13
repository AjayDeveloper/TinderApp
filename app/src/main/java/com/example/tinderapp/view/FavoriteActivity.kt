package com.example.tinderapp.view

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tinderapp.R
import com.example.tinderapp.adapter.FavoriteCustomAdapter
import com.example.tinderapp.databinding.ActivityFavoriteBinding
import com.example.tinderapp.databinding.ActivityMainBinding
import com.example.tinderapp.model.ServicesSetterGetter
import com.example.tinderapp.viewmodel.MainActivityViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var activityFavoriteMainBinding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFavoriteMainBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = activityFavoriteMainBinding.root
        setContentView(view)

    }

}