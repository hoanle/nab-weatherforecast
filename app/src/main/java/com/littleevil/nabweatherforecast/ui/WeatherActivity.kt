package com.littleevil.nabweatherforecast.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.littleevil.nabweatherforecast.R
import com.littleevil.nabweatherforecast.databinding.ActivityWeatherBinding
import com.littleevil.nabweatherforecast.ext.hideKeyboard
import com.scottyab.rootbeer.RootBeer
import dagger.hilt.android.AndroidEntryPoint
import com.littleevil.nabweatherforecast.util.Result
import javax.inject.Inject

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var dataBinding: ActivityWeatherBinding
    private val weatherViewModel: WeatherViewModel by viewModels { viewModelFactory }
    private val weatherAdapter: WeatherAdapter by lazy { WeatherAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (RootBeer(this).isRooted) {
            showRootedAlertDialog()
            return
        }
        dataBinding =
            DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather)
                .apply {
                    lifecycleOwner = this@WeatherActivity
                    viewModel = weatherViewModel
                }
        initWeatherListView()
        setUpObservers()
    }

    private fun initWeatherListView() {
        with(dataBinding.weatherListView) {
            layoutManager =
                LinearLayoutManager(this@WeatherActivity, LinearLayoutManager.VERTICAL, false)
            adapter = weatherAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@WeatherActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun setUpObservers() {
        weatherViewModel.queryError.observe(this) {
            dataBinding.searchInputLayout.error = if (it.isEmpty()) null else it
        }
        weatherViewModel.isSearching.observe(this) {
            hideKeyboard()
        }
        weatherViewModel.searchResult.observe(this) { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    dataBinding.progressBar.visibility = View.VISIBLE
                    dataBinding.weatherListView.visibility = View.GONE
                    dataBinding.tvError.visibility = View.GONE
                }
                Result.Status.ERROR -> {
                    dataBinding.tvError.visibility = View.VISIBLE
                    dataBinding.progressBar.visibility = View.GONE
                    dataBinding.weatherListView.visibility = View.GONE
                    result.message?.let { msg ->
                        showError(msg)
                    }
                }
                Result.Status.SUCCESS -> {
                    dataBinding.weatherListView.visibility = View.VISIBLE
                    dataBinding.tvError.visibility = View.GONE
                    dataBinding.progressBar.visibility = View.GONE
                    result.data?.let { weathers -> weatherAdapter.update(weathers) }
                }
            }
        }
    }

    private fun showError(errorMessage: String) {
        dataBinding.tvError.text = errorMessage
    }


    private fun showRootedAlertDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Rooted Device")
            .setMessage("You're running application on a rooted device. Please careful and try our service on another secure device!")
            .setPositiveButton("Ok") { _, _ ->
                finish()
            }
            .create()
        dialog.show()
    }
}