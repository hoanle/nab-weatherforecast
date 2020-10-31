package com.littleevil.nabweatherforecast.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.littleevil.nabweatherforecast.MainCoroutineRule
import com.littleevil.nabweatherforecast.data.local.entity.Weather
import com.littleevil.nabweatherforecast.data.repos.FakeWeatherRepository
import com.littleevil.nabweatherforecast.getOrAwaitValue
import com.littleevil.nabweatherforecast.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.Is.`is`
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.util.*

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private lateinit var fakeWeatherRepository: FakeWeatherRepository
    private lateinit var weatherViewModel: WeatherViewModel

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecuteRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        fakeWeatherRepository = FakeWeatherRepository()
        val weatherList = mutableListOf<Weather>(
            Weather(
                temp = 295.5,
                humidity = 74,
                pressure = 1001,
                date = Date(),
                description = "light rain",
                searchQuery = "Hanoi"
            ),
            Weather(
                temp = 296.5,
                humidity = 74,
                pressure = 1002,
                date = Date(),
                description = "light rain",
                searchQuery = "Hanoi"
            ),
            Weather(
                temp = 297.5,
                humidity = 74,
                pressure = 1003,
                date = Date(),
                description = "light rain",
                searchQuery = "Hanoi"
            ),
            Weather(
                temp = 298.5,
                humidity = 74,
                pressure = 1004,
                date = Date(),
                description = "good",
                searchQuery = "Hanoi",
            ),
            Weather(
                temp = 299.5,
                humidity = 74,
                pressure = 1005,
                date = Date(),
                description = "so good",
                searchQuery = "Hanoi",
            ),
            Weather(
                temp = 293.5,
                humidity = 74,
                pressure = 1006,
                date = Date(),
                description = "rain",
                searchQuery = "Hanoi",
            ),
            Weather(
                temp = 291.5,
                humidity = 74,
                pressure = 1007,
                date = Date(),
                description = "heavy rain",
                searchQuery = "Hanoi",
            )
        )
        fakeWeatherRepository.addWeathers(weatherList)
        weatherViewModel = WeatherViewModel(fakeWeatherRepository)
    }

    @Test
    fun searchQuery_allSpaces_queryErrorNotNull() {
        weatherViewModel.searchQueryLiveData.value = "     "
        assertNotNull(weatherViewModel.queryError.getOrAwaitValue())
    }

    @Test
    fun searchQuery_lessThanThreeCharacters_queryErrorNotNull() {
        weatherViewModel.searchQueryLiveData.value = "aa"
        assertNotNull(weatherViewModel.queryError.getOrAwaitValue())
    }

    @Test
    fun searchQuery_isValid_queryErrorEmpty() {
        weatherViewModel.searchQueryLiveData.value = "aaaaa"
        assertThat(weatherViewModel.queryError.getOrAwaitValue(), `is`(""))
    }

    @Test
    fun searchQuery_isValidButNotMatch_returnErrorNotFoundResult() {
        weatherViewModel.searchQueryLiveData.value = "aaaaaa"
        weatherViewModel.search()
        assertThat(
            weatherViewModel.searchResult.getOrAwaitValue().status,
            `is`(Result.Status.ERROR)
        )
        assertThat(weatherViewModel.searchResult.getOrAwaitValue().code, `is`(404))
    }

    @Test
    fun search_hasException_returnResultError() {
        weatherViewModel.searchQueryLiveData.value = "aaaaa"
        weatherViewModel.search()
        fakeWeatherRepository.errorMessage = "Fake Exception"
        assertThat(
            weatherViewModel.searchResult.getOrAwaitValue().status,
            `is`(Result.Status.ERROR)
        )
        assertEquals(
            weatherViewModel.searchResult.getOrAwaitValue().message,
            fakeWeatherRepository.errorMessage
        )
    }

    @Test
    fun search_returnSuccess() {
        weatherViewModel.searchQueryLiveData.value = "Hanoi"
        weatherViewModel.search()
        val searchResult = weatherViewModel.searchResult.getOrAwaitValue()
        assertThat(searchResult.status, `is`(Result.Status.SUCCESS))
        com.google.common.truth.Truth.assertThat(searchResult.data).hasSize(7)
    }
}