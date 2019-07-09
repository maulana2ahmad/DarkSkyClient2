package com.redudant.darkskyclient2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.redudant.darkskyclient2.R;
import com.redudant.darkskyclient2.model.Currently;
import com.redudant.darkskyclient2.model.Weather;
import com.redudant.darkskyclient2.services.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("https://api.darksky.net/forecast/f0f6756cd3ccee165333fa3b80c4bed3/37.8267,-122.4233/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<Weather> weatherData = weatherService.getWeather();
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Currently currently = response.body().getCurrently();

                Log.e(TAG, "Temperatur: " + currently.getTemperature());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

    }

}
