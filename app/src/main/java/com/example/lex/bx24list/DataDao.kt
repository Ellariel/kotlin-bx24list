package com.example.lex.bx24list

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface DataDao {

    @Query("SELECT * FROM Data")
    fun getAll(): List<Data>

    @Insert
    fun insertAll(photos: List<Data>)
}