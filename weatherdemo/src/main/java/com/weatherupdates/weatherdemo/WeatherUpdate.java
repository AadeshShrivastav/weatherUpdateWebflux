package com.weatherupdates.weatherdemo;

import java.time.Instant;

public class WeatherUpdate {
    private String id;
    private String city;
    private double temperature;
    private String condition;
    private Instant timestamp;

    public WeatherUpdate() {

    }

    public WeatherUpdate(String id, String city, double temperature, String condition, Instant timestamp) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
