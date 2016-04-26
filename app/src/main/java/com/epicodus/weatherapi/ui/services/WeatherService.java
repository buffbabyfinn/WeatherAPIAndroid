package com.epicodus.weatherapi.ui.services;

import android.util.Log;

import com.epicodus.weatherapi.ui.Constants;
import com.epicodus.weatherapi.ui.models.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 4/26/16.
 */
public class WeatherService {
    public static void findForecast(String city, Callback callback) {
        String CONSUMER_KEY = Constants.OPEN_WEATHER_CONSUMER_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WEATHER_BASE_URL_FRONT).newBuilder();
        String url = urlBuilder.toString();
        String urlComplete = url + city + Constants.WEATHER_BASE_URL_BACK + CONSUMER_KEY;

        Request request = new Request.Builder()
                .url(urlComplete)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> weather = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject weatherJSON = new JSONObject(jsonData);
                JSONObject forcastJSON = weatherJSON.getJSONObject("city");
                JSONArray listJSON = weatherJSON.getJSONArray("list");

                    String name = forcastJSON.getString("name");
                    String country = forcastJSON.getString("country");
                    double latitude = forcastJSON.getJSONObject("coord").getDouble("lat");
                    double longitude = forcastJSON.getJSONObject("coord").getDouble("lon");

                for (int i = 0; i < listJSON.length(); i++) {
                    JSONObject forecastJSON = listJSON.getJSONObject(i);
                    double temp = forecastJSON.getJSONObject("main").getDouble("temp");
                    int humidity = forecastJSON.getJSONObject("main").getInt("humidity");

                    String weatherString = forecastJSON.getJSONArray("weather").getJSONObject(0).getString("description");

                    Weather weatherObject = new Weather(name, country, latitude, longitude, temp, humidity, weatherString);
                    weather.add(weatherObject);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
