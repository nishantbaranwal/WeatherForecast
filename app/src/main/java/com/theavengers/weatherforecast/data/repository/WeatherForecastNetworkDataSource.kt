package com.theavengers.weatherforecast.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theavengers.weatherforecast.data.api.API_KEY
import com.theavengers.weatherforecast.data.api.WeatherForecastInterface
import com.theavengers.weatherforecast.data.vo.WeatherForecastDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherForecastNetworkDataSource(private val apiService: WeatherForecastInterface, private val compositeDisposable: CompositeDisposable) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedWeatherForecastDetailsResponse = MutableLiveData<WeatherForecastDetails>()
    val downloadedWeatherForecastDetailsResponse : LiveData<WeatherForecastDetails>
        get() = _downloadedWeatherForecastDetailsResponse

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun fetchWeatherForecastDetails(movieId: Int){

        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getForecastDetails("Bangalore",7, API_KEY).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe({
                        _downloadedWeatherForecastDetailsResponse.postValue(it)
                        _networkState.postValue(NetworkState.LOADED)
                    },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                        })
            )
        }
        catch ( e: Exception) {
            Log.d("WeatherForecastDetails", e.message)
        }

    }
}