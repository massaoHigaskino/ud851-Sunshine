package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO (2) Display the weather forecast that was passed from MainActivity
        String extra = null;
        Intent intent = getIntent();
        if(intent != null) {
            extra = intent.getStringExtra(FORECAST_SHARE_HASHTAG);
        }
        if(extra != null && !extra.isEmpty()) {
            TextView mWeatherDetailTextView = (TextView) findViewById(R.id.tv_weather_detail);
            mWeatherDetailTextView.setText(extra);
        }
    }
}