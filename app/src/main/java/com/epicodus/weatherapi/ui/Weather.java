package com.epicodus.weatherapi.ui;

import java.util.ArrayList;

/**
 * Created by Guest on 4/26/16.
 */
public class Weather {
    private String mName;
    private String mCountry;
    private double mLatitude;
    private double mLongitude;
    private double mTemp;
    private double mHumidity;
    private String mWeather;

    public Weather(String name, String country, double latitude, double longitude, double temp, double humidity, String weather) {
        mName = name;
        mCountry = country;
        mLatitude = latitude;
        mLongitude = longitude;
        mTemp = temp;
        mHumidity = humidity;
        mWeather = weather;
    }

    public String getName() {
        return mName;
    }

    public String getCountry() {
        return mCountry;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public double getTemp() {
        return mTemp;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public String getWeather() {
        return mWeather;
    }
}

