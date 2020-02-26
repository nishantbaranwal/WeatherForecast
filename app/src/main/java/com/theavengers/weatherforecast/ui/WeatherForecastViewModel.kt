package com.theavengers.weatherforecast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.theavengers.weatherforecast.data.repository.NetworkState
import com.theavengers.weatherforecast.data.vo.WeatherForecastDetails
import io.reactivex.disposables.CompositeDisposable

class WeatherForecastViewModel (private val weatherRepository : WeatherForecastDetailsRepository, weatherId : Int) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val  weatherForecastDetails : LiveData<WeatherForecastDetails> by lazy {
        weatherRepository.fetchSingleWeatherForecastDetails(compositeDisposable , weatherId)
    }

    val networkState : LiveData<NetworkState> by lazy {
        weatherRepository.getWeatherForecastDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}