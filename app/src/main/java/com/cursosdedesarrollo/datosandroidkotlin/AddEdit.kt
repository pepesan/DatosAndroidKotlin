package com.cursosdedesarrollo.datosandroidkotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast

class AddEdit : AppActivity() {

    private var editcadena: EditText? = null
    private var id: Long? = null
    private var p: Person? = null
    private var edit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        try {
            val extras = intent.extras
            id = extras!!.getLong("id")
            p = app[id]
            edit = true
        } catch (e: Exception) {
            edit = false
        }

        setUpviews()
    }

    private fun setUpviews() {
        editcadena = findViewById(R.id.cadena) as EditText
        if (edit == true) {
            editcadena!!.setText(p!!.name)
        }
    }

    override fun onBackPressed() {
        this.save(View(this))
    }

    fun save(v: View?) {
        val s = editcadena!!.text.toString()
        val p = Person()
        p.name = s
        if (edit == false) {
            app.add(p)
            Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT)
                    .show()
        } else {
            p.id = id
            app.set(p)
            Toast.makeText(this, R.string.modified, Toast.LENGTH_SHORT)
                    .show()
        }

        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_add_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.menu_save -> {
                save(null)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
