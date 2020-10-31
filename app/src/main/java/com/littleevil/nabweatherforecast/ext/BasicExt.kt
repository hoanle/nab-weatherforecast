package com.littleevil.nabweatherforecast.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.*

private const val DISPLAY_DATE_FORMAT = "EEE, dd MMM yyyy"

fun Date.toDisplayDate(): String = SimpleDateFormat(
    DISPLAY_DATE_FORMAT,
    Locale.getDefault()
).format(
    this
)

fun Long.toDate(): Date = Calendar.getInstance().apply { timeInMillis = this@toDate * 1000 }.time

fun Double.toCelsius() = this - 273.15

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}