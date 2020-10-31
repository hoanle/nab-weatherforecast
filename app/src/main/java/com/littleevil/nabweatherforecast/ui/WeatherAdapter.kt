package com.littleevil.nabweatherforecast.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.littleevil.nabweatherforecast.R
import com.littleevil.nabweatherforecast.data.local.entity.Weather
import com.littleevil.nabweatherforecast.ext.toCelsius
import com.littleevil.nabweatherforecast.ext.toDisplayDate
import kotlinx.android.synthetic.main.item_weather.view.*
import kotlin.math.roundToInt

class WeatherAdapter(
    private val weatherList: MutableList<Weather> = mutableListOf()
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    fun update(weathers: List<Weather>) {
        weatherList.clear()
        weatherList.addAll(weathers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int = weatherList.size

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: Weather) {
            with(itemView) {
                val txtDate = "Date: ${weather.date.toDisplayDate()}"
                tvDate.text = txtDate
                val txtTemp = "Average temperature: ${weather.temp.toCelsius().roundToInt()}\u2103"
                tvTemp.text = txtTemp
                val txtPressure = "Pressure: ${weather.pressure}"
                tvPressure.text = txtPressure
                val txtHumidity = "Humidity: ${weather.humidity}%"
                tvHumidity.text = txtHumidity
                val txtDesc = "Description: ${weather.description}"
                tvDesc.text = txtDesc
            }
        }
    }
}