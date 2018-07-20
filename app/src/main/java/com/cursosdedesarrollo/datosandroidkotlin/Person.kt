package com.cursosdedesarrollo.datosandroidkotlin

import android.location.Address
import java.io.Serializable


class Person : Serializable {
    /**
     *
     */
    var id: Long? = null
    lateinit var name: String
    var tlf: String? = null
    var globalpoints: Float? = null
    var photopath: String? = null
    var thumbphotopath: String? = null
    var location: String? = null
    var latitude: Double = 0.toDouble()
    var longitude: Double = 0.toDouble()

    var valorations: List<Valoration>? = null

    constructor() {
        id = 0.toLong()
        name = ""
        tlf = ""
        globalpoints = 0.0f
    }


    constructor(id: Long?, name: String, tlf: String, globalpoints: Float?) {
        this.id = id
        this.name = name
        this.tlf = tlf
        this.globalpoints = globalpoints
    }

    fun setAddressLocation(a: Address?) {
        if (null == a) {
            location = null
            longitude = 0.0
            latitude = longitude
        } else {
            val maxAddressLine = a.maxAddressLineIndex
            val sb = StringBuffer("")
            for (i in 0 until maxAddressLine) {
                sb.append(a.getAddressLine(i) + " ")
            }
            location = sb.toString()
            latitude = a.latitude
            longitude = a.longitude
        }
    }


    override fun toString(): String {
        return name
    }

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 7402097807653944314L
    }


}
