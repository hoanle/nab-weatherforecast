package com.littleevil.nabweatherforecast.data.remote.datasource

import com.littleevil.nabweatherforecast.data.local.entity.Weather
import com.littleevil.nabweatherforecast.data.remote.resp.WeatherSearchResp
import com.littleevil.nabweatherforecast.data.remote.service.WeatherService
import com.littleevil.nabweatherforecast.ext.toDate
import com.littleevil.nabweatherforecast.util.Result
import javax.inject.Inject

interface WeatherRemoteDataSource {
    suspend fun searchWeather(city: String, appId: String): Result<List<Weather>>
}

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherService: WeatherService
) : BaseDataSource(), WeatherRemoteDataSource {
    override suspend fun searchWeather(city: String, appId: String): Result<List<Weather>> =
        getResult(
            call = { weatherService.searchWeather(city, appId = appId) },
            bodyMapping = { weatherResp: WeatherSearchResp ->
                weatherResp
                    .list
                    ?.map {
                        Weather(
                            id = "${it.date}${weatherResp.city!!.id}",
                            temp = (it.temp.min + it.temp.max) / 2,
                            pressure = it.pressure,
                            date = it.date.toDate(),
                            description = it.weather.firstOrNull()?.description,
                            humidity = it.humidity,
                            searchQuery = city
                        )
                    }
                    ?: mutableListOf()
            }
        )
}