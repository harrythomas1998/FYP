package com.example.fyp.Objects;

public class Plant {

    private String name, soil, care, position, growth, picture;

    public Plant() {
    }

    public Plant(String name, String picture, String position, String soil, String growth, String care) {
        this.name = name;
        this.soil = soil;
        this.care = care;
        this.position = position;
        this.growth = growth;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
