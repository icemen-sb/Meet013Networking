package ru.relastic.meet013networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final String VERSION_API = "2.5/";
    private static final String BASE_URL = "http://api.openweathermap.org/data/";
    public static final String PATH_CURRENT = "weather";
    public static final String PATH_FORECAST = "forecast";
    public static final String APPID = "72bda240bd2e87975920efda89b36efa";
    RetrofitHelper () {

    }
    public WeatherService getService() {
        Gson gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL+VERSION_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();
        return retrofit.create(WeatherService.class);
    }
}
