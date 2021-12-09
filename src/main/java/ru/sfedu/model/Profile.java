package ru.sfedu.model;

import com.opencsv.bean.CsvBindByName;
import ru.sfedu.Constants;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@Entity
//@Table(name= Constants.NAME_TABLE_PROFILE)
public class Profile implements Serializable {

    private Set<Feedback> feedback = new HashSet<Feedback>();

    public void addFeedback(Feedback newFeedback){
        feedback.add(newFeedback);
    }

    public Set<Feedback> getFeedback(){
        return feedback;
    }

    @CsvBindByName(column = Constants.PROFILE_USER_ID)
    private long userId;

    @CsvBindByName(column =Constants.PROFILE_NAME)
    private String name;

    @CsvBindByName(column =Constants.PROFILE_LAST_NAME)
    private String lastName;

    @CsvBindByName(column =Constants.PROFILE_BIRTHDAY)
    private String birthday;

    @CsvBindByName(column =Constants.PROFILE_CITY)
    private String city;

    @CsvBindByName(column =Constants.PROFILE_RATING)
    private int rating;

    @CsvBindByName(column =Constants.PROFILE_EMPLOYER)
    private boolean employer;

    public Profile(){}

    public Profile(long userId, String name, String lastName, String birthday, String city, int rating, boolean employer) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.city = city;
        this.rating = rating;
        this.employer = employer;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isEmployer() {
        return employer;
    }

    public void setEmployer(boolean employer) {
        this.employer = employer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return getUserId() == profile.getUserId() &&
                getRating() == profile.getRating() &&
                isEmployer() == profile.isEmployer() &&
                getName().equals(profile.getName()) &&
                getLastName().equals(profile.getLastName()) &&
                getBirthday().equals(profile.getBirthday()) &&
                getCity().equals(profile.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getName(), getLastName(), getBirthday(), getCity(), getRating(), isEmployer());
    }

    @Override
    public String toString() {
        return "Profile{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", city='" + city + '\'' +
                ", rating=" + rating +
                ", employer=" + employer +
                '}';
    }
}