package com.dajce_olti.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var calcSheet : EditText
    private lateinit var historyStack : ArrayList<String>
    private lateinit var recyclerView : RecyclerView
    private lateinit var viewManager : RecyclerView.LayoutManager
    private lateinit var viewAdapter : RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calcSheet = findViewById(R.id.editText)
        historyStack = java.util.ArrayList<String>()
        historyStack.add("test");
        viewManager = LinearLayoutManager(this)
        viewAdapter = HistoryAdapter(historyStack)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter
            adapter = viewAdapter
        }

        findViewById<Button>(R.id.button0).setOnClickListener(this)
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)
        findViewById<Button>(R.id.plus_button).setOnClickListener(this)
        findViewById<Button>(R.id.minus_button).setOnClickListener(this)
        findViewById<Button>(R.id.mult_button).setOnClickListener(this)
        findViewById<Button>(R.id.mod_button).setOnClickListener(this)
        findViewById<Button>(R.id.div_button).setOnClickListener(this)
        findViewById<Button>(R.id.del_button).setOnClickListener(this)
        findViewById<Button>(R.id.clear_button).setOnClickListener(this)
        findViewById<Button>(R.id.calculate_button).setOnClickListener(this)
        findViewById<Button>(R.id.point_button).setOnClickListener(this)
        findViewById<Button>(R.id.open_button).setOnClickListener(this)
        findViewById<Button>(R.id.close_button).setOnClickListener(this)
    }


    override fun onClick(v: View?) {

        if (v != null) {
            when(v.id){
                R.id.button0 -> calcSheet.append("0")
                R.id.button1 -> calcSheet.append("1")
                R.id.button2 -> calcSheet.append("2")
                R.id.button3 -> calcSheet.append("3")
                R.id.button4 -> calcSheet.append("4")
                R.id.button5 -> calcSheet.append("5")
                R.id.button6 -> calcSheet.append("6")
                R.id.button7 -> calcSheet.append("7")
                R.id.button8 -> calcSheet.append("8")
                R.id.button9 -> calcSheet.append("9")

                //operations
                R.id.plus_button -> calcSheet.append("+")
                R.id.minus_button-> calcSheet.append("-")
                R.id.mult_button -> calcSheet.append("*")
                R.id.mod_button  -> calcSheet.append("%")
                R.id.div_button  -> calcSheet.append("/")
                R.id.point_button-> calcSheet.append(".")
                R.id.open_button -> calcSheet.append("(")
                R.id.close_button-> calcSheet.append(")")
                R.id.del_button  -> calcSheet.setText(calcSheet.text.substring(0, calcSheet.text.length-1))
                R.id.clear_button-> calcSheet.setText("")
                R.id.calculate_button -> {
                    calcSheet.setText((calculate(calcSheet.text.toString())).toString())
                    historyStack.add(calcSheet.text.toString())
                    viewAdapter.notifyDataSetChanged()

                }
            }
        }
    }

    fun calculate(expression : String): Float {
        val evaluator : Evaluator = Evaluator.createEvaluator()
        return evaluator.evaluate(expression)
    }

}
