package com.example.tinderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tinderapp.R
import com.example.tinderapp.model.Profile

class WishlistAdapter(val items: List<Profile>, val context: Context) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_whishlist_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items.get(position).name
        holder.age.text = items.get(position).age
        holder.city.text = items.get(position).distance
        Glide.with(context).load(items.get(position).profile_pic).into(holder.profile_pic)

    }


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val name : TextView = view.findViewById(R.id.name)
        var profile_pic : ImageView = view.findViewById(R.id.profile_pic)
        val age : TextView   = view.findViewById(R.id.age)
        val city : TextView  = view.findViewById(R.id.city)
    }

}
