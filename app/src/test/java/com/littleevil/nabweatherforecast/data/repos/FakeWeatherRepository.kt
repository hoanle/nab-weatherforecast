package com.littleevil.nabweatherforecast.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.littleevil.nabweatherforecast.data.local.entity.Weather
import com.littleevil.nabweatherforecast.util.Result

class FakeWeatherRepository() : WeatherRepository {
    var errorMessage: String? = null
    private val listWeather = mutableListOf<Weather>()
    private val searchResult: MutableLiveData<Result<List<Weather>>> = MutableLiveData()

    override fun searchWeatherByCity(city: String): LiveData<Result<List<Weather>>> {
        searchResult.value = getWeathers(city)
        return searchResult
    }

    private fun getWeathers(city: String): Result<List<Weather>> {
        errorMessage?.let { return Result.error(null, it) }
        val weathers = listWeather
                .filter { it.searchQuery?.contains(city) == true }
        if (weathers.isEmpty()) {
            errorMessage = "City not found"
            return Result.error(404, errorMessage!!)
        }
        return Result.success(weathers)
    }

    fun addWeather(weather: Weather) {
        listWeather.add(weather)
    }

    fun addWeathers(weathers: MutableList<Weather>) {
        listWeather.addAll(weathers)
    }
}