package com.littleevil.nabweatherforecast.data.repos

import android.util.Base64
import androidx.lifecycle.LiveData
import com.littleevil.nabweatherforecast.BuildConfig
import com.littleevil.nabweatherforecast.data.local.dao.WeatherDao
import com.littleevil.nabweatherforecast.data.local.entity.Weather
import com.littleevil.nabweatherforecast.data.remote.datasource.WeatherRemoteDataSource
import com.littleevil.nabweatherforecast.util.performGetOperation
import com.littleevil.nabweatherforecast.util.Result
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override fun searchWeatherByCity(city: String): LiveData<Result<List<Weather>>> =
        performGetOperation(
            databaseQuery = { weatherDao.searchByCityName(city) },
            networkCall = { weatherRemoteDataSource.searchWeather(city, getAppId()) },
            saveCallResult = { weatherDao.insertAllWeather(it) }
        )

    private fun getAppId(): String {
        return String(Base64.decode(BuildConfig.API_APP_ID, Base64.DEFAULT), StandardCharsets.UTF_8)
    }

}