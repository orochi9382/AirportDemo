package com.airport.demo.data.source.interfaces

import com.airport.demo.data.OperationCallback

interface CurrencyDataSourceInterface {
    fun retrieveCurrencies(baseCurrency :String ,
                           currencies :String ,
                           callback: OperationCallback<Any>)
}