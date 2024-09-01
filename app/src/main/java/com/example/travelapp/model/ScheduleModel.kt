package com.example.travelapp.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader

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

    constructor(flightString: InputStreamReader){
        val itemType = object : TypeToken<List<FlightInfo>>() {}.type
        val jsonObject = JSONObject(flightString.readText())
        flightList = Gson().fromJson(jsonObject.getJSONArray("InstantSchedule").toString(), itemType)
    }
}