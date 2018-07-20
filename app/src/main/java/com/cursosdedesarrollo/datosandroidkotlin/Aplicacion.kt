package com.cursosdedesarrollo.datosandroidkotlin

import android.app.Application
import android.arch.persistence.room.Room
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Aplicacion : Application() {
    var database : AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "sample-db").build()
    }
}