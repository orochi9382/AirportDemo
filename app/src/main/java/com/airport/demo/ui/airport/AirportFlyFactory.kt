package com.airport.demo.ui.airport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.airport.demo.data.repo.AirportFlyRepo

class AirportFlyFactory(private val repo:AirportFlyRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AirportFlyViewModel(repo) as T
    }
}