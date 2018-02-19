package com.example.pargibaycalvo.animationkotlin

import android.util.Log

/**
 * Clase Mensaje, la llamaremos desde la clase de MainAcitvity
 */
data class Mensajes constructor(private var key2: String = "Hi"){
    //propiedad
    var key: String = ""
            // get por defecto
        get() = field
            // cambiamos el set
        set(value) {
            field = value + " :P"
        }
    // objeto
    companion object Compi {
        fun say(message: String) {
            Log.d("Mensajes",message)
        }
    }

}