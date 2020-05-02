package com.dajce_olti.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener{

    val clacSheet : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.button0 ->
            }
        }
    }

}
