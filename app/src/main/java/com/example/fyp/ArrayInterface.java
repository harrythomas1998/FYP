package com.example.fyp;

import com.example.fyp.Objects.Job;
import com.example.fyp.Objects.MaintenancePlant;
import com.example.fyp.Objects.Plant;
import com.example.fyp.Objects.Weather;

import java.util.ArrayList;

public interface ArrayInterface {

    //Weather Fragments
    ArrayList<Weather> mondayData = new ArrayList<>();
    ArrayList<Weather> tuesdayData = new ArrayList<>();
    ArrayList<Weather> wednesdayData = new ArrayList<>();
    ArrayList<Weather> thursdayData = new ArrayList<>();
    ArrayList<Weather> fridayData = new ArrayList<>();
    ArrayList<Weather> saturdayData = new ArrayList<>();
    ArrayList<Weather> sundayData = new ArrayList<>();

    //Plant Types
    ArrayList<Plant> conifers = new ArrayList<>();
    ArrayList<Plant> hedges = new ArrayList<>();
    ArrayList<Plant> ferns = new ArrayList<>();
    ArrayList<Plant> bedding = new ArrayList<>();
    ArrayList<Plant> climbers = new ArrayList<>();
    ArrayList<Plant> exotic = new ArrayList<>();
    ArrayList<Plant> grasses = new ArrayList<>();
    ArrayList<Plant> alpines = new ArrayList<>();

    ArrayList<Plant> myPlants = new ArrayList<>();

    ArrayList<Job> jobs = new ArrayList<>();

    ArrayList<MaintenancePlant> summer = new ArrayList<>();
    ArrayList<MaintenancePlant> winter = new ArrayList<>();
    ArrayList<MaintenancePlant> spring = new ArrayList<>();
    ArrayList<MaintenancePlant> autumn = new ArrayList<>();

}
