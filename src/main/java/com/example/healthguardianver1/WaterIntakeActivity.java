package com.example.healthguardianver1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WaterIntakeActivity extends AppCompatActivity {

    private EditText goalInput;
    private EditText waterInput;
    private TextView totalWaterText, goalLabel;

    private int waterGoal = 0;
    private int totalWaterConsumed = 0;
    private ImageView glassImageView;

    private static final String PREFS_NAME = "WaterIntakePrefs";
    private static final String KEY_WATER_GOAL = "waterGoal";
    private static final String KEY_TOTAL_WATER_CONSUMED = "totalWaterConsumed";

    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake);

        goalInput = findViewById(R.id.goalInput);
        waterInput = findViewById(R.id.waterInput);
        totalWaterText = findViewById(R.id.totalWaterText);
        goalLabel = findViewById(R.id.goalText);
        glassImageView = findViewById(R.id.glassImageView);
        Button setGoalButton = findViewById(R.id.setGoalButton);
        Button addWaterButton = findViewById(R.id.addWaterButton);
        Button resetButton = findViewById(R.id.resetButton);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        // Load saved data from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        waterGoal = prefs.getInt(KEY_WATER_GOAL, 0);
        totalWaterConsumed = prefs.getInt(KEY_TOTAL_WATER_CONSUMED, 0);

        goalLabel.setText("Goal: " + waterGoal + " ml");
        totalWaterText.setText("Total Water Consumed: " + totalWaterConsumed + " ml");
        updateWaterDisplay();

        setGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goalText = goalInput.getText().toString();
                if (!goalText.isEmpty()) {
                    waterGoal = Integer.parseInt(goalText);
                    Toast.makeText(WaterIntakeActivity.this, "Goal set to " + waterGoal + " ml", Toast.LENGTH_SHORT).show();
                    goalLabel.setText("Goal: " + waterGoal + " ml");
                    updateWaterDisplay();
                    saveData(); // Save data to SharedPreferences
                } else {
                    Toast.makeText(WaterIntakeActivity.this, "Please enter a goal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addWaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String waterText = waterInput.getText().toString();
                if (!waterText.isEmpty()) {
                    int waterAmount = Integer.parseInt(waterText);
                    totalWaterConsumed += waterAmount;
                    totalWaterText.setText("Total Water Consumed: " + totalWaterConsumed + " ml");
                    Toast.makeText(WaterIntakeActivity.this, "Water Added💧", Toast.LENGTH_SHORT).show();
                    updateWaterDisplay();
                    saveData(); // Save data to SharedPreferences

                    if (totalWaterConsumed >= waterGoal) {
                        Toast.makeText(WaterIntakeActivity.this, "Congratulations! You've reached your goal!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(WaterIntakeActivity.this, "Please enter water intake", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterGoal = 0;
                totalWaterConsumed = 0;
                goalInput.setText("");
                waterInput.setText("");
                goalLabel.setText("Goal: " + waterGoal + " ml");
                totalWaterText.setText("Total Water Consumed: " + totalWaterConsumed + " ml");
                glassImageView.setImageResource(R.drawable.glass_empty);
                saveData(); // Save data to SharedPreferences
            }
        });


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

    private void updateWaterDisplay() {
        totalWaterText.setText("Total Water Consumed: " + totalWaterConsumed + " ml");

        if (totalWaterConsumed >= waterGoal) {
            glassImageView.setImageResource(R.drawable.glass_full);
        } else if (totalWaterConsumed >= waterGoal * 0.75) {
            glassImageView.setImageResource(R.drawable.glass_75);
        } else if (totalWaterConsumed >= waterGoal * 0.50) {
            glassImageView.setImageResource(R.drawable.glass_50);
        } else if (totalWaterConsumed >= waterGoal * 0.25) {
            glassImageView.setImageResource(R.drawable.glass_25);
        } else {
            glassImageView.setImageResource(R.drawable.glass_empty);
        }
    }

    private void saveData() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_WATER_GOAL, waterGoal);
        editor.putInt(KEY_TOTAL_WATER_CONSUMED, totalWaterConsumed);
        editor.apply();
    }


}
