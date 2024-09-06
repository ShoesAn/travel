package com.example.travelapp.viewmodel

import android.os.Environment
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.model.ScheduleModel
import com.example.travelapp.model.ScheduleModel.FlightInfo
import com.example.travelapp.repository.AirplaneRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AirplaneViewModel : ViewModel() {
    val planeInfoList = MutableLiveData<List<FlightInfo>?>()
    private val airplaneRepository: AirplaneRepository = AirplaneRepository()

    fun getAirplaneInfo() {
        viewModelScope.launch(Dispatchers.IO) {

            val result = try {
                airplaneRepository.getAirplaneInfo()
            } catch (e: Exception) {
                planeInfoList.postValue(null)
                com.example.travelapp.repository.Result.Error(Exception("Network request failed"))
            }
            when (result) {
                is com.example.travelapp.repository.Result.Success<ScheduleModel> -> {
                    planeInfoList.postValue(result.data.flightList)
                }
                else -> planeInfoList.postValue(null)
            }
        }
    }
}