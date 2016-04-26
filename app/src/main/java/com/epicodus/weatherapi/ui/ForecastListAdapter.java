package com.epicodus.weatherapi.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.weatherapi.R;
import com.epicodus.weatherapi.ui.models.Weather;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/26/16.
 */
public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder> {

    private ArrayList<Weather> mWeatherArray = new ArrayList<>();
    private Context mContext;

    public ForecastListAdapter(Context context, ArrayList<Weather> weathers) {
        mContext = context;
        mWeatherArray = weathers;
    }

    @Override
    public ForecastListAdapter.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        ForecastViewHolder viewHolder = new ForecastViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastListAdapter.ForecastViewHolder holder, int position) {
        holder.bindForecast(mWeatherArray.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeatherArray.size();
    }


    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cityName) TextView mCityName;
        @Bind(R.id.countryName) TextView mCountryName;
        @Bind(R.id.latitude) TextView mLatitude;
        @Bind(R.id.longitude) TextView mLongitude;
        @Bind(R.id.temp) TextView mTemp;
        @Bind(R.id.humidity) TextView mHumidity;
        @Bind(R.id.description) TextView mDescription;
        private Context mContext;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindForecast(Weather weather) {
            mCityName.setText(weather.getName());
            mCountryName.setText(weather.getCountry());
            mLatitude.setText(weather.getLatitude() + ", ");
            mLongitude.setText(weather.getLongitude()+ "");
            mTemp.setText(weather.getTemp() + " degrees Fahrenheit");
            mHumidity.setText(weather.getHumidity() + "");
            mDescription.setText(weather.getWeather() + ".");
        }
    }
}
