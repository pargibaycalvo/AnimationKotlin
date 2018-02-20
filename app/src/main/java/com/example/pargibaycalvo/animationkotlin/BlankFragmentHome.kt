package com.example.pargibaycalvo.animationkotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_blank_fragment_home.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch


/*
 * Clase Fragment Blank Home
 */

 class BlankFragmentHome:Fragment() {

    companion object {
        // definimos los argumentos, son los datos para usar entre el fragment y la activity
        fun newInstance(key: String): BlankFragmentHome {
            Log.d("Mensajes", "En el fragment Home recibo: ${key}")
            var fragmentHome = BlankFragmentHome()
            var args = Bundle()
            args.putString("key", key)
            fragmentHome.arguments = args
            return fragmentHome
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_blank_fragment_home, container, false)
    }

    var jobHome: Job? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Definimos una tarea en este fragment
        jobHome = launch(UI) {
            for (i in 10 downTo 1) {
                textoarg.text = "Countdown $i ..."
                delay(500)
            }
            textoarg.text = "Done!"
            // Cuando acaba la tarea usamos la clase Mensaje para enviar un log
            Mensajes.say("Desde Launch del Fragment Home")
        }
        jobHome?.start()
        Mensajes.say("Desde Fragment Home")
        textoarg.setText(arguments.getString("key"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        jobHome?.cancel()
    }
}
