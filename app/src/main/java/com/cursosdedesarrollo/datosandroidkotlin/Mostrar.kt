package com.cursosdedesarrollo.datosandroidkotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Mostrar : AppActivity() {

    private var id: Long? = null
    private var primera = true
    private var tv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val extras = intent.extras
        id = extras!!.getLong("id")
        tv = findViewById(R.id.mostrar_tv) as TextView
        tv!!.text = app[id].name
    }

    fun edit(v: View?) {
        val intent = Intent(this,
                AddEdit::class.java)
        intent.putExtra("id", id)
        this.startActivity(intent)
    }

    public override fun onResume() {
        super.onResume()
        if (primera == true) {
            primera = false
        } else {
            tv!!.text = app[id as Long].name
        }
    }

    fun delete(v: View?) {
        app.remove(app[id as Long])
        Toast.makeText(this, R.string.deleted, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Saliendo del Mostrar",
                Toast.LENGTH_SHORT).show()
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_mostrar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.menu_edit -> {
                edit(null)
                return true
            }
            R.id.menu_delete -> {
                delete(null)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
