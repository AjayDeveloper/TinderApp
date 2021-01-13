package com.example.tinderapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tinderapp.databinding.ActivityMainBinding
import com.example.tinderapp.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.acceptBtn.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            Toast.makeText(this, "Accepted", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
        binding.rejectBtn.setOnClickListener {
            Toast.makeText(this, "Rejected", Toast.LENGTH_LONG).show()

        }


    }
}