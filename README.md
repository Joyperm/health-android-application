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

## Features

- **Water Intake**: Allows users to set daily water intake goals and log their consumption throughout the day.
  
- **Step Counter**: Users can set daily step goals and receive notifications on their progress.
  - Due to the inability to test with a real sensor on a physical Android device, I’ve simulated through hard-coded values, and the sensor-related methods are not fully implemented.

- **Weather**: Provides recommendations on whether it is a good day to exercise outdoors based on the current weather conditions.

## Example
![image](https://github.com/user-attachments/assets/9366e2c6-2f2a-4e07-9aab-cc3eb26b7128)
![image](https://github.com/user-attachments/assets/32f45383-01ac-4b98-8ddc-d1cfd5a6d15e)
![image](https://github.com/user-attachments/assets/8c44d213-d3bf-4e42-91c9-562871593aff)
![image](https://github.com/user-attachments/assets/af065706-d96d-46c8-b782-6963d7bf255b)
![image](https://github.com/user-attachments/assets/43b2f228-38f1-4554-ab91-2c2f26995023)
![image](https://github.com/user-attachments/assets/d1b0b1fc-8394-4437-ae98-638989810202)

https://vm.tiktok.com/ZMr7XbUuP/
