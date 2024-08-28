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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class weather extends AppCompatActivity {
    private TextView textViewTemperature, textViewDescription;
    private ImageView imageViewWeatherIcon;
    private Button btnGet;
    private EditText etCity;
    private RecyclerView recyclerViewCities;
    private CityAdapter cityAdapter;
    private List<City> cityList = new ArrayList<>();
    public BottomNavigationView bottomNavigationView;

    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "8547a57b129557267c0d50178c0db02f";
    DecimalFormat df = new DecimalFormat("#");

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "WeatherPrefs";
    private static final String LAST_CITY_KEY = "lastCity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewDescription = findViewById(R.id.textViewDescription);
        imageViewWeatherIcon = findViewById(R.id.WeatherIcon);
        btnGet = findViewById(R.id.btnGet);
        etCity = findViewById(R.id.etCity);
        recyclerViewCities = findViewById(R.id.recyclerViewCities);
        bottomNavigationView = findViewById(R.id.bottom_nav);

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


        // Set up RecyclerView
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter = new CityAdapter(cityList);
        recyclerViewCities.setAdapter(cityAdapter);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Load the last searched city
        String lastCity = sharedPreferences.getString(LAST_CITY_KEY, null);
        if (lastCity != null) {
            etCity.setText(lastCity);
            getWeatherDetailsForCity(lastCity);
        }

        // Initialize with popular cities
        List<String> popularCities = Arrays.asList("London","Bangkok", "Chiang Mai", "New York", "Los Angeles", "Chicago", "Houston", "Phoenix");
        fetchWeatherForCities(popularCities);

    }

    private void fetchWeatherForCities(List<String> cities) {
        cityList.clear(); // Clear the list before adding new items

        for (String city : cities) {
            String tempUrl = url + "?q=" + city + "&appid=" + appid;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        String icon = jsonObjectWeather.getString("icon");

                        String tempText = df.format(temp) + " °C";
                        String iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png"; // Weather icon URL
                        City cityObject = new City(city, tempText, iconUrl);
                        cityList.add(cityObject);

                        cityAdapter.notifyDataSetChanged(); // Notify adapter of data change



                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    public void getWeatherDetails(View view) {
        String city = etCity.getText().toString().trim();
        if (city.equals("")) {
            Toast.makeText(this, "City field can not be empty!", Toast.LENGTH_SHORT).show();
        } else {
            // Save the city to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(LAST_CITY_KEY, city);
            editor.apply();

            // Get weather details
            String tempUrl = url + "?q=" + city + "&appid=" + appid;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String output = "";
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        String icon = jsonObjectWeather.getString("icon");

                        output = df.format(temp) + " °C";
                        textViewTemperature.setText(output);

                        String iconImageURL = "https://openweathermap.org/img/wn/"+icon+"@2x.png";

                        textViewDescription.setText(description);
                        loadWeatherIcon(iconImageURL);

                        // Create an Intent to start the SummaryActivity
//                        Intent intent = new Intent(weather.this, SummaryActivity.class);
//                        intent.putExtra("TEMPERATURE", tempText);
//                        intent.putExtra("DESCRIPTION", description);
//                        startActivity(intent);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }


    private void getWeatherDetailsForCity(String city) {
        String tempUrl = url + "?q=" + city + "&appid=" + appid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    String icon = jsonObjectWeather.getString("icon");

                    String tempText = df.format(temp) + " °C";
                    textViewTemperature.setText(tempText);

                    String iconImageURL = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
                    textViewDescription.setText(description);
                    loadWeatherIcon(iconImageURL);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void loadWeatherIcon(String iconCode) {
        Glide.with(this).load(iconCode).into(imageViewWeatherIcon);
    }

}
