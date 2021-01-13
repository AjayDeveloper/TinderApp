package com.example.tinderapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.tinderapp.adapter.FavoriteCustomAdapter
import com.example.tinderapp.databinding.ActivityMainBinding
import com.example.tinderapp.model.Profile
import com.example.tinderapp.retrofit.ApiInterface
import com.example.tinderapp.viewmodel.MainActivityViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), CardStackListener {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = FavoriteCustomAdapter()
    private lateinit var layoutManager: CardStackLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        Fresco.initialize(this)
        setContentView(view)

        layoutManager = CardStackLayoutManager(this, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        binding.stackView.layoutManager = layoutManager
        binding.stackView.adapter = adapter
        binding.stackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

        ApiInterface().getProfiles().enqueue(object : Callback<List<Profile>> {
            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                response.body()?.let {
                    Log.d("test",response.body().toString())
                    adapter.setProfiles(it)
                }
            }
        })

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.acceptBtn.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Accepted", Toast.LENGTH_LONG).show()

        }
        binding.rejectBtn.setOnClickListener {
            Toast.makeText(this, "Rejected", Toast.LENGTH_LONG).show()

        }


    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }


}