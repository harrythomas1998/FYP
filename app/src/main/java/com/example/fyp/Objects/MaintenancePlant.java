package com.example.fyp.Objects;

public class MaintenancePlant {

    private String name;
    private String image;
    private String link;
    private String care;

    public MaintenancePlant() {
    }

    public MaintenancePlant(String name, String image, String link, String care) {
        this.name = name;
        this.image = image;
        this.link = link;
        this.care = care;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
    }
}
