package com.littleevil.nabweatherforecast.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.littleevil.nabweatherforecast.data.local.entity.Weather

@Dao
interface WeatherDao {

    @Query("SELECT *, `rowid` FROM weather WHERE `query` LIKE '%' || :query || '%' LIMIT 7")
    fun searchByCityName(query: String): LiveData<List<Weather>>

    @Insert(entity = Weather::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWeather(weathers: List<Weather>)
}