package com.example.tinderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val name: String,
    val profile_pic: String,
    val age: String,
    val distance: String,
)
{
    constructor(name: String,profile_pic: String,age: String,distance: String) : this(0,name,profile_pic,age,distance)

}