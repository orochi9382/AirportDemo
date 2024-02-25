package com.airport.demo.data.source

import com.airport.demo.data.OperationCallback
import com.airport.demo.data.dto.AirPortFlyDto
import com.airport.demo.data.source.interfaces.AirFlyDataSourceInterface
import com.emedinaa.kotlinmvvm.data.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AirPortFlyDataSource (apiClient: ApiClient) : AirFlyDataSourceInterface {

    private var call: Call<List<AirPortFlyDto>>? = null
    private val service = apiClient.build()

    override fun retrieveFlyInfo(flyType:String,callback: OperationCallback<List<Any>>) {
        call = service?.flyInfo(flyType,"TPE")
        call?.enqueue(object : Callback<List<AirPortFlyDto>> {
            override fun onFailure(call: Call<List<AirPortFlyDto>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<List<AirPortFlyDto>>,
                response: Response<List<AirPortFlyDto>>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        AirPortFlyDto.result = it
                        callback.onSuccess(it)
                    } else {
                        callback.onError(response.message())
                    }
                }
            }
        })
    }

}