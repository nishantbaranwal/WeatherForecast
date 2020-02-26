package com.theavengers.weatherforecast.ui

import androidx.lifecycle.LiveData
import com.theavengers.weatherforecast.data.api.WeatherForecastInterface
import com.theavengers.weatherforecast.data.repository.NetworkState
import com.theavengers.weatherforecast.data.repository.WeatherForecastNetworkDataSource
import com.theavengers.weatherforecast.data.vo.WeatherForecastDetails
import io.reactivex.disposables.CompositeDisposable

class WeatherForecastDetailsRepository(private val apiService: WeatherForecastInterface) {

    lateinit var WeatherForecastNetworkDataSource: WeatherForecastNetworkDataSource

    fun fetchSingleWeatherForecastDetails(compositeDisposable: CompositeDisposable, movieId: Int): LiveData<WeatherForecastDetails> {

        WeatherForecastNetworkDataSource = WeatherForecastNetworkDataSource(apiService, compositeDisposable)
        WeatherForecastNetworkDataSource.fetchWeatherForecastDetails(movieId)

        return WeatherForecastNetworkDataSource.downloadedWeatherForecastDetailsResponse

    }

    fun getWeatherForecastDetailsNetworkState(): LiveData<NetworkState> {
        return WeatherForecastNetworkDataSource.networkState
    }
}