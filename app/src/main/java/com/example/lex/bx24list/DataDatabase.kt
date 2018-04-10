package com.example.lex.bx24list

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Data::class], version = 1)
abstract class DataDatabase : RoomDatabase() {

    abstract fun DataDao(): DataDao
}