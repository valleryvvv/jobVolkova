package ru.sfedu.model;

import com.opencsv.bean.CsvBindByName;
import ru.sfedu.Constants;

import java.io.Serializable;
import java.util.Objects;

public class ProfileEmployee extends Profile{

    @CsvBindByName(column = "workExperience")
    private String workExperience;

    @CsvBindByName(column = "education")
    private String education;

    @CsvBindByName(column = "resume")
    private String resume;

    @CsvBindByName(column = "areaWork")
    private String areaWork;

    @CsvBindByName(column = "post")
    private String post;

    @CsvBindByName(column = "workRemotely")
    private Boolean workRemotely;

    public ProfileEmployee(){}

    public ProfileEmployee(long userId, String name,
                           String lastName, String birthday, String city,
                           int rating, boolean employer, String workExperience,
                           String education, String resume, String areaWork,
                           String post, Boolean workRemotely) {
        super(userId, name, lastName, birthday, city, rating, employer);
        this.workExperience = workExperience;
        this.education = education;
        this.resume = resume;
        this.areaWork = areaWork;
        this.post = post;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
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

    public Boolean getWorkRemotely() {
        return workRemotely;
    }

    public void setWorkRemotely(Boolean workRemotely) {
        this.workRemotely = workRemotely;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileEmployee)) return false;
        if (!super.equals(o)) return false;
        ProfileEmployee that = (ProfileEmployee) o;
        return getWorkExperience().equals(that.getWorkExperience()) &&
                getEducation().equals(that.getEducation()) &&
                getResume().equals(that.getResume()) &&
                getAreaWork().equals(that.getAreaWork()) &&
                getPost().equals(that.getPost()) &&
                getWorkRemotely().equals(that.getWorkRemotely());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWorkExperience(), getEducation(), getResume(), getAreaWork(), getPost(), getWorkRemotely());
    }

    @Override
    public String toString() {
        return "ProfileEmployee{" +
                "workExperience='" + workExperience + '\'' +
                ", education='" + education + '\'' +
                ", resume='" + resume + '\'' +
                ", areaWork='" + areaWork + '\'' +
                ", post='" + post + '\'' +
                ", workRemotely=" + workRemotely +
                '}';
    }
}
