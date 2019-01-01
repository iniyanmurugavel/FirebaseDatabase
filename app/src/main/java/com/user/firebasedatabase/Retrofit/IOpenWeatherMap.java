package com.user.firebasedatabase.Retrofit;


import com.user.firebasedatabase.Pojo.Weather.WeatherResult;
import com.user.firebasedatabase.Pojo.WeatherForecast.WeatherForecastResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherMap {

    @GET("weather?")
    Observable<WeatherResult> getWeatherByLatLng
            (@Query("lat") double lat, @Query("lon") double lng,
             @Query("appid") String appid, @Query("units") String unit);



    @GET("forecast?")
    Observable<WeatherForecastResult> getWeatherForecastResultLatLng
            (@Query("lat") double lat, @Query("lon") double lng,
             @Query("appid") String appid, @Query("units") String unit);
}
 