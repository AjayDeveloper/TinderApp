package com.example.tinderapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tinderapp.R
import com.example.tinderapp.databinding.CardViewProfileBinding
import com.example.tinderapp.model.Profile
import com.example.tinderapp.model.ServicesSetterGetter


class FavoriteCustomAdapter : RecyclerView.Adapter<FavoriteCustomAdapter.ViewHolder>() {

    private var profiles: List<ServicesSetterGetter.Result>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.card_view_profile,
            parent,
            false
        )
    )

    override fun getItemCount() = profiles?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        profiles?.let {
            holder.binding.result= it.get(position)
            holder.binding.executePendingBindings()
        }
    }

    fun setProfiles(profiles: List<ServicesSetterGetter.Result>) {
        this.profiles = profiles
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: CardViewProfileBinding) :
        RecyclerView.ViewHolder(binding.root)

}
