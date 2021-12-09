package ru.sfedu.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Vacancy {

    @CsvBindByName(column = "vacancyId")
    private long vacancyId;

    @CsvBindByName(column = "idProfileEmployer")
    private long idProfileEmployer;

    @CsvBindByName(column = "areaWork")
    private String areaWork;

    @CsvBindByName(column = "titleWork")
    private String titleWork;

    @CsvBindByName(column = "description")
    private String description;

    @CsvBindByName(column = "city")
    private String city;

    @CsvBindByName(column = "remotely")
    private  Boolean remotely;

    @CsvBindByName(column = "deadline")
    private String deadline;

    @CsvBindByName(column = "price")
    private float price;

    public Vacancy(){}

    public Vacancy(long vacancyId, long idProfileEmployer, String areaWork, String titleWork, String description, String city, Boolean remotely, String deadline, float price) {
        this.vacancyId = vacancyId;
        this.idProfileEmployer = idProfileEmployer;
        this.areaWork = areaWork;
        this.titleWork = titleWork;
        this.description = description;
        this.city = city;
        this.remotely = remotely;
        this.deadline = deadline;
        this.price = price;
    }

    public long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public long getIdProfileEmployer() {
        return idProfileEmployer;
    }

    public void setIdProfileEmployer(long idProfileEmployer) {
        this.idProfileEmployer = idProfileEmployer;
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

    public Boolean getRemotely() {
        return remotely;
    }

    public void setRemotely(Boolean remotely) {
        this.remotely = remotely;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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
        if (!(o instanceof Vacancy)) return false;
        Vacancy vacancy = (Vacancy) o;
        return getVacancyId() == vacancy.getVacancyId() &&
                getIdProfileEmployer() == vacancy.getIdProfileEmployer() &&
                Float.compare(vacancy.getPrice(), getPrice()) == 0 &&
                getAreaWork().equals(vacancy.getAreaWork()) &&
                getTitleWork().equals(vacancy.getTitleWork()) &&
                getDescription().equals(vacancy.getDescription()) &&
                getCity().equals(vacancy.getCity()) &&
                getRemotely().equals(vacancy.getRemotely()) &&
                getDeadline().equals(vacancy.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVacancyId(), getIdProfileEmployer(), getAreaWork(), getTitleWork(), getDescription(), getCity(), getRemotely(), getDeadline(), getPrice());
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "vacancyId=" + vacancyId +
                ", idProfileEmployer=" + idProfileEmployer +
                ", areaWork='" + areaWork + '\'' +
                ", titleWork='" + titleWork + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", remotely=" + remotely +
                ", deadline='" + deadline + '\'' +
                ", price=" + price +
                '}';
    }
}
