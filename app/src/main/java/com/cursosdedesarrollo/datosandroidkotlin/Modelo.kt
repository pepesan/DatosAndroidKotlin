package com.cursosdedesarrollo.datosandroidkotlin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.util.ArrayList


class Modelo internal constructor(aplicacion: Aplicacion) {
    private val db: SQLiteDatabase
    lateinit var listado: MutableList<Person>
    val persons: List<Person>?
        get() = listado

    init {
        //Esto podría paralelizarse
        val helper = PersonSqliteHelper(aplicacion)

        db = helper.writableDatabase
        listado = ArrayList()

    }

    fun loadList() {
        listado = ArrayList()
        val personCursor = db.query(PersonSqliteHelper.PERSON_TABLE,
                    arrayOf(
                        PersonSqliteHelper.PERSON_ID,
                        PersonSqliteHelper.PERSON_NAME,
                        PersonSqliteHelper.PERSON_TLF,
                        PersonSqliteHelper.PERSON_GLOBAL,
                        PersonSqliteHelper.PERSON_PHOTOPATH,
                        PersonSqliteHelper.PERSON_THUMBPHOTOPATH,
                        PersonSqliteHelper.PERSON_LOCATION),
            null, null, null, null,
                //String.format("%s,%s",PERSON_GLOBAL, PERSON_NAME )
                String.format("%s",
                        PersonSqliteHelper.PERSON_GLOBAL))
        personCursor.moveToFirst()
        var p: Person
        if (!personCursor.isAfterLast) {
            do {
                val id = personCursor.getLong(0)
                val name = personCursor.getString(1)
                val tlf = personCursor.getString(2)
                val global = personCursor.getFloat(3)
                val filename = personCursor.getString(4)
                val thumbfilename = personCursor.getString(5)
                val location = personCursor.getString(6)
                p = Person(id, name, tlf, global)
                p.photopath = filename
                p.thumbphotopath = thumbfilename
                p.location = location
                listado!!.add(p)
            } while (personCursor.moveToNext())
        }

        personCursor.close()

    }

    fun add(p: Person?) {
        assert(null != p)

        val values = ContentValues()
        values.put(PersonSqliteHelper.PERSON_NAME, p!!.name)
        values.put(PersonSqliteHelper.PERSON_TLF, p.tlf)
        values.put(PersonSqliteHelper.PERSON_GLOBAL, p.globalpoints)
        values.put(PersonSqliteHelper.PERSON_PHOTOPATH, p.photopath)
        values.put(PersonSqliteHelper.PERSON_THUMBPHOTOPATH, p.thumbphotopath)
        values.put(PersonSqliteHelper.PERSON_LOCATION, p.location)
        values.put(PersonSqliteHelper.PERSON_LATITUDE, p.latitude)
        values.put(PersonSqliteHelper.PERSON_LONGITUDE, p.longitude)

        p.id = db.insert(PersonSqliteHelper.PERSON_TABLE, null, values)
        Log.d("app",p.toString())
        listado!!.add(p)

    }

    @SuppressLint("DefaultLocale")
    fun set(p: Person?) {
        assert(null != p)
        val values = ContentValues()
        values.put(PersonSqliteHelper.PERSON_NAME, p!!.name)
        values.put(PersonSqliteHelper.PERSON_TLF, p.tlf)
        values.put(PersonSqliteHelper.PERSON_GLOBAL, p.globalpoints)
        values.put(PersonSqliteHelper.PERSON_PHOTOPATH, p.photopath)
        values.put(PersonSqliteHelper.PERSON_THUMBPHOTOPATH, p.thumbphotopath)
        values.put(PersonSqliteHelper.PERSON_LOCATION, p.location)
        values.put(PersonSqliteHelper.PERSON_LATITUDE, p.latitude)
        values.put(PersonSqliteHelper.PERSON_LONGITUDE, p.longitude)
        val id = p.id!!
        Log.d("id", "" + id)
        val where = String.format("%s = %d",
                PersonSqliteHelper.PERSON_ID, id)

        db.update(PersonSqliteHelper.PERSON_TABLE, values, where, null)
        val tam = listado!!.size
        for (i in 0 until tam) {
            val p2 = listado!![i]
            if (p.id === p2.id) {
                listado!![i] = p
                break
            }
        }
    }

    fun remove(p: Person) {
        val where = String.format("%s = %s",
                PersonSqliteHelper.PERSON_ID,
                p.id)
        db.delete(PersonSqliteHelper.PERSON_TABLE, where, null)
        listado!!.remove(p)
    }

    operator fun get(id: Long?): Person {
        val count = listado!!.size
        for (i in 0 until count) {
            val p = listado!![i]
            if (p.id === id) {
                return p
            }
        }
        return Person()
    }

    fun removePersonByName(s: String) {
        //TODO: quitar persona por nombre
        //listado.remove(s);
    }
}
