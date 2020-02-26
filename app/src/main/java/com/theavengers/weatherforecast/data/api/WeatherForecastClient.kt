package com.theavengers.weatherforecast.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "362ec7516708e08ad641098c2a9d4a9c"
const val IMAGE_URL= "https://openweathermap.org/img/w/"
const val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/2.5/forecast/"

object WeatherForecastClient {
    fun getClient(): WeatherForecastInterface{
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().client(okHttpClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.openweathermap.org/").build()
        return retrofit .create(WeatherForecastInterface::class.java)
    }
}
