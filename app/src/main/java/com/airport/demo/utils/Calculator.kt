package com.airport.demo.utils

class Calculator {
    private var currentInput: String = ""
    private var currentOperator: String = ""
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0

    fun appendDigit(digit: String) {
        currentInput += digit
    }

    fun appendDigit(digit: Int) {
        currentInput += digit.toString()
    }

    fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            currentOperator = operator
            operand1 = currentInput.toDouble()
            currentInput = ""
        }
    }

    fun calculate(): Any {
        if (currentInput.isNotEmpty() ) {
            var result: Double = 0.0
           if (currentOperator.isNotEmpty()){
               operand2 = currentInput.toDouble()
                result = when (currentOperator) {
                   "+" -> operand1 + operand2
                   "-" -> operand1 - operand2
                   "*" -> operand1 * operand2
                   "/" -> {
                       if (operand2 != 0.0) {
                           operand1 / operand2
                       } else {
                           return Double.NaN
                       }
                   }
                   else -> return Double.NaN
               }
           }else{
               result = currentInput.toDouble()
           }

            currentInput = ""

            val resultString = if (result.isNaN() || !result.isFinite()) {
                "Error"
            } else {
                val resultFormatted = if (result % 1 == 0.0) {
                    result.toInt().toString()
                } else {
                    val resultStr = result.toString()
                    if (resultStr.length > 8) {
                        String.format("%.3e", result)
                    } else {
                        resultStr
                    }
                }
                resultFormatted
            }

            return resultString
        }
        return Double.NaN
    }

    fun getCurrentInput(): String {
        return currentInput
    }

    fun clear() {
        currentInput = ""
        currentOperator = ""
        operand1 = 0.0
        operand2 = 0.0
    }
}