package com.theavengers.weatherforecast.data.vo

import City
import com.google.gson.annotations.SerializedName
import TemperatureList

 class WeatherForecastDetails (

    @SerializedName("city")
    val city: City,

    @SerializedName("list")
    val weatherForeCastList: List<TemperatureList>

)