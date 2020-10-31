package com.littleevil.nabweatherforecast.ui

import androidx.lifecycle.*
import com.littleevil.nabweatherforecast.data.repos.WeatherRepository
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val searchQueryLiveData = MutableLiveData<String>()
    val queryError: LiveData<String> = searchQueryLiveData.map {
        if (it.trim().length > 3)
            ""
        else
            "Please enter more than 3 characters!"
    }
    private val _doSearch = MutableLiveData<String>()

    private val _searchResult = _doSearch.switchMap { weatherRepository.searchWeatherByCity(it) }
    val searchResult = _searchResult
    val isSearching = _doSearch.map { it.isNotEmpty() }

    fun search() {
        _doSearch.value = searchQueryLiveData.value?.trim()
    }
}