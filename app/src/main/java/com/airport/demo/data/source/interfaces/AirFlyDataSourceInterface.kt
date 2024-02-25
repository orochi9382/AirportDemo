package com.airport.demo.data.source.interfaces

import com.airport.demo.data.OperationCallback


interface AirFlyDataSourceInterface {
    fun retrieveFlyInfo(flyType :String,callback: OperationCallback<List<Any>>)
}