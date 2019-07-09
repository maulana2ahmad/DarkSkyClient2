package com.redudant.darkskyclient2.services;

import com.redudant.darkskyclient2.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherService {

    //https://api.darksky.net/forecast/f0f6756cd3ccee165333fa3b80c4bed3/37.8267,-122.4233
    @GET(".")
    Call<Weather> getWeather();
}
