package com.airport.demo.di

import com.airport.demo.data.ApiClient
import com.airport.demo.data.repo.AirportFlyRepo
import com.airport.demo.data.source.AirPortFlyDataSource
import com.airport.demo.ui.airport.AirportFlyFactory

object AirPortFlyInjection {
    private var airportFlyDataSource: AirPortFlyDataSource? = null
    private var airportFlyRepository: AirportFlyRepo? = null
    private var museumViewModelFactory: AirportFlyFactory? = null

    private fun createAirportFlyDataSource(): AirPortFlyDataSource {
        val dataSource = AirPortFlyDataSource(ApiClient)
        airportFlyDataSource = dataSource
        return dataSource
    }

    private fun createAirportFlyRepo(): AirportFlyRepo {
        val repository = AirportFlyRepo(provideDataSource())
        airportFlyRepository = repository
        return repository
    }

    private fun createFactory(): AirportFlyFactory {
        val factory = AirportFlyFactory(providerRepository())
        museumViewModelFactory = factory
        return factory
    }

    private fun provideDataSource() = airportFlyDataSource ?: createAirportFlyDataSource()
    private fun providerRepository() = airportFlyRepository ?: createAirportFlyRepo()

    fun provideViewModelFactory() = museumViewModelFactory ?: createFactory()

    fun destroy() {
        airportFlyDataSource = null
        airportFlyRepository = null
        museumViewModelFactory = null
    }
}