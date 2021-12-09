package ru.sfedu.model;

import java.util.Objects;

public class EmployeeRequest {
    private String workExperience;
    private String education;
    private Boolean resume;
    private String areaWork;
    private String post;
    private String city;
    private Boolean workRemotely;

    public EmployeeRequest(){}

    public EmployeeRequest(String workExperience, String education, Boolean resume, String areaWork, String post, String city, Boolean workRemotely) {
        this.workExperience = workExperience;
        this.education = education;
        this.resume = resume;
        this.areaWork = areaWork;
        this.post = post;
        this.city = city;
        this.workRemotely = workRemotely;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Boolean getResume() {
        return resume;
    }

    public void setResume(Boolean resume) {
        this.resume = resume;
    }

    public String getAreaWork() {
        return areaWork;
    }

    public void setAreaWork(String areaWork) {
        this.areaWork = areaWork;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getWorkRemotely() {
        return workRemotely;
    }

    public void setWorkRemotely(Boolean workRemotely) {
        this.workRemotely = workRemotely;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeRequest)) return false;
        EmployeeRequest that = (EmployeeRequest) o;
        return getWorkExperience().equals(that.getWorkExperience()) &&
                getEducation().equals(that.getEducation()) &&
                getResume().equals(that.getResume()) &&
                getAreaWork().equals(that.getAreaWork()) &&
                getPost().equals(that.getPost()) &&
                getCity().equals(that.getCity()) &&
                getWorkRemotely().equals(that.getWorkRemotely());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWorkExperience(), getEducation(), getResume(), getAreaWork(), getPost(), getCity(), getWorkRemotely());
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "workExperience='" + workExperience + '\'' +
                ", education='" + education + '\'' +
                ", resume=" + resume +
                ", areaWork='" + areaWork + '\'' +
                ", post='" + post + '\'' +
                ", city='" + city + '\'' +
                ", workRemotely=" + workRemotely +
                '}';
    }
}
