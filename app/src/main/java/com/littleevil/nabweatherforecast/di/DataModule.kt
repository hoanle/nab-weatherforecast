package com.littleevil.nabweatherforecast.di

import com.littleevil.nabweatherforecast.data.local.datasource.WeatherLocalDataSource
import com.littleevil.nabweatherforecast.data.local.datasource.WeatherLocalDataSourceImpl
import com.littleevil.nabweatherforecast.data.remote.datasource.WeatherRemoteDataSource
import com.littleevil.nabweatherforecast.data.remote.datasource.WeatherRemoteDataSourceImpl
import com.littleevil.nabweatherforecast.data.repos.WeatherRepository
import com.littleevil.nabweatherforecast.data.repos.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindWeathreRemoteDataSource(weatherRemoteDataSource: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    @Binds
    abstract fun bindWeatherLocalDataSource(weatherLocalDataSourceImpl: WeatherLocalDataSourceImpl): WeatherLocalDataSource

    @Binds
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}