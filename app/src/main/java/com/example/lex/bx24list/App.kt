package com.example.lex.bx24list

import android.app.Application
import android.arch.persistence.room.Room

class App : Application() {

    lateinit var database: DataDatabase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, DataDatabase::class.java, "gallery").build()
    }
}