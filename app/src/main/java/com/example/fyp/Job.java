package com.example.fyp;

public class Job {

    String date, time, weatherType, title, description;
    double temp;


    public Job() {
    }

    public Job(String date, String time, String weatherType, String title, String description, double temp) {
        this.date = date;
        this.time = time;
        this.weatherType = weatherType;
        this.title = title;
        this.description = description;
        this.temp = temp;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
