package com.cursosdedesarrollo.datosandroidkotlin

import java.io.Serializable

class Valoration : Serializable {
    var facepoints: Float? = null
    var boobspoints: Float? = null
    var buttpoints: Float? = null
    var note: String? = null

    constructor() {
        // TODO Auto-generated constructor stub
    }

    constructor(facepoints: Float?, boobspoints: Float?, buttpoints: Float?,
                note: String) : super() {
        this.facepoints = facepoints
        this.boobspoints = boobspoints
        this.buttpoints = buttpoints
        this.note = note
    }

    companion object {

        /**
         *
         */
        private const val serialVersionUID = -383231886005873194L
    }


}
