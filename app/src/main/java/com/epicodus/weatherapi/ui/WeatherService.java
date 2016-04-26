package com.epicodus.weatherapi.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;

/**
 * Created by Guest on 4/26/16.
 */
public class WeatherService {

    public static void findForecast(String city, Callback callback) {
        String CONSUMER_KEY = Constants.OPEN_WEATHER_CONSUMER_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WEATHER_BASE_URL_FRONT).newBuilder();
        urlBuilder.addQueryParameter(Constants.WEATHER_QUERY_PARAMETER, city);
        String url = urlBuilder.build().toString();
        String urlComplete = url + Constants.WEATHER_BASE_URL_BACK + CONSUMER_KEY;

        Request request = new Request.Builder()
                .url(urlComplete)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> weather = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject weatherJSON = new JSONObject(jsonData);
                JSONArray listJSON = weatherJSON.getJSONArray("city");

                for (int i = 0; i < listJSON.length(); i++) {
                    JSONObject forecastJSON = listJSON.getJSONObject(i);
                    JSONArray forecastArrayJSON = forecastJSON.getJSONArray("list");
                    String name = forecastJSON.getString("name");
                    String country = forecastJSON.getString("country");
                    double latitude = forecastJSON.getJSONObject("coord").getDouble("lat");
                    double longitude = forecastJSON.getJSONObject("coord").getDouble("lon");
                    double temp = forecastArrayJSON.getJSONObject(Integer.parseInt("main")).getDouble("temp");
                    double humidity = forecastArrayJSON.getJSONObject(Integer.parseInt("main")).getDouble("humidity");

                    String weatherString = forecastArrayJSON.getJSONObject(Integer.parseInt("weather")).getString("description");

                    Weather weatherObject = new Weather(name, country, latitude, longitude, temp, humidity, weatherString);
                    weather.add(weatherObject);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
