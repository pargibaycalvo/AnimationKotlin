package com.example.pargibaycalvo.animationkotlin

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_blank_fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_blank_fragment_home.*
import kotlinx.coroutines.experimental.*
import org.jetbrains.anko.toast
import kotlin.coroutines.experimental.CoroutineContext
import kotlinx.coroutines.experimental.android.UI

class MainActivity : AppCompatActivity() {

    private val uiContext: CoroutineContext = UI
    private val bgContext: CoroutineContext = CommonPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var miMensaje = Mensajes("Hola Pedro")
        miMensaje.key = "JJBXK"
        Mensajes.say(miMensaje.key)

        //metodo definido abajo
        //ejecuta la tarea de animacion en segundo plano
        buttonAnimacion.setOnClickListener {
            tarea_segundo_plano()
        }

        //
        val intentDomotta: Intent = Intent(this, Main2ActivityNavigation::class.java)
        imageDomotta.setOnClickListener {
            toast("Soy Domotta")
            startActivity(intentDomotta)
        }
    }

    var j: Int = 1
    var job2: Job? = null

    //animacion en movimiento con contador y mensaje.
    private fun tarea_segundo_plano() = launch(uiContext) {
        val task2 = async(bgContext) {
            for (i in 30 downTo 1) {
                Log.d("Task2", "Contandor: ${i}")
                delay(200) // wait half a second
            }
        }
        job2 = task2
        val result = job2?.start();
        j*=-1
        val objectAnimator = ObjectAnimator.ofFloat(imageDomotta, "translationY", 300f*j)
        objectAnimator.duration=3000L
        objectAnimator.interpolator
        objectAnimator.start()

        //contador, al pulsar el boton, cuenta atrás y mensaje cuando acabe la cuenta
        for (i in 10 downTo 1) {
            textView.text = "Countdown $i ..."
            delay(500)
            if (!objectAnimator.isRunning) job2!!.cancel()
        }
        textView.text = "Done!"
    }
}

