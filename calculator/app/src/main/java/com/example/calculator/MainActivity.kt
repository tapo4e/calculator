package com.example.calculator

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding
import java.util.Queue

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var value1: String = "0"
    private var value1Free: Boolean = true
    private var value2Free: Boolean = true
    private var value2: String = "0"
    private var numOfOperation = 0
    private var nextNumOfOperation = 0
    private lateinit var bindingClass : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        //buttons
        bindingClass.one.setOnClickListener(this)
        bindingClass.two.setOnClickListener(this)
        bindingClass.three.setOnClickListener(this)
        bindingClass.four.setOnClickListener(this)
        bindingClass.five.setOnClickListener(this)
        bindingClass.six.setOnClickListener(this)
        bindingClass.seven.setOnClickListener(this)
        bindingClass.eight.setOnClickListener(this)
        bindingClass.nine.setOnClickListener(this)
        bindingClass.zero.setOnClickListener(this)
        bindingClass.dot.setOnClickListener(this)
        bindingClass.plus.setOnClickListener(this)
        bindingClass.minus.setOnClickListener(this)
        bindingClass.multiplication.setOnClickListener(this)
        bindingClass.division.setOnClickListener(this)
        bindingClass.equals.setOnClickListener(this)
        bindingClass.clearButton.setOnClickListener(this)
        bindingClass.procentButton.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View) {
        when (view.id) {
            bindingClass.one.id -> {
                createValue("1")
            }

            bindingClass.two.id -> {
                createValue("2")
            }

            bindingClass.three.id -> {
                createValue("3")
            }

            bindingClass.four.id -> {
                createValue("4")
            }

            bindingClass.five.id -> {
                createValue("5")
            }

            bindingClass.six.id -> {
                createValue("6")
            }

            bindingClass.seven.id -> {
                createValue("7")
            }

            bindingClass.eight.id -> {
                createValue("8")
            }

            bindingClass.nine.id -> {
                createValue("9")
            }

            bindingClass.zero.id -> {
                createValue("0")
            }

            bindingClass.dot.id -> {
                createValue(".")
            }

            bindingClass.plus.id -> {
                writeOperator("+", 1)
            }

            bindingClass.minus.id -> {
                writeOperator("-", 2)

            }

            bindingClass.multiplication.id -> {
                writeOperator("×", 3)
            }

            bindingClass.division.id -> {
                writeOperator("÷", 4)
            }

            bindingClass.procentButton.id -> {
                value2 = "100"
                value2Free = false
                numOfOperation = 4
                writeOperator("%", 4)
            }

            bindingClass.equals.id -> {
                if (!value2Free)
                    operation()
            }

            bindingClass.clearButton.id -> {
                value1 = "0"
                value1Free = true
                value2 = "0"
                value2Free = true
                numOfOperation = 0
                nextNumOfOperation = 0
                bindingClass.resultField.text = ""
                bindingClass.operationField.text = ""
            }
        }
        println(value1)

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
        bindingClass.resultField.text = value1
        if (bindingClass.resultField.text.matches(Regex("[a-zA-Z]+")))
            bindingClass.resultField.setTextColor(Color.RED)
        else
            bindingClass.resultField.setTextColor(Color.WHITE)
        value2 = "0"
        value2Free = true
        value1Free = false
    }

    @SuppressLint("SetTextI18n")
    fun createValue(digit: String) {
            bindingClass.operationField.text = bindingClass.operationField.text.toString() + digit
            when (value1Free) {
                true -> value1 += digit
                false -> {
                    value2 += digit
                    numOfOperation = nextNumOfOperation
                    value2Free = false
                }
            }
    }

    @SuppressLint("SetTextI18n")
    fun writeOperator(operator: String, codeOfOperator: Int) {

        if (bindingClass.operationField.text.matches(Regex("^-?\\d+((.)+)?")) || operator == "-") {
            when (bindingClass.operationField.text.toString().matches(Regex("^.*\\D\$"))) {
                true -> bindingClass.operationField.text =
                    bindingClass.operationField.text.toString().replaceFirst(Regex("\\D\$"), operator)

                false -> bindingClass.operationField.text = bindingClass.operationField.text.toString() + operator
            }
            nextNumOfOperation = codeOfOperator
            value1Free = false
            if (!value2Free)
                operation()
        }
    }

}