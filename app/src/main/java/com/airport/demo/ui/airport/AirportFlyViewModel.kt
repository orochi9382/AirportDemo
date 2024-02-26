package com.airport.demo.ui.airport

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airport.demo.data.OperationCallback
import com.airport.demo.data.repo.AirportFlyRepo
import com.airport.demo.model.AirPortFlyEntity

class AirportFlyViewModel(private val repo:AirportFlyRepo) : ViewModel() {

    private var arrivalTimestamp = 0L
    private var departureTimestamp = 0L
    private val _bindingAirportFlyEntity = MutableLiveData<List<AirPortFlyEntity>>()
    val bindingAirportFlyEntity: LiveData<List<AirPortFlyEntity>> = _bindingAirportFlyEntity

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    fun requestFlyInfo(flyType:String){
        if (checkUpdate(flyType)){
            repo.fetchAirFlyInfo(flyType,object :OperationCallback<List<Any>>{

                override fun onSuccess(data: Any?) {
                    updateTimesTamp(flyType)
                    (data as List<AirPortFlyEntity>).let {
                        _bindingAirportFlyEntity.value = it
                    }

                }


                override fun onError(error: String?) {
                    error?.let {
                        Log.d("Ryan","error = $error")
                        _onMessageError.postValue(it)
                    }
                }

            })
        }

    }

    fun updateTimesTamp(flyType:String){
        if (flyType == "A"){
           arrivalTimestamp = System.currentTimeMillis()
        }else if (flyType == "D"){
            departureTimestamp = System.currentTimeMillis()
        }
    }

    private fun checkUpdate(flyType:String):Boolean{
        if (flyType == "A"){
            val dd = System.currentTimeMillis() - arrivalTimestamp
            return System.currentTimeMillis() - arrivalTimestamp > 180000
        }else if (flyType == "D"){
            return System.currentTimeMillis() - departureTimestamp > 180000
        }
        return false
    }
}