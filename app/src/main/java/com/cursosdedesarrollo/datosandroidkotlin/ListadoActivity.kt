package com.cursosdedesarrollo.datosandroidkotlin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_listado.*
import kotlinx.android.synthetic.main.content_listado.*

class ListadoActivity : AppCompatActivity() {
    lateinit var adapter: TestListAdapter
    private var primera: Boolean? = true
    lateinit var app: Aplicacion
    lateinit var datos: List<Person>
    lateinit var modelo: Modelo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        */
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        app = application as Aplicacion
        modelo = app.modelo
        //esto podría colocarse en un ASyncTask
        modelo.loadList()

        datos = app.listado
        Log.d("app:ListadoActivity","Listado:${datos}")
        adapter = TestListAdapter(this,
                R.layout.item, datos)
        if (datos.size <= 0) {

            empty.setVisibility(View.VISIBLE)

            list.setVisibility(View.GONE)
        }
        list.setAdapter(adapter)
        list.setTextFilterEnabled(true)
        list.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,
                    Mostrar::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        })
    }

    fun goAdd(v: View?) {
        val intent = Intent(this, AddEdit::class.java)
        this.startActivity(intent)
    }

    public override fun onResume() {
        super.onResume()
        if (primera == true) {
            primera = false
        } else {
            if (datos.size > 0) {
                empty.setVisibility(View.GONE)
                list.setVisibility(View.VISIBLE)
            } else {
                empty.setVisibility(View.VISIBLE)
                list.setVisibility(View.GONE)
            }
            adapter.listado=modelo.persons
            adapter.forceReload()
            Log.d("app:ListadoActivity","Listado:${datos}")
        }
    }

    var back: Int? = 0
    override fun onBackPressed() {
        if (back == 0) {
            back = 1
            Toast.makeText(this, "Pulsa dos veces volver para salir",
                    Toast.LENGTH_LONG).show()
        } else {
            super.onBackPressed()
            //finish();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.menu_add -> {
                goAdd(null)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}