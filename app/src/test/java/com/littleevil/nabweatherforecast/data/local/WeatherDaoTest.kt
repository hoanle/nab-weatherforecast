package com.littleevil.nabweatherforecast.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.littleevil.nabweatherforecast.MainCoroutineRule
import com.littleevil.nabweatherforecast.data.local.entity.Weather
import com.littleevil.nabweatherforecast.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class WeatherDaoTest {
    private lateinit var appDatabase: AppDatabase

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = appDatabase.close()

    @Test
    fun insertWeathersAndSearchNotEmpty() = runBlockingTest {
        val searchQuery = "Hanoi"
        val weatherList = mutableListOf<Weather>(
            Weather(
                temp = 295.5,
                humidity = 74,
                pressure = 1001,
                date = Date(),
                description = "light rain",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 296.5,
                humidity = 74,
                pressure = 1002,
                date = Date(),
                description = "light rain",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 297.5,
                humidity = 74,
                pressure = 1003,
                date = Date(),
                description = "light rain",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 298.5,
                humidity = 74,
                pressure = 1004,
                date = Date(),
                description = "good",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 299.5,
                humidity = 74,
                pressure = 1005,
                date = Date(),
                description = "so good",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 293.5,
                humidity = 74,
                pressure = 1006,
                date = Date(),
                description = "rain",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 291.5,
                humidity = 74,
                pressure = 1007,
                date = Date(),
                description = "heavy rain",
                searchQuery = searchQuery
            )
        )
        appDatabase.weatherDao().insertAllWeather(weatherList)
        val searchWeatherResult =
            appDatabase.weatherDao().searchByCityName(searchQuery).getOrAwaitValue()
        assertThat(searchWeatherResult, hasSize(weatherList.size))
    }

    @Test
    fun insertWeathersAndSearchReturnEmpty() = runBlockingTest {
        val searchQuery = "Hanoi"
        val weatherList = mutableListOf<Weather>(
            Weather(
                temp = 295.5,
                humidity = 74,
                pressure = 1001,
                date = Date(),
                description = "light rain",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 296.5,
                humidity = 74,
                pressure = 1002,
                date = Date(),
                description = "light rain",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 297.5,
                humidity = 74,
                pressure = 1003,
                date = Date(),
                description = "light rain",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 298.5,
                humidity = 74,
                pressure = 1004,
                date = Date(),
                description = "good",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 299.5,
                humidity = 74,
                pressure = 1005,
                date = Date(),
                description = "so good",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 293.5,
                humidity = 74,
                pressure = 1006,
                date = Date(),
                description = "rain",
                searchQuery = searchQuery
            ),
            Weather(
                temp = 291.5,
                humidity = 74,
                pressure = 1007,
                date = Date(),
                description = "heavy rain",
                searchQuery = searchQuery
            )
        )
        appDatabase.weatherDao().insertAllWeather(weatherList)
        val searchWeatherResult =
            appDatabase.weatherDao().searchByCityName("aaaaa").getOrAwaitValue()
        assertThat(searchWeatherResult, hasSize(0))
    }
}