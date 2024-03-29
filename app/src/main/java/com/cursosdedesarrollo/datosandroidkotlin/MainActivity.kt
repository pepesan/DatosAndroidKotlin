package com.cursosdedesarrollo.datosandroidkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    private var cambia: Button? = null
    private var muestra: Button? = null
    lateinit var toolbar: Toolbar
    var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        muestra = findViewById<Button>(R.id.muestra)
        cambia = findViewById<Button>(R.id.cambia)
        setSupportActionBar(toolbar)
        sharedPref = getPreferences(Context.MODE_PRIVATE)
        muestra?.setOnClickListener {
            show(it)
        }
        cambia?.setOnClickListener {
            write(it)
        }
    }

    fun write(v: View){
        val editor = sharedPref?.edit()
        editor?.putString("user","pepesan")
                //commit lo hace inmediatamente
                //?.commit()
                //apply lo hace en segundo plano
                ?.apply()
    }
    fun show(v: View){
        val valor =  sharedPref?.getString("user","valor predefinido")
        val password =  sharedPref?.getString("password","valor predefinido")
        val pref_sync=sharedPref?.getBoolean("pref_sync",false)
        val pref_syncConnectionType=sharedPref?.getString("pref_syncConnectionType","valor predefinido")

        Toast.makeText(this@MainActivity, "User: $valor, Password:$password, Pref_Sync: $pref_sync, pref_syncConnectionType: $pref_syncConnectionType",Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {

            R.id.action_settings_activity -> {
                val intent = Intent(this,SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_preference_activity -> {
                val intent = Intent(this,CompatSettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_ficheros -> {
                val intent = Intent(this,FicherosActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_room -> {
                val intent = Intent(this,RoomActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_sqlite -> {
                val intent = Intent(this,ListadoActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSettings() {


    }
}
