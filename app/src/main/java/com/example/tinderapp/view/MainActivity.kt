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
import com.example.tinderapp.utils.ConnectionType
import com.example.tinderapp.utils.NetworkMonitorUtil
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

    private lateinit var listData: Response<List<Profile>>
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = FavoriteCustomAdapter()
    private lateinit var layoutManager: CardStackLayoutManager
    private var swapDrirectionToRight = false
    private val networkMonitor = NetworkMonitorUtil(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        Fresco.initialize(this)
        setContentView(view)
        networkcheck()

        layoutManager = CardStackLayoutManager(this, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }
        layoutManager.setCanScrollHorizontal(true)
        layoutManager.setCanScrollVertical(false)

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
                    listData = response
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

    private fun networkcheck() {
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Log.i("NETWORK_MONITOR_STATUS", "Wifi Connection")
                            }
                            ConnectionType.Cellular -> {
                                Log.i("NETWORK_MONITOR_STATUS", "Cellular Connection")
                            }
                            else -> { }
                        }
                    }
                    false -> {
                        Log.i("NETWORK_MONITOR_STATUS", "No Connection")
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }
    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.d("onCardDragging", "onCardDragging")
        if (direction != null)
        {
            if (direction.name == "Left")
                swapDrirectionToRight = false
            else
                swapDrirectionToRight = true
        }
    }

    override fun onCardSwiped(direction: Direction?) {
        Log.d("onCardDragging", "onCardDragging")

    }

    override fun onCardRewound() {
        Log.d("onCardDragging", "onCardDragging")
    }

    override fun onCardCanceled() {
        Log.d("onCardDragging", "onCardDragging")
    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        if (swapDrirectionToRight)
        {
            var data = listData.body()?.get(position)
            var id = data?.id
            var name = data?.name
            var profile_pic = data?.profile_pic
            var age = data?.age
            var distance  = data?.distance
            mainActivityViewModel.insertData(this, id,name, profile_pic,age,distance)



            Toast.makeText(this, "Right Shift Insert Data", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Left Shift Dis Liked", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}