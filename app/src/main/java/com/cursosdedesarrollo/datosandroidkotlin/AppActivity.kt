package com.cursosdedesarrollo.datosandroidkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu

open class AppActivity : AppCompatActivity() {


    lateinit var app: Aplicacion
    lateinit var datos: List<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as Aplicacion
        datos = app.listado
    }



}
