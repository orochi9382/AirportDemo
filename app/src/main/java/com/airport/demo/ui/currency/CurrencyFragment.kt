package com.airport.demo.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.airport.demo.databinding.FragmentCurrencyBinding
import com.airport.demo.di.CurrencyInjection
import com.airport.demo.model.ExchangeEntity
import com.airport.demo.utils.DialogUtil
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale


class CurrencyFragment : Fragment() {

    private var _binding: FragmentCurrencyBinding? = null

    private val vm by viewModels<CurrencyViewModel> {
        CurrencyInjection.provideViewModelFactory()
    }
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        liveBinding()
        vm.requestExchange()

        _binding?.currencyItem1?.setOnClickListener {view ->
            val baseCurrency = _binding?.currency1?.text.toString()
            showBottomDialog(baseCurrency)
        }
        _binding?.currencyItem2?.setOnClickListener {view ->
            val baseCurrency = _binding?.currency2?.text.toString()
            showBottomDialog(baseCurrency)
        }
        _binding?.currencyItem3?.setOnClickListener {view ->
            val baseCurrency = _binding?.currency3?.text.toString()
            showBottomDialog(baseCurrency)
        }
        _binding?.currencyItem4?.setOnClickListener {view ->
            val baseCurrency = _binding?.currency4?.text.toString()
            showBottomDialog(baseCurrency)
        }
        _binding?.currencyItem5?.setOnClickListener {view ->
            val baseCurrency = _binding?.currency5?.text.toString()
            showBottomDialog(baseCurrency)
        }
        _binding?.currencyItem6?.setOnClickListener {view ->
            val baseCurrency = _binding?.currency6?.text.toString()
            showBottomDialog(baseCurrency)
        }

    }

    private fun showBottomDialog(baseCurrency:String){
        val dialog = CalculatorBottomSheetDialog()


        dialog.setListener(object:CalculatorBottomSheetDialog.CalculateListener{
            override fun callback(num: String) {
                vm.updateBasePrice(num)
                vm.requestExchange(baseCurrency)
            }

        })
        activity?.supportFragmentManager?.let { dialog.show(it, CalculatorBottomSheetDialog.TAG) }
    }

    private fun liveBinding(){
        vm.bindingExchangeEntity.observe(viewLifecycleOwner, currencyObserver)
        vm.onMessageError.removeObserver(errorObserver)
    }

    private val currencyObserver = Observer<ExchangeEntity?> {
        requireActivity().runOnUiThread {
            it?.let{entity->
                _binding?.price1?.text = currencyFormat(entity.USD)
                _binding?.price2?.text = currencyFormat(entity.CNY)
                _binding?.price3?.text = currencyFormat(entity.EUR)
                _binding?.price4?.text = currencyFormat(entity.HKD)
                _binding?.price5?.text = currencyFormat(entity.JPY)
                _binding?.price6?.text = currencyFormat(entity.KRW)
            }
        }
    }

    private val errorObserver = Observer<Any> {
        it.let{
            requireActivity().runOnUiThread {
                DialogUtil().showDialog(requireActivity())
            }
        }
    }

    private fun currencyFormat(d: Double): String? {
        val formatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###.##")
        formatter.roundingMode = RoundingMode.FLOOR
        return formatter.format(d)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        vm.bindingExchangeEntity.removeObserver(currencyObserver)
        vm.onMessageError.removeObserver(errorObserver)
        _binding = null
    }
}