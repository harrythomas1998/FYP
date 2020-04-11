package com.example.fyp.Objects;

public class OtherDetails {

    String orientation, area;

    public OtherDetails(){

    }

    public OtherDetails(String orientation, String area) {

        this.orientation = orientation;
        this.area = area;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
