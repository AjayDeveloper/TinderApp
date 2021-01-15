package com.example.tinderapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tinderapp.adapter.FavoriteCustomAdapter
import com.example.tinderapp.databinding.ActivityFavoriteBinding
import com.example.tinderapp.viewmodel.MainActivityViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var activityFavoriteMainBinding: ActivityFavoriteBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var mfavorite: FavoriteCustomAdapter? = null
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFavoriteMainBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = activityFavoriteMainBinding.root
        setContentView(view)
        mainActivityViewModel = ViewModelProvider(this).get(mainActivityViewModel::class.java)
        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
        recyclerView = activityFavoriteMainBinding.recyclerView
        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mfavorite
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.getOrientation()
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

    }

}