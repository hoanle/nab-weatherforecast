package com.littleevil.nabweatherforecast.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.littleevil.nabweatherforecast.data.local.dao.WeatherDao
import com.littleevil.nabweatherforecast.data.local.entity.Weather
import com.littleevil.nabweatherforecast.util.Converters

@Database(entities = [Weather::class], version = 1)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}