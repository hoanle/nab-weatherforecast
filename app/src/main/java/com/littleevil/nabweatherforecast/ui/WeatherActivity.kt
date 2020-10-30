package com.littleevil.nabweatherforecast.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.littleevil.nabweatherforecast.R
import com.littleevil.nabweatherforecast.databinding.ActivityWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityWeatherBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather).apply {
            lifecycleOwner = this@WeatherActivity
            viewModel = weatherViewModel
        }
    }
}