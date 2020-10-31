package com.littleevil.nabweatherforecast.data.local.entity


import androidx.room.*
import java.util.*

@Fts4
@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val id: Int,

    var temp: Double,

    var pressure: Int,

    var date: Date,

    var description: String? = null,

    var humidity: Int,

    @ColumnInfo(name = "query")
    var searchQuery: String? = null
)