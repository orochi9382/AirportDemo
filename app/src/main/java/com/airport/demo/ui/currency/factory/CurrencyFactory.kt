package com.airport.demo.ui.currency.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.airport.demo.data.repo.CurrencyRepo
import com.airport.demo.ui.currency.CurrencyViewModel

class CurrencyFactory(private val repo: CurrencyRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyViewModel(repo) as T
    }
}