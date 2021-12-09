package ru.sfedu.dataProvider;

import ru.sfedu.model.*;

import java.util.List;

public interface Interface {

    List<ProfileEmployee> employeeSearch(EmployeeRequest employeeRequest);
    List<Vacancy> partTimeJobSearch(JobRequest jobRequest);
    void changeRating(long userId, int newRating);
    Status makeFeedback(Feedback feedback);
    void editProfile(long userId, Profile profile);
    void createProfileEmployee(ProfileEmployee profileEmployee);
    Status createProfileEmployer(ProfileEmployer profileEmployer);
    void postVacancy(Vacancy vacancy);

    List<Profile> selectProfile();
    Profile profileGetById(long userId);
    void insertProfile(Profile profile);
    void deleteProfileById(long userId);

    List<Feedback> selectFeedback();
    Feedback feedbackGetById(long feedbackId);

    void deleteFeedbackById(long feedbackId);
    void updateFeedbackById(long feedbackId, Feedback feedback);

    List<ProfileEmployee> selectProfileEmployee();
    List<ProfileEmployer> selectProfileEmployer();
    List<Vacancy> selectVacancy();

    ProfileEmployee profileEmployeeGetById(long profileEmployee);
    ProfileEmployer profileEmployerGetById(long profileEmployerId);
    Vacancy vacancyGetById(long vacancyId);

    void deleteProfileEmployeeBuId(long userId);
    void deleteProfileEmployerById(long userId);
    void deleteVacancyById(long vacancyId);

    void updateProfileEmployeeById(long userId, ProfileEmployee profileEmployee);
    void updateProfileEmployerById(long userId, ProfileEmployer profileEmployer);
    void updateVacancyById(long vacancyId, Vacancy vacancy);

}
