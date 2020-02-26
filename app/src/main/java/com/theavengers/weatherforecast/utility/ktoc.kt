package com.theavengers.weatherforecast.utility

fun  Kelvin_to_Celsius(x: Double):Double {
    return String.format("%.2f", x - 273.15).toDouble()
}