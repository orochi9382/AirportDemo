package com.airport.demo.data.repo

import com.airport.demo.data.OperationCallback
import com.airport.demo.data.dto.CurrencyDto
import com.airport.demo.data.source.CurrencyDataSource
import com.airport.demo.model.ExchangeEntity

class CurrencyRepo(private val dataSource: CurrencyDataSource) {

    fun fetchCurrencies(
        baseCurrency: String,
        currencies: String,
        basePrice: Double = 1000.0,
        callback: OperationCallback<Any>
    ) {
        dataSource.retrieveCurrencies(baseCurrency, currencies, object : OperationCallback<Any> {
            override fun onSuccess(data: Any?) {
                data as CurrencyDto
                val exchangeEntity = data.data?.let {
                    ExchangeEntity(
                        it.CNY.toDouble().times(basePrice),
                        it.EUR.toDouble().times(basePrice),
                        it.HKD.toDouble().times(basePrice),
                        it.JPY.toDouble().times(basePrice),
                        it.KRW.toDouble().times(basePrice),
                        it.USD.toDouble().times(basePrice)
                    ) ?: ExchangeEntity()
                }

                callback.onSuccess(exchangeEntity)
            }

            override fun onError(error: String?) {
                callback.onError(error)
            }

        })
    }


}