package ru.sfedu.model;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ProfileEmployer extends Profile{

    @CsvBindByName(column = "nameCompany")
    private String nameCompany;

    public ProfileEmployer() {
    }

    public ProfileEmployer(long userId, String name, String lastName, String birthday, String city, int rating, boolean employer, String nameCompany) {
        super(userId, name, lastName, birthday, city, rating, employer);
        this.nameCompany = nameCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileEmployer)) return false;
        if (!super.equals(o)) return false;
        ProfileEmployer that = (ProfileEmployer) o;
        return getNameCompany().equals(that.getNameCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNameCompany());
    }

    @Override
    public String toString() {
        return "ProfileEmployer{" +
                "nameCompany='" + nameCompany + '\'' +
                '}';
    }
}
