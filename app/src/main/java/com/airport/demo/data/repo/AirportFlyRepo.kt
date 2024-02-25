package com.airport.demo.data.repo

import com.airport.demo.data.OperationCallback
import com.airport.demo.data.dto.AirPortFlyDto
import com.airport.demo.data.source.AirPortFlyDataSource
import com.airport.demo.model.AirPortFlyEntity

class AirportFlyRepo(private val airFlyDataSource: AirPortFlyDataSource) {
    fun fetchAirFlyInfo(flyType:String,callback: OperationCallback<List<Any>>){

        airFlyDataSource.retrieveFlyInfo(flyType,object :OperationCallback<List<Any>>{

            override fun onSuccess(data: Any?) {
                val list = mutableListOf<AirPortFlyEntity>()
                data?.let {
                    data as List<AirPortFlyDto>
                    data.map {
                        val entity = AirPortFlyEntity(
                            it.FlyType,
                            it.AirlineID,
                            it.Airline,
                            it.FlightNumber,
                            it.DepartureAirportID,
                            it.DepartureAirport,
                            it.ArrivalAirportID,
                            it.ArrivalAirport,
                            it.ScheduleTime,
                            it.ActualTime,
                            it.EstimatedTime,
                            it.Remark,
                            it.Terminal,
                            it.Gate,
                            it.UpdateTime,
                        )
                        list.add(entity)
                    }
                }
                callback.onSuccess(list)
            }

            override fun onError(error: String?) {
                callback.onError(error)
            }


        })
    }
}