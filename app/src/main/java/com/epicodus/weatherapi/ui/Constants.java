package com.epicodus.weatherapi.ui;

import com.epicodus.weatherapi.BuildConfig;

/**
 * Created by Guest on 4/25/16.
 */
public class Constants {
    public static final String OPEN_WEATHER_CONSUMER_KEY = BuildConfig.OPEN_WEATHER_CONSUMER_KEY;

    public static final String WEATHER_BASE_URL_FRONT = "http://api.openweathermap.org/data/2.5/forecast?q=";
    public static final String WEATHER_BASE_URL_BACK = ",us&mode=json&appid=";

    public static final String WEATHER_QUERY_PARAMETER = "city";
}
