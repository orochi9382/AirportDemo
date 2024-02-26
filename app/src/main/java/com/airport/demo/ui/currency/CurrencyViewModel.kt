package com.airport.demo.ui.currency

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airport.demo.data.OperationCallback
import com.airport.demo.data.repo.CurrencyRepo
import com.airport.demo.model.ExchangeEntity

class CurrencyViewModel(val repo: CurrencyRepo) : ViewModel() {

    private val _bindingExchangeEntity = MutableLiveData<ExchangeEntity?>()
    val bindingExchangeEntity: LiveData<ExchangeEntity?> = _bindingExchangeEntity

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError
    private var basePrice: Double = 1000.0

    fun requestExchange(baseCurrency: String = "USD", currencies: String = "EUR,USD,JPY,KRW,CNY,HKD,USD") {
        repo.fetchCurrencies(baseCurrency,currencies,basePrice,object :OperationCallback<Any>{
            override fun onSuccess(data: Any?) {
               data as ExchangeEntity
                _bindingExchangeEntity.postValue(data)
            }

            override fun onError(error: String?) {
                error?.let {
                    Log.e("Ryan","error = $it")
                    _onMessageError.postValue(it)
                }
            }

        })
    }

    fun updateBasePrice(price:String){
        this.basePrice = price.toDouble()
    }
}