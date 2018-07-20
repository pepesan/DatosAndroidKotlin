package com.cursosdedesarrollo.datosandroidkotlin

import android.app.Application
import android.arch.persistence.room.Room


class Aplicacion : Application() {
    var database : AppDatabase? = null
    lateinit var  modelo: Modelo
    lateinit var  listado: List<Person>

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "sample-db").build()
        modelo = Modelo(this)
        modelo.loadList()
        listado= modelo.persons!!

    }




    operator fun get(id: Long?): Person {
        return modelo[id]
    }

    fun remove(p: Person) {
        modelo.remove(p)
    }

    fun add(p: Person) {
        modelo.add(p)
    }

    fun set(p: Person) {
        modelo.set(p)
    }


}