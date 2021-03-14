package com.amtelco.android.fonttester

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.fragment_first.view.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typefaceThin = context?.let { ResourcesCompat.getFont(it, R.font.roboto_thin_italic) }
        val typefaceBold= context?.let { ResourcesCompat.getFont(it, R.font.roboto_bold) }

        val newContext = ContextThemeWrapper(
            context,
            R.style.Myslider
        )

        val custom = Slider(newContext)


        custom.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        custom.valueFrom=0F
        custom.valueTo=100F
        custom.stepSize=10F

        val constraintLayout = view.findViewById(R.id.FirstFragment) as ConstraintLayout
        constraintLayout.addView(custom)

        val set = ConstraintSet()
        custom.id = View.generateViewId()
        set.clone(constraintLayout)

        set.connect(custom.id, TOP, R.id.button_first, BOTTOM)
        set.connect(custom.id, START, PARENT_ID, START)
        set.connect(custom.id, BOTTOM, PARENT_ID, BOTTOM)

        //app:layout_constraintTop_toBottomOf="@id/button_first"
        //app:layout_constraintStart_toStartOf="parent"/>
        set.applyTo(constraintLayout)

        view.findViewById<TextView>(R.id.textview_first).typeface = typefaceBold
        view.findViewById<Button>(R.id.button_first).typeface = typefaceThin
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    // Extension method to convert pixels to dp
    fun Int.toDp(context: Context):Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,this.toFloat(),context.resources.displayMetrics
    ).toInt()
}