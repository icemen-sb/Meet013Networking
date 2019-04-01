package ru.relastic.meet013networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Callback;


public class RetrofitApiMapper {

    private RetrofitHelper helper;

    public RetrofitApiMapper(RetrofitHelper helper) {
        this.helper = helper;
    }

    public void requestWeatherByCitiId(long citi_id, String token, Callback<WeatherEntity> callbacks) {
        helper.getService().getWeatherForecastByCitiId(citi_id, token).enqueue(callbacks);
    }
    public void requestWeatherByCitiIdCurrent(long citi_id, String token,
                                               Callback<WeatherEntityCurrent> callbacks) {
        helper.getService().getWeatherCurrentByCitiId(citi_id, token).enqueue(callbacks);
    }


    public static WeatherEntity convertJSON(String json_string) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json_string, WeatherEntity.class);
    }
    public static String convertToString(WeatherEntity wheather) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(wheather).toString();
    }
}
