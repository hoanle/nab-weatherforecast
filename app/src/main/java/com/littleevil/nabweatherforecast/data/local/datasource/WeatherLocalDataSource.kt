package com.littleevil.nabweatherforecast.data.local.datasource

import androidx.lifecycle.LiveData
import com.littleevil.nabweatherforecast.data.local.dao.WeatherDao
import com.littleevil.nabweatherforecast.data.local.entity.Weather
import javax.inject.Inject

interface WeatherLocalDataSource {
    fun searchByCityName(query: String): LiveData<List<Weather>>

    suspend fun insertAllWeather(weathers: List<Weather>)
}

class WeatherLocalDataSourceImpl @Inject constructor(
    private val weatherDao: WeatherDao
) : WeatherLocalDataSource {
    override fun searchByCityName(query: String): LiveData<List<Weather>> =
        weatherDao.searchByCityName(query)

    override suspend fun insertAllWeather(weathers: List<Weather>) =
        weatherDao.insertAllWeather(weathers)
}