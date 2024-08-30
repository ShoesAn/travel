package com.example.travelapp.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

class ScheduleModel {

    val flightList: List<FlightInfo>

    data class FlightInfo(val expectTime : String,
                          val realTime : String,
                          val airLineName : String,
                          val airLineCode : String,
                          val airLineLogo : String,
                          val airLineUrl : String,
                          val airLineNum : String,
                          val upAirportCode : String,
                          val upAirportName : String,
                          val airPlaneType : String,
                          val airBoardingGate : String,
                          val airFlyStatus : String,
                          val airFlyDelayCause : String)

    constructor(flightString: String){
        val itemType = object : TypeToken<List<FlightInfo>>() {}.type
        flightList = Gson().fromJson(flightString, itemType)
    }
}