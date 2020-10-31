package com.littleevil.nabweatherforecast.data.remote.service

import com.littleevil.nabweatherforecast.data.remote.resp.WeatherSearchResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast/daily")
    suspend fun searchWeather(@Query("q") searchQuery: String, @Query("cnt") cnt: Int = 7,@Query("appid") appId: String): Response<WeatherSearchResp>
}