package com.theavengers.weatherforecast.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.theavengers.weatherforecast.R
import com.theavengers.weatherforecast.data.api.IMAGE_URL
import com.theavengers.weatherforecast.data.vo.WeatherForecastDetails
import com.theavengers.weatherforecast.utility.Kelvin_to_Celsius
import kotlinx.android.synthetic.main.activity_temperature.*


class TemperatureActivity : AppCompatActivity() {
    private var weatherDetails: WeatherForecastDetails ?= null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)
        val gson = Gson()
        val strObj = intent.getStringExtra("WeatherForecast")
        weatherDetails = gson.fromJson(strObj, WeatherForecastDetails::class.java)
        if(weatherDetails!=null){
            temperatureTextView1.text = "Temp: " + Kelvin_to_Celsius(weatherDetails!!.weatherForeCastList[1].temp.eve).toString()+"°C"
            locationTextView1.text = "Bengaluru"
            conditionTextView1.text = weatherDetails!!.weatherForeCastList[1].weather[0].description
            Glide.with(this)
                .load(IMAGE_URL + weatherDetails!!.weatherForeCastList[1].weather[0].icon+".png")
                .apply(RequestOptions().override(600).centerCrop())
                .into(weatherIconImageView1)


            temperatureTextView2.text = "Temp: " + Kelvin_to_Celsius(weatherDetails!!.weatherForeCastList[2].temp.eve).toString()+"°C"
            locationTextView2.text = "Bengaluru"
            conditionTextView2.text = weatherDetails!!.weatherForeCastList[2].weather[0].description
            Glide.with(this)
                .load(IMAGE_URL + weatherDetails!!.weatherForeCastList[2].weather[0].icon+".png")
                .apply(RequestOptions().override(600).centerCrop())
                .into(weatherIconImageView2)

            temperatureTextView3.text = "Temp: " + Kelvin_to_Celsius(weatherDetails!!.weatherForeCastList[3].temp.eve).toString()+"°C"
            locationTextView3.text = "Bengaluru"
            conditionTextView3.text = weatherDetails!!.weatherForeCastList[3].weather[0].description
            Glide.with(this)
                .load(IMAGE_URL + weatherDetails!!.weatherForeCastList[3].weather[0].icon+".png")
                .apply(RequestOptions().override(600).centerCrop())
                .into(weatherIconImageView3)

            temperatureTextView4.text = "Temp: " + Kelvin_to_Celsius(weatherDetails!!.weatherForeCastList[4].temp.eve).toString()+"°C"
            locationTextView4.text = "Bengaluru"
            conditionTextView4.text = weatherDetails!!.weatherForeCastList[4].weather[0].description
            Glide.with(this)
                .load(IMAGE_URL + weatherDetails!!.weatherForeCastList[4].weather[0].icon+".png")
                .apply(RequestOptions().override(600).centerCrop())
                .into(weatherIconImageView4)

            temperatureTextView5.text = "Temp: " + Kelvin_to_Celsius(weatherDetails!!.weatherForeCastList[5].temp.eve).toString()+"°C"
            locationTextView5.text = "Bengaluru"
            conditionTextView5.text = weatherDetails!!.weatherForeCastList[5].weather[0].description
            Glide.with(this)
                .load(IMAGE_URL + weatherDetails!!.weatherForeCastList[5].weather[0].icon+".png")
                .apply(RequestOptions().override(600).centerCrop())
                .into(weatherIconImageView5)

            temperatureTextView6.text = "Temp: " + Kelvin_to_Celsius(weatherDetails!!.weatherForeCastList[6].temp.eve).toString()+"°C"
            locationTextView6.text = "Bengaluru"
            conditionTextView6.text = weatherDetails!!.weatherForeCastList[6].weather[0].description
            Glide.with(this)
                .load(IMAGE_URL + weatherDetails!!.weatherForeCastList[6].weather[0].icon+".png")
                .apply(RequestOptions().override(600).centerCrop())
                .into(weatherIconImageView6)
        }
    }
}
