package com.airport.demo.di

import com.airport.demo.data.ApiClient
import com.airport.demo.data.repo.CurrencyRepo
import com.airport.demo.data.source.CurrencyDataSource
import com.airport.demo.ui.currency.factory.CurrencyFactory

object CurrencyInjection {
    private var currencyDataSource: CurrencyDataSource? = null
    private var currencyRepository: CurrencyRepo? = null
    private var currencyFactory: CurrencyFactory? = null

    private fun createCurrencyDataSource(): CurrencyDataSource {
        val dataSource = CurrencyDataSource(ApiClient)
        currencyDataSource = dataSource
        return dataSource
    }

    private fun createCurrencyRepo(): CurrencyRepo {
        val repository = CurrencyRepo(provideDataSource())
        currencyRepository = repository
        return repository
    }

    private fun createFactory(): CurrencyFactory {
        val factory = CurrencyFactory(providerRepository())
        currencyFactory = factory
        return factory
    }

    private fun provideDataSource() = currencyDataSource ?: createCurrencyDataSource()
    private fun providerRepository() = currencyRepository ?: createCurrencyRepo()

    fun provideViewModelFactory() = currencyFactory ?: createFactory()

    fun destroy() {
        currencyDataSource = null
        currencyRepository = null
        currencyFactory = null
    }
}