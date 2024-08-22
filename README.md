# Health Guardian Android Application Built with Java

## Technologies Used

- **SharedPreferences**: Saves and retrieves key-value pairs of data in persistent storage. The app uses SharedPreferences to save the user's water goal and total water consumed between app sessions. The data remains consistent when users navigate across features.
- **BottomNavigationView**: Creates a navigation bar with tab items at the bottom of the screen. The app uses a BottomNavigationView for navigation between different activities such as Home, Weather, and About.
- **Toast**: A pop-up notification to inform the user (e.g., congratulating them on reaching their goal).
- **RecyclerView**: Displays a scrollable list of items (cities with weather data).
- **Volley**: Used to make HTTP requests to the OpenWeatherMap API to fetch weather data.
- **Glide**: An image loading library used to load and display weather icons from the API response.

## External API

- **OpenWeatherMap API**: This API provides access to weather data for various locations around the world.

## Features Completed

- **Water Intake**: Allows users to set daily water intake goals and log their consumption throughout the day.
  
- **Step Counter**: Users can set daily step goals and receive notifications on their progress.
  - Due to the inability to test with a real sensor on a physical Android device, I’ve simulated through hard-coded values, and the sensor-related methods are not fully implemented.

- **Weather**: Provides recommendations on whether it is a good day to exercise outdoors based on the current weather conditions.
