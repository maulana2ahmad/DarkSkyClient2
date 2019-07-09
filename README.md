# DarkSkyClient2
Get API Weather use Retrofit stap first


## Result
![Screen Shot 2019-07-09 at 23 55 03](https://user-images.githubusercontent.com/43386555/60908248-97b47380-a2a5-11e9-830a-3de6cf3b29cd.png)

## Stap 1
 . Register https://darksky.net/dev
 . ![Screen Shot 2019-07-10 at 00 01 38](https://user-images.githubusercontent.com/43386555/60908391-f24dcf80-a2a5-11e9-98dc-abe09a098b7c.png)
 . Then select the console
 . Select Sample API Call

## Stap 2
  . Create this package ![Screen Shot 2019-07-10 at 00 05 49](https://user-images.githubusercontent.com/43386555/60908618-8455d800-a2a6-11e9-817b-866782c408aa.png)
  . Klik right -> New -> Select Package and creat Name package
  
## Stap 3
  . After selecting Sample API Call
  . Klik URL This ![Screen Shot 2019-07-10 at 00 12 11](https://user-images.githubusercontent.com/43386555/60909031-676dd480-a2a7-11e9-9e3a-0024f9ac71da.png)
  
  . Result from Url https://api.darksky.net/forecast/f0f6756cd3ccee165333fa3b80c4bed3/37.8267,-122.4233
  ![Screen Shot 2019-07-10 at 00 09 52](https://user-images.githubusercontent.com/43386555/60908869-18c03a80-a2a7-11e9-8dc3-10d95d8e6fc3.png)
  
  . Copy this JSON and paste in URL http://www.jsonschema2pojo.org
  . You can preview or directly download
  
## Stap 4
  . Follow it like this 
![Screen Shot 2019-07-10 at 00 17 22](https://user-images.githubusercontent.com/43386555/60909897-92592800-a2a9-11e9-8dfd-18fb8582097b.png)
    
## Stap 5
## Package -> MODEL
## Weather.Java
      package com.redudant.darkskyclient.models;

      import com.google.gson.annotations.Expose;
      import com.google.gson.annotations.SerializedName;

      public class Weather {

          @SerializedName("latitude")
          @Expose
          private Double latitude;
          @SerializedName("longitude")
          @Expose
          private Double longitude;
          @SerializedName("currently")
          @Expose
          private Currently currently;


          public Double getLatitude() {
              return latitude;
          }

          public void setLatitude(Double latitude) {
              this.latitude = latitude;
          }

          public Double getLongitude() {
              return longitude;
          }

          public void setLongitude(Double longitude) {
              this.longitude = longitude;
          }

          public Currently getCurrently() {
              return currently;
          }

          public void setCurrently(Currently currently) {
              this.currently = currently;
          }
      }
 
 ## Currently.Java
      package com.redudant.darkskyclient.models;

      import com.google.gson.annotations.Expose;
      import com.google.gson.annotations.SerializedName;

      public class Currently {

          @SerializedName("time")
          @Expose
          private Integer time;
          @SerializedName("summary")
          @Expose
          private String summary;
          @SerializedName("icon")
          @Expose
          private String icon;
          @SerializedName("temperature")
          @Expose
          private Double temperature;


          public Integer getTime() {
              return time;
          }

          public void setTime(Integer time) {
              this.time = time;
          }

          public String getSummary() {
              return summary;
          }

          public void setSummary(String summary) {
              this.summary = summary;
          }

          public String getIcon() {
              return icon;
          }

          public void setIcon(String icon) {
              this.icon = icon;
          }

          public Double getTemperature() {
              return temperature;
          }

          public void setTemperature(Double temperature) {
              this.temperature = temperature;
          }

      }
## Stap 6
## Package -> SERVICES
## WeatherService.Java
    package com.redudant.darkskyclient2.services;

    import com.redudant.darkskyclient2.model.Weather;

    import retrofit2.Call;
    import retrofit2.http.GET;

    public interface WeatherService {

        //https://api.darksky.net/forecast/f0f6756cd3ccee165333fa3b80c4bed3/37.8267,-122.4233
        @GET(".")
        Call<Weather> getWeather();
    }

## Stap 7
## Package -> View
## MainActivity.Java
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
