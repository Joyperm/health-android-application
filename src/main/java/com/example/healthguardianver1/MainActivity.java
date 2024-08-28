package com.example.healthguardianver1;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;


import com.example.healthguardianver1.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarItemView;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonWaterIntake,buttonStepCounter,buttonWeather;
    private LinearLayout WaterIntake,stepCounter,Weather;

    public BottomNavigationView bottomNavigationView;
//    public NavigationBarItemView bottomNavigationView

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this activity
        setContentView(R.layout.activity_main);

        // Initialize variable
        buttonWaterIntake = findViewById(R.id.buttonWaterIntake);
        WaterIntake = findViewById(R.id.WaterIntake);

        buttonStepCounter = findViewById(R.id.buttonStepCounter);
        stepCounter = findViewById(R.id.stepCounter);

        buttonWeather = findViewById(R.id.buttonWeather);
        Weather = findViewById(R.id.Weather);

        //nav bar
        bottomNavigationView = findViewById(R.id.bottom_nav);

        // Set onClickListener for buttonWaterIntake
        buttonWaterIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WaterIntakeActivity.class);
                startActivity(intent);
            }
        });

        WaterIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WaterIntakeActivity.class);
                startActivity(intent);
            }
        });

        buttonStepCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, stepCounter.class);
                startActivity(intent);
            }
        });


        stepCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, stepCounter.class);
                startActivity(intent);
            }
        });

        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, weather.class);
                startActivity(intent);
            }
        });

        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, weather.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            // do stuff
            if(item.getItemId() == R.id.home){
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else if(item.getItemId() == R.id.weather){
                Intent intent = new Intent(MainActivity.this, weather.class);
                startActivity(intent);
            }
            else if(item.getItemId() == R.id.about){
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }

            return true;
        });
    }





}
