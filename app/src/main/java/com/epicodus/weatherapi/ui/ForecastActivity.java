package com.epicodus.weatherapi.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.weatherapi.R;
import com.epicodus.weatherapi.ui.models.Weather;
import com.epicodus.weatherapi.ui.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForecastActivity extends AppCompatActivity {
    public static final String TAG = ForecastActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private ForecastListAdapter mAdapter;
    public ArrayList<Weather> mWeathersArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        getForecast(city);

        Toast.makeText(ForecastActivity.this, city, Toast.LENGTH_LONG).show();
    }

    private void getForecast(String city) {
        final WeatherService weatherService = new WeatherService();
        WeatherService.findForecast(city, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                       mWeathersArray = weatherService.processResults(response);

                        ForecastActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter = new ForecastListAdapter(getApplicationContext(), mWeathersArray);
                                mRecyclerView.setAdapter(mAdapter);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ForecastActivity.this);
                                mRecyclerView.setLayoutManager(layoutManager);
                                mRecyclerView.setHasFixedSize(true);
                            }
                        });

                    }
        });
    }
}
