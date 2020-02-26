package com.theavengers.weatherforecast.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.theavengers.weatherforecast.R
import com.theavengers.weatherforecast.data.api.IMAGE_URL
import com.theavengers.weatherforecast.data.api.WeatherForecastClient
import com.theavengers.weatherforecast.data.repository.NetworkState
import com.theavengers.weatherforecast.data.vo.WeatherForecastDetails
import com.theavengers.weatherforecast.utility.Kelvin_to_Celsius
import kotlinx.android.synthetic.main.activity_weather_forecast.*

class WeatherForecastActivity : AppCompatActivity() {

    private var x1: Float? = 0.0f
    private var x2: Float?=0.0f
    private var y1: Float?=0.0f
    private var y2: Float?=0.0f
    var weatherDetails: WeatherForecastDetails?=null
    private lateinit var viewModel : WeatherForecastViewModel
    private lateinit var weatherForecastDetailsRepository : WeatherForecastDetailsRepository


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)


        val apiService = WeatherForecastClient.getClient()
        weatherForecastDetailsRepository = WeatherForecastDetailsRepository(apiService)
        viewModel = getViewModel(0)
        viewModel.weatherForecastDetails.observe(this, Observer {
            bindUI(it)
            weatherDetails = it
        })
        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if(it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if(it == NetworkState.LOADING) View.VISIBLE else View.GONE
        })
    }

    private fun  bindUI(weatherDetails: WeatherForecastDetails){

        temperatureTextView.text = "Temp: " + Kelvin_to_Celsius(weatherDetails.weatherForeCastList[0].temp.eve).toString()+"°C"
        locationTextView.setText("Bengaluru")
        Glide.with(this)
            .load(IMAGE_URL + weatherDetails.weatherForeCastList[0].weather[0].icon+".png")
            .into(weatherIconImageView)
        conditionTextView.setText(weatherDetails.weatherForeCastList[0].weather[0].description)

    }

    private fun getViewModel(weatherId : Int): WeatherForecastViewModel{
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return WeatherForecastViewModel(weatherForecastDetailsRepository, weatherId ) as T
            }
        }) [WeatherForecastViewModel::class.java]
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event!!.action){
            MotionEvent.ACTION_DOWN-> {
                x1 = event.x
                y1 = event.y
            }
            MotionEvent.ACTION_UP->{
               x2 = event.x
               y2 = event.y
                if (x1 != null) {
                    if(y1!!>y2!! && weatherDetails!=null){
                        val intent = Intent(applicationContext,TemperatureActivity::class.java)
                        val gson = Gson()
                        intent.putExtra("WeatherForecast",gson.toJson(weatherDetails))
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                    }
                }
            }
        }
        return false
    }
}
