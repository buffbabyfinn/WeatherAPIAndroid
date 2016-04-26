package com.epicodus.weatherapi.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.weatherapi.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.forecastButton) Button mForecastButton;
    @Bind(R.id.locationInput) EditText mLocationInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mForecastButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mForecastButton) {
            String city = mLocationInput.getText().toString();
            Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
            intent.putExtra("city", city);
            startActivity(intent);
        }
    }
}
