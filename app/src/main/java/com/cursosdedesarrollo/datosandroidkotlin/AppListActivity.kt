package com.cursosdedesarrollo.datosandroidkotlin

import android.app.ListActivity
import android.os.Bundle
import android.view.Menu

open class AppListActivity : ListActivity() {

    lateinit var app: Aplicacion
    lateinit var datos: List<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as Aplicacion
        datos = app.listado
    }

}