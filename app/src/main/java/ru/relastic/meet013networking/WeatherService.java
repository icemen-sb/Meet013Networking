package ru.relastic.meet013networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherService {

    @Headers({"Content-Type: application/json"})
    @GET(RetrofitHelper.PATH_FORECAST)
    Call<WeatherEntity> getWeatherForecastByCitiId (@Query("id") long id,
                                                     @Query("appid") String appid);

    @Headers({"Content-Type: application/json"})
    @GET(RetrofitHelper.PATH_CURRENT)
    Call<WeatherEntityCurrent> getWeatherCurrentByCitiId (@Query("id") long id,
                                                           @Query("appid") String appid);

}
