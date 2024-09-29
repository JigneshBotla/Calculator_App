package com.appdevbootcamp.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var input: TextView? = null
    private var lastDigit: Boolean = false
    private var lastDecimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
    }

    fun onDigit(view: View) {

        input?.append((view as Button).text)
        lastDigit = true
        lastDecimal = false
    }

    fun clear(view: View) {
        input?.text = ""
    }

    fun onDecimal(view: View) {

        if (!lastDecimal) {
            input?.append(".")
            lastDecimal = true
            lastDigit = false
        }
    }

    fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/")
        }
    }

    fun onOperator(view: View) {
        if (!isOperatorAdded(input?.text.toString()) && lastDigit) {
            input?.append((view as Button).text)
            lastDecimal = false
            lastDigit = false
        }
    }

    fun onEqual(view: View) {
        var value = input?.text.toString()
        var prefix = ""
        if (lastDigit) {
            try {
                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1)
                }
                if (value.contains("+")) {
                    var splitvalue = value.split("+")
                    var first = splitvalue[0]
                    var second = splitvalue[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }

                    input?.text = (first.toDouble() + second.toDouble()).toString()
                } else if (value.contains("-")) {
                    var splitvalue = value.split("-")
                    var first = splitvalue[0]
                    var second = splitvalue[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }

                    input?.text = (first.toDouble() - second.toDouble()).toString()
                } else if (value.contains("*")) {
                    var splitvalue = value.split("*")
                    var first = splitvalue[0]
                    var second = splitvalue[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }

                    input?.text = (first.toDouble() * second.toDouble()).toString()
                }

                if (value.contains("/")) {
                    var splitvalue = value.split("/")
                    var first = splitvalue[0]
                    var second = splitvalue[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }

                    input?.text = (first.toDouble() / second.toDouble()).toString()
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }

    }
}


