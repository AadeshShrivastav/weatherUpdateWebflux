package com.weatherupdates.weatherdemo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse {

    private String id;
    private String name;
    private Main main;
    private List<Weather> weather;

    public String getId() { return id; }
    public String getName() { return name; }
    public Main getMain() { return main; }
    public List<Weather> getWeather() { return weather; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        private double temp;
        public double getTemp() { return temp; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private String description;
        public String getDescription() { return description; }
    }
}
