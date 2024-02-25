package com.airport.demo.data.dto

data class CurrencyDto(
    val data: Exchange?
) {

    data class Exchange(
        val CNY: String,
        val EUR: String,
        val HKD: String,
        val JPY: String,
        val KRW: String,
        val USD: String,
    )
}
