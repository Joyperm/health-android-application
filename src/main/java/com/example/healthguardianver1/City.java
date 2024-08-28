package com.example.healthguardianver1;

public class City {
    private String name;
    private String temperature;
    private String iconUrl;

    public City(String name, String temperature, String iconUrl) {
        this.name = name;
        this.temperature = temperature;
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
