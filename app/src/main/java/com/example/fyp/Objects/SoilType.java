package com.example.fyp.Objects;

public class SoilType {

    String name;
    String ph;
    String fertility;
    String comVeg;
    String climate;
    String drainage;
    String orientation;


    public SoilType() {
    }

    public SoilType(String name, String ph, String fertility, String comVeg, String climate, String drainage, String orientation) {
        this.name = name;
        this.ph = ph;
        this.fertility = fertility;
        this.comVeg = comVeg;
        this.climate = climate;
        this.drainage = drainage;
        this.orientation = orientation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getFertility() {
        return fertility;
    }

    public void setFertility(String fertility) {
        this.fertility = fertility;
    }

    public String getComVeg() {
        return comVeg;
    }

    public void setComVeg(String comVeg) {
        this.comVeg = comVeg;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getDrainage() {
        return drainage;
    }

    public void setDrainage(String drainage) {
        this.drainage = drainage;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
