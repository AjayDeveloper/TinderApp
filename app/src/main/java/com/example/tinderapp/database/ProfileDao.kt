package com.example.tinderapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tinderapp.model.Profile

@Dao
interface ProfileDao {
    @Insert
    fun saveProfile(profile: Profile)

    @Query(value = "Select * from Profile")
    fun getAllProfile() : List<Profile>
}