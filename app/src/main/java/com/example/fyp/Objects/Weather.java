package com.example.fyp.Objects;



public class Weather {


    private String weatherType;
    private String time;
    private double temperature;
    private String date;


    public Weather(String weatherType, String time, int temperature, String date) {
        this.weatherType = weatherType;
        this.time = time;
        this.temperature = temperature;
        this.date = date;
    }


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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
