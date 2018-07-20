package com.cursosdedesarrollo.datosandroidkotlin

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TestListAdapter( val context: Context,
                       val vista: Int,
                       var listado: List<Person> = ArrayList()) : BaseAdapter() {


    override fun getCount(): Int {
        return listado.size
    }

    override fun getItem(position: Int): Person? {
        return listado[position]
    }

    override fun getItemId(position: Int): Long {
        return this.getItem(position)!!.id!!
    }

    override fun getView(position: Int, convertView: View?,
                         parent: ViewGroup): View {
        //Log.d("getview",""+position);
        val tli: TextView
        if (null == convertView) {
            tli = View.inflate(context,
                    vista, null) as TextView
        } else {
            tli = convertView as TextView
        }
        tli.text = listado[position].name
        Log.d("Presentando", "" + position)
        return tli
    }

    fun forceReload() {
        notifyDataSetChanged()
    }


}
