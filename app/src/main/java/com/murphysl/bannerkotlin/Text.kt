package com.murphysl.bannerkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

/**
 * Text

 * @author: MurphySL
 * *
 * @time: 2017/2/14 17:21
 */


class Text : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.setOnClickListener { }
    }
}
