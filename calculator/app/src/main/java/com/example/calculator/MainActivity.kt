package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Queue

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var value1: String = ""
    private var value1Free: Boolean = true
    private var value2Free: Boolean = true
    private var value2: String = ""
    private var numOfOperation = 0
    private var nextNumOfOperation = 0
    private lateinit var operationField: TextView
    private lateinit var resultField: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        operationField = findViewById(R.id.operationField)
        resultField = findViewById(R.id.resultField)
        //buttons
        val one: Button = findViewById(R.id.one)
        one.setOnClickListener(this)
        val two: Button = findViewById(R.id.two)
        two.setOnClickListener(this)
        val three: Button = findViewById(R.id.three)
        three.setOnClickListener(this)
        val four: Button = findViewById(R.id.four)
        four.setOnClickListener(this)
        val five: Button = findViewById(R.id.five)
        five.setOnClickListener(this)
        val six: Button = findViewById(R.id.six)
        six.setOnClickListener(this)
        val seven: Button = findViewById(R.id.seven)
        seven.setOnClickListener(this)
        val eight: Button = findViewById(R.id.eight)
        eight.setOnClickListener(this)
        val nine: Button = findViewById(R.id.nine)
        nine.setOnClickListener(this)
        val zero: Button = findViewById(R.id.zero)
        zero.setOnClickListener(this)
        val dot: Button = findViewById(R.id.dot)
        dot.setOnClickListener(this)
        val plus: Button = findViewById(R.id.plus)
        plus.setOnClickListener(this)
        val minus: Button = findViewById(R.id.minus)
        minus.setOnClickListener(this)
        val multiplication: Button = findViewById(R.id.multiplication)
        multiplication.setOnClickListener(this)
        val division: Button = findViewById(R.id.division)
        division.setOnClickListener(this)
        val equals: Button = findViewById(R.id.equals)
        equals.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View) {
        when (view.id) {
            R.id.one -> {
                createValue("1")
            }

            R.id.two -> {
                createValue("2")
                println(operationField.text.toString())
            }

            R.id.three -> {
                createValue("3")
            }

            R.id.four -> {
                createValue("4")
            }

            R.id.five -> {
                createValue("5")
            }

            R.id.six -> {
                createValue("6")
            }

            R.id.seven -> {
                createValue("7")
            }

            R.id.eight -> {
                createValue("8")
            }

            R.id.nine -> {
                createValue("9")
            }

            R.id.zero -> {
                createValue("0")
            }

            R.id.dot -> {
                createValue(".")
            }

            R.id.plus -> {
                if (operationField.text.matches(Regex("^-?\\d+((.)+)?"))) {
                    when (operationField.text.toString().matches(Regex("^.*\\D\$"))) {
                        true -> operationField.text =
                            operationField.text.toString().replaceFirst(Regex("\\D\$"), "+")

                        false -> operationField.text = operationField.text.toString() + "+"
                    }
                    nextNumOfOperation = 1
                    value1Free = false
                    if (!value2Free)
                        operation()
                }
            }

            R.id.minus -> {
                when (operationField.text.toString().matches(Regex("^.*\\D\$"))) {
                    true -> operationField.text =
                        operationField.text.toString().replaceFirst(Regex("\\D\$"), "-")

                    false -> operationField.text = operationField.text.toString() + "-"
                }
                value1Free = false
                nextNumOfOperation = 2
                if (!value2Free)
                    operation()

            }

            R.id.multiplication -> {if (operationField.text.matches(Regex("^-?\\d+((.)+)?"))) {
                when (operationField.text.toString().matches(Regex("^.*\\D\$"))) {
                    true -> operationField.text =
                        operationField.text.toString().replaceFirst(Regex("\\D\$"), "⨉")

                    false -> operationField.text = operationField.text.toString() + "⨉"
                }
                nextNumOfOperation = 3
                value1Free = false
                if (!value2Free)
                    operation()
            }
            }

            R.id.division -> {if (operationField.text.matches(Regex("^-?\\d+((.)+)?"))) {
                when (operationField.text.toString().matches(Regex("^.*\\D\$"))) {
                    true -> operationField.text =
                        operationField.text.toString().replaceFirst(Regex("\\D\$"), "÷")

                    false -> operationField.text = operationField.text.toString() + "÷"
                }
                nextNumOfOperation = 4
                value1Free = false
                if (!value2Free)
                    operation()
            }
            }
        }


    }

    @SuppressLint("SetTextI18n")
    private fun operation() {
        when (numOfOperation) {
            1 -> value1 =
                if ((value1.toDouble() + value2.toDouble()) % 1 == 0.0) (value1.toDouble() + value2.toDouble()).toInt()
                    .toString() else (value1.toDouble() + value2.toDouble()).toString()

            2 -> value1 =
                if ((value1.toDouble() - value2.toDouble()) % 1 == 0.0) (value1.toDouble() - value2.toDouble()).toInt()
                    .toString() else (value1.toDouble() - value2.toDouble()).toString()

            3 -> value1 =
                if ((value1.toDouble() * value2.toDouble()) % 1 == 0.0) (value1.toDouble() * value2.toDouble()).toInt()
                    .toString() else (value1.toDouble() * value2.toDouble()).toString()

            4 -> value1 =
                if ((value1.toDouble() / value2.toDouble()) % 1 == 0.0) (value1.toDouble() / value2.toDouble()).toInt()
                    .toString() else (value1.toDouble() / value2.toDouble()).toString()
        }
        resultField.text = value1
        value2 = ""
        value2Free = true
        value1Free = false
        println("success")
    }

    @SuppressLint("SetTextI18n")
    fun createValue(digit: String) {

        operationField.text = operationField.text.toString() + digit
        when (value1Free) {
            true -> value1 += digit
            false -> {
                value2 += digit
                numOfOperation = nextNumOfOperation
                value2Free = false
            }
        }
    }

}