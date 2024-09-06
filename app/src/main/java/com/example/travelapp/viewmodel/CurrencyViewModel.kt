package com.example.travelapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.model.MoneyCurrency
import com.example.travelapp.repository.ChangeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel: ViewModel()  {

    val currencyInfoList = MutableLiveData<MoneyCurrency?>()
    private val changeRepository: ChangeRepository = ChangeRepository()

    fun getCurrencyInfo(currency: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                changeRepository.getChangeInfo(currency)
            } catch(e: Exception) {
                currencyInfoList.postValue(null)
                com.example.travelapp.repository.Result.Error(Exception("Network request failed"))
            }
            when (result) {

                is  com.example.travelapp.repository.Result.Success<MoneyCurrency> -> {
                    currencyInfoList.postValue(result.data)
                }
                // Happy path
                else -> currencyInfoList.postValue(null)
            }
        }
    }
}