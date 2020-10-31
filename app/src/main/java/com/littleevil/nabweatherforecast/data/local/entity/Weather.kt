package com.littleevil.nabweatherforecast.data.local.entity


import androidx.room.*
import java.util.*

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    var temp: Double,

    var pressure: Int,

    var date: Date,

    var description: String? = null,

    var humidity: Int,

    @ColumnInfo(name = "query")
    var searchQuery: String? = null
)