package com.emedinaa.kotlinmvvm.data

import com.airport.demo.data.dto.AirPortFlyDto
import com.airport.demo.data.dto.CurrencyDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.sql.ClientInfoStatus
import java.util.concurrent.TimeUnit


/**
 * @author Eduardo Medina
 */
object ApiClient {
    private const val API_BASE_URL = "https://e-traffic.taichung.gov.tw"

    private var servicesApiInterface: ServicesApiInterface? = null

    fun build(baseUrl: String? = null): ServicesApiInterface {


        val url = baseUrl?.let {
            baseUrl
        } ?: API_BASE_URL
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
        httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java
        )

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface {
        @GET("/DataAPI/api/AirPortFlyAPI/{flyType}/{portID}")
        fun flyInfo(
            @Path(value = "flyType") flyType: String,
            @Path(value = "portID") portID: String
        ): Call<List<AirPortFlyDto>>

        @GET("/v1/latest")
        fun getCurrencies(
            @Query(value = "apikey") apikey: String,
            @Query(value = "base_currency") baseCurrency: String,
            @Query(value = "currencies") currencies: String,
        ): Call<CurrencyDto>
    }


}