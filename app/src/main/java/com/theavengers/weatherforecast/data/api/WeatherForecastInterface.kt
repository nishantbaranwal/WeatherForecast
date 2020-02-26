package com.theavengers.weatherforecast.data.api

import com.theavengers.weatherforecast.data.vo.WeatherForecastDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastInterface {

    @GET("data/2.5/forecast/daily")
    fun getForecastDetails(@Query("q") cityName: String,
                           @Query("cnt") totalDays: Int, @Query("appid") api_key: String)
            : Single<WeatherForecastDetails>

}