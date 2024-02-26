package com.airport.demo.data.source

import com.airport.demo.data.ApiClient
import com.airport.demo.data.OperationCallback
import com.airport.demo.data.dto.CurrencyDto
import com.airport.demo.data.source.interfaces.CurrencyDataSourceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyDataSource(apiClient: ApiClient) : CurrencyDataSourceInterface {
    private var call: Call<CurrencyDto>? = null
    private val service = apiClient.build("https://api.freecurrencyapi.com")

    override fun retrieveCurrencies(
        baseCurrency: String,
        currencies: String,
        callback: OperationCallback<Any>
    ) {
        val apikey = "fca_live_QtJK7Ca2yuxrQXxI9v5vOj6OUjJgpKV6eGLBvMpg"
        call = service?.getCurrencies(apikey,baseCurrency,currencies)
        call?.enqueue(object : Callback<CurrencyDto> {

            override fun onFailure(call: Call<CurrencyDto>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<CurrencyDto>, response: Response<CurrencyDto>) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    } else {
                        callback.onError(response.message())
                    }
                }
            }
        })
    }
}