package ru.sfedu.model;

import java.util.Objects;

public class JobRequest {
    private String areaWork;
    private String titleWork;
    private String description;
    private String city;
    private float price;

    public JobRequest(){}

    public JobRequest(String areaWork, String titleWork, String description, String city, float price) {
        this.areaWork = areaWork;
        this.titleWork = titleWork;
        this.description = description;
        this.city = city;
        this.price = price;
    }

    public String getAreaWork() {
        return areaWork;
    }

    public void setAreaWork(String areaWork) {
        this.areaWork = areaWork;
    }

    public String getTitleWork() {
        return titleWork;
    }

    public void setTitleWork(String titleWork) {
        this.titleWork = titleWork;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobRequest)) return false;
        JobRequest that = (JobRequest) o;
        return Float.compare(that.getPrice(), getPrice()) == 0 &&
                getAreaWork().equals(that.getAreaWork()) &&
                getTitleWork().equals(that.getTitleWork()) &&
                getDescription().equals(that.getDescription()) &&
                getCity().equals(that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAreaWork(), getTitleWork(), getDescription(), getCity(), getPrice());
    }

    @Override
    public String toString() {
        return "JobRequest{" +
                "areaWork='" + areaWork + '\'' +
                ", titleWork='" + titleWork + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", price=" + price +
                '}';
    }
}
