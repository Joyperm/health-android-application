package com.example.healthguardianver1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            // do stuff
            if(item.getItemId() == R.id.home){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else if(item.getItemId() == R.id.weather){
                Intent intent = new Intent(this, weather.class);
                startActivity(intent);
            }
            else if(item.getItemId() == R.id.about){
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
            }

            return true;
        });
    }
}