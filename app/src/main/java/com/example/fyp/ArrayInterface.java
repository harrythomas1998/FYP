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

    ArrayList<Plant> fullSunConifersList = new ArrayList<>();
    ArrayList<Plant> fullSunHedgesList = new ArrayList<>();
    ArrayList<Plant> fullSunFernsList = new ArrayList<>();
    ArrayList<Plant> fullSunBeddingList = new ArrayList<>();
    ArrayList<Plant> fullSunClimbersList = new ArrayList<>();
    ArrayList<Plant> fullSunExoticsList = new ArrayList<>();
    ArrayList<Plant> fullSunGrassesList = new ArrayList<>();
    ArrayList<Plant> fullSunAlpinesList = new ArrayList<>();


    ArrayList<Plant> halfConifersList = new ArrayList<>();
    ArrayList<Plant> halfHedgesList = new ArrayList<>();
    ArrayList<Plant> halfFernsList = new ArrayList<>();
    ArrayList<Plant> halfBeddingList = new ArrayList<>();
    ArrayList<Plant> halfClimbersList = new ArrayList<>();
    ArrayList<Plant> halfExoticsList = new ArrayList<>();
    ArrayList<Plant> halfGrassesList = new ArrayList<>();
    ArrayList<Plant> halfAlpinesList = new ArrayList<>();

    ArrayList<Plant> fullShadeList = new ArrayList<>();

    ArrayList<Plant> myPlants = new ArrayList<>();

    ArrayList<Job> jobs = new ArrayList<>();

    ArrayList<MaintenancePlant> summer = new ArrayList<>();
    ArrayList<MaintenancePlant> winter = new ArrayList<>();
    ArrayList<MaintenancePlant> spring = new ArrayList<>();
    ArrayList<MaintenancePlant> autumn = new ArrayList<>();
    ArrayList<MaintenancePlant> otherJobs = new ArrayList<>();

}
