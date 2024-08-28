package com.example.healthguardianver1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class stepCounter extends AppCompatActivity {

    private TextView tv_stepsTaken, goal;
    private Spinner spinner;
    private Handler handler = new Handler();
    private int simulatedSteps = 0;
    private int stepGoal = 0;
    private boolean goalReached = false;

    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        tv_stepsTaken = findViewById(R.id.tv_stepsTaken);
        goal = findViewById(R.id.goal);
        spinner = findViewById(R.id.spinner2);

        // Set up the spinner listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateGoalText(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Initialize the goal text
        updateGoalText(spinner.getSelectedItemPosition());

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

    private void updateGoalText(int position) {
        if (position == 0) {
            goal.setText("You must select your goal");
            return;
        }

        switch (position) {
            case 1:
                stepGoal = 10;
                break;
            case 2:
                stepGoal = 50;
                break;
            case 3:
                stepGoal = 70;
                break;
            default:
                goal.setText("You must select your goal");
                return;
        }

        String goalText = spinner.getSelectedItem().toString();
        goal.setText(String.format("Goal for %s is %d steps", goalText, stepGoal));
        tv_stepsTaken.setText(String.valueOf(simulatedSteps));
        goalReached = false; // Reset the goalReached flag when goal is updated
    }

    private Runnable simulateSteps = new Runnable() {
        @Override
        public void run() {
            simulatedSteps++;
            tv_stepsTaken.setText(String.format("%d", simulatedSteps));

            // Check if the simulated steps have reached or exceeded the goal
            if (simulatedSteps >= stepGoal && !goalReached && stepGoal > 0) {
                Toast.makeText(stepCounter.this, "Congratulations! You've reached your goal!", Toast.LENGTH_SHORT).show();
                goalReached = true; // Set the flag to avoid multiple Toast messages
            }

            handler.postDelayed(this, 1000); // Simulate a step every second
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(simulateSteps, 1000); // Start simulating steps when the activity is resumed
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(simulateSteps); // Stop simulation when the activity is paused
    }
}
