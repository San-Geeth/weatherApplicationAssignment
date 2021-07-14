package com.example.weatherapplication.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather?&appid=d554e9be5d86e6b811be10429412d3c1&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);
}
