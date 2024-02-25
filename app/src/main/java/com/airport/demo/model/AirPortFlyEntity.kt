package com.airport.demo.model

data class AirPortFlyEntity(
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
//    "FlyType": "A",
//"AirlineID": "JX",
//"Airline": "星宇航空",
//"FlightNumber": "786",
//"DepartureAirportID": "MNL",
//"DepartureAirport": "馬尼拉機場",
//"ArrivalAirportID": "TPE",
//"ArrivalAirport": "臺北桃園國際機場",
//"ScheduleTime": "00:10",
//"ActualTime": "00:02",
//"EstimatedTime": "00:02",
//"Remark": "已到ARRIVED",
//"Terminal": "1",
//"Gate": "A9",
//"UpdateTime": "2023-05-23 11:00:28"

) {
}