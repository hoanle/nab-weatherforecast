package com.littleevil.nabweatherforecast.di

import com.littleevil.nabweatherforecast.data.repos.WeatherRepository
import com.littleevil.nabweatherforecast.data.repos.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}