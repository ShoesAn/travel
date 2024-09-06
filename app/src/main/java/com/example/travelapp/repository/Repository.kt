package com.example.travelapp.repository

import android.os.Environment
import android.util.Log
import com.example.travelapp.BuildConfig
import com.example.travelapp.model.MoneyCurrency
import com.example.travelapp.model.ScheduleModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import java.io.FileInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class AirplaneRepository {
    // Function that makes the network request, blocking the current thread
    fun getAirplaneInfo(): Result<ScheduleModel> {
        val file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        return Result.Success(ScheduleModel(InputStreamReader(FileInputStream(file.path.plus("/airplan_temp")))))
    }
}

class ChangeRepository {
    private val loginUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=" + BuildConfig.API_TOKEN

    fun getChangeInfo(currency: String?): Result<MoneyCurrency> {
        var url = URL(loginUrl)
        currency?.let {
            url = URL(url.path + "&base_currency=${currency}")
        }
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            doInput = true
            val data = JSONObject(InputStreamReader(inputStream).readText())
            return Result.Success(Gson().fromJson(data.optJSONObject("data")!!.toString(), MoneyCurrency::class.java))
        }
        return Result.Error(Exception("Cannot open HttpURLConnection"))
    }
}