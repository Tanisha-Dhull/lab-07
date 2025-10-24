package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    public static final String EXTRA_CITY_NAME = "extra_city_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Pull the string we passed in the Intent
        String cityName = getIntent().getStringExtra(EXTRA_CITY_NAME);

        // Put that on screen
        TextView cityText = findViewById(R.id.text_selected_city);
        if (cityName != null) {
            cityText.setText(cityName);
        } else {
            // fallback so it doesn't crash silently if null
            cityText.setText("No city received");
        }

        // Hook up back button
        Button backBtn = findViewById(R.id.button_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close this Activity and return to MainActivity
                finish();
            }
        });
    }
}
