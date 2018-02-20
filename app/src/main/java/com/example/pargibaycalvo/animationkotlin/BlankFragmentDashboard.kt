package com.example.pargibaycalvo.animationkotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_blank_fragment_dashboard.*

/**
 * Clase Blank Dashboard
 */

class BlankFragmentDashboard : Fragment() {

    //ejercicio miercoles
    var main2: String = ""

    companion object {
        fun newInstance(): BlankFragmentDashboard {
            var fragmentDashboard = BlankFragmentDashboard()
            var args = Bundle()
            fragmentDashboard.arguments = args
            return fragmentDashboard
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_blank_fragment_dashboard, container, false)
    }

    //ejercicio miercoles
    override fun onResume() {
        super.onResume()
        main2 = arguments.getString("111", "ERROR 404")
        textDashboard.setText(main2)

    }

}
