package com.example.tinderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    @PrimaryKey
    val id: Int,
    val name: String,
    val profile_pic: String,
    val age: Int,
    val distance: Int,
)