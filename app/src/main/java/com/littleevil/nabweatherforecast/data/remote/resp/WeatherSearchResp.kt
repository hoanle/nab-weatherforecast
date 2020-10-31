package com.littleevil.nabweatherforecast.data.remote.resp

import com.google.gson.annotations.SerializedName


data class WeatherSearchResp(
    val city: City? = null,

    val cod: Int? = null,

    val message: Any? = null,

    val list: List<WeatherDto>? = null
)


data class WeatherDto(
    val temp: Temp,

    val pressure: Int,

    @SerializedName("dt")
    val date: Long,

    val weather: List<WeatherItem>,

    val humidity: Int,
)

data class City(
    val name: String,
    val id: Int,
)


data class Temp(
    val min: Double,
    val max: Double,
)


data class WeatherItem(
    val id: Int,
    val icon: String,
    val description: String,
)
