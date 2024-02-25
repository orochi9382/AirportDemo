package com.airport.demo.data.dto


    data class AirPortFlyDto(
        val FlyType : String? = null,
        val AirlineID : String? = null,
        val Airline : String? = null,
        val FlightNumber : String? = null,
        val DepartureAirportID : String? = null,
        val DepartureAirport : String? = null,
        val ArrivalAirportID : String? = null,
        val ArrivalAirport : String? = null,
        val ScheduleTime : String? = null,
        val ActualTime : String? = null,
        val EstimatedTime : String? = null,
        val Remark : String? = null,
        val Terminal : String? = null,
        val Gate : String? = null,
        val UpdateTime : String? = null,
    ){
        companion object{
            var result:Any? = null
        }
    }


