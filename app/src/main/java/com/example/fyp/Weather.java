package com.example.fyp;



public class Weather {


    String weatherType;
    String time;
    String temperature;


    public Weather(String weatherType, String time, String temperature) {
        this.weatherType = weatherType;
        this.time = time;
        this.temperature = temperature;
    }

    public Weather(){}


    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
