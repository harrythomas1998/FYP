package com.example.fyp.Objects;



public class Weather {


    String weatherType;
    String time;
    double temperature;
    String date;


    public Weather(String weatherType, String time, int temperature, String date) {
        this.weatherType = weatherType;
        this.time = time;
        this.temperature = temperature;
        this.date = date;
    }


    public String getWeatherType() {
        return weatherType;
    }

    public String getTime() {
        return time;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDate() {
        return date;
    }
}
