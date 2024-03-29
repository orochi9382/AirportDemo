package com.airport.demo.ui.currency

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airport.demo.databinding.ViewCalculatorBinding
import com.airport.demo.utils.Calculator
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CalculatorBottomSheetDialog : BottomSheetDialogFragment() {

    private  var _binding: ViewCalculatorBinding? = null
    private lateinit var calculator: Calculator
    private var calculateListener: CalculateListener? = null

    companion object{
        val TAG = "CalculatorBottomSheetDialog"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ViewCalculatorBinding.inflate(
            inflater,
            container,
            false
        )

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculator = Calculator()
        createCalculator()
    }

    fun setListener(calculateListener: CalculateListener){
        this.calculateListener = calculateListener
    }

    private fun createCalculator(){
        _binding?.let {
            val resultTextView = it.resultTextView

            val button0 = it.button0
            val button1 = it.button1
            val button2 = it.button2
            val button3 = it.button3
            val button4 = it.button4
            val button5 = it.button5
            val button6 = it.button6
            val button7 = it.button7
            val button8 = it.button8
            val button9 = it.button9

            val digitButtons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)

            val buttonAdd = it.buttonAdd
            val buttonSubtract = it.buttonSubtract
            val buttonMultiply = it.buttonMultiply
            val buttonDivide = it.buttonDivide
            val buttonDecimal = it.buttonDecimal
            val buttonClear = it.buttonClear
            val buttonEqual = it.equal
            buttonClear.setOnClickListener {
                calculator.clear()
                resultTextView.text = calculator.getCurrentInput()
            }

            digitButtons.forEachIndexed { index, button ->
                button.setOnClickListener {
                    calculator.appendDigit(index)
                    resultTextView.text = calculator.getCurrentInput()
                }
            }

            buttonAdd.setOnClickListener {
                calculator.setOperator("+")
            }

            buttonSubtract.setOnClickListener {
                calculator.setOperator("-")
            }

            buttonMultiply.setOnClickListener {
                calculator.setOperator("*")
            }

            buttonDivide.setOnClickListener {
                calculator.setOperator("/")
            }

            buttonEqual.setOnClickListener {
                val result = calculator.calculate().toString()
                resultTextView.text = result

                if (result != "NaN" && result != "Error") {
                    calculateListener?.callback(result)
                    dismiss()
                }
            }

            buttonDecimal.setOnClickListener {
                calculator.appendDigit(".")
                resultTextView.text = calculator.getCurrentInput()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)

                behavior.state = BottomSheetBehavior.STATE_EXPANDED

            }
        }
        return super.onCreateDialog(savedInstanceState)
    }


   interface CalculateListener{
       fun callback(num: String)
   }
}