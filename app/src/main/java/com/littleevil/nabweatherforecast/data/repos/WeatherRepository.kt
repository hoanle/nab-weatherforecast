package com.littleevil.nabweatherforecast.data.repos

import androidx.lifecycle.LiveData
import com.littleevil.nabweatherforecast.util.Result
import com.littleevil.nabweatherforecast.data.local.entity.Weather

interface WeatherRepository {
    fun searchWeatherByCity(city: String): LiveData<Result<List<Weather>>>
}