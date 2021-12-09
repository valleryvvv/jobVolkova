package ru.sfedu.dataProvider;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Constants;
import ru.sfedu.model.*;
import utils.HistoryMongo;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.currentThread;

public class DataProviderCSV implements Interface {

    private static final Logger log = LogManager.getLogger(DataProviderCSV.class.getName());

    public List<ProfileEmployee> employeeSearch(EmployeeRequest employeeRequest){

        List<ProfileEmployee> bean = selectProfileEmployee().stream()
                .filter(beans -> (beans).getWorkExperience().equals(employeeRequest.getWorkExperience()) ||
                        (beans).getEducation().equals(employeeRequest.getEducation()) ||
                        (beans).getResume().equals(employeeRequest.getResume()) ||
                        (beans).getAreaWork().equals(employeeRequest.getAreaWork()) ||
                        (beans).getPost().equals(employeeRequest.getPost()) ||
                        (beans).getCity().equals(employeeRequest.getCity()) ||
                        (beans).getWorkRemotely().equals(employeeRequest.getWorkRemotely())
                ).collect(Collectors.toList());
        return bean;
    }

    public List<Vacancy> partTimeJobSearch(JobRequest jobRequest){
        List<Vacancy> bean = selectVacancy().stream()
                .filter(beans -> (beans).getAreaWork().equals(jobRequest.getAreaWork()) ||
                                (beans).getTitleWork().equals(jobRequest.getTitleWork()) ||
                                (beans).getDescription().equals(jobRequest.getDescription()) ||
                                (beans).getCity().equals(jobRequest.getCity()) ||
                                (beans).getPrice() == jobRequest.getPrice()
                        ).collect(Collectors.toList());
        return bean;
    }

    private void sendHistory(String className, String methodName, Object object, Status status){
        long id = new Date().getTime();
        Date createdDate = new Date();
        String actor = Constants.DEFAULT_ACTOR;
        HistoryContent historyContent = new HistoryContent(id, className, createdDate,
                actor, methodName, object, status);
        HistoryMongo.recordToMongo(historyContent);
    }


    private  <T> List<T> csvToBean(T bean){
        List list = null;
        try {
            list = new CsvToBeanBuilder(new FileReader(findPath(bean)))
                    .withType(bean.getClass())
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            log.error(e);
        }
        return list;
    }

    private <T> Status beanToCsv(List<T> list, String className, String method){
        Writer writer = null;
        try {
            writer = new FileWriter(findPath(list));
            StatefulBeanToCsv<T> sbc = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(',')
                    .build();
            sbc.write(list);
            writer.close();
            sendHistory(className, method, null, Status.SUCCESS);
            return Status.SUCCESS;
        } catch (Exception e) {
            log.error(e);
            sendHistory(className, method, null, Status.FAULT);
            return Status.FAULT;
        }
    }

    private static <T> String findPath(T bean){
        switch (bean.getClass().getSimpleName()){
            case "Profile":
                return Constants.CSV_PATH_PROFILE;
            case "Feedback":
                return Constants.CSV_PATH_FEEDBACK;
            case "ProfileEmployee":
                return Constants.CSV_PATH_PROFILE_EMPLOYEE;
            case "ProfileEmployer":
                return Constants.CSV_PATH_PROFILE_EMPLOYER;
            case "Vacancy":
                return Constants.CSV_PATH_VACANCY;
        }
        log.error("File not found!!!");
        return Constants.CSV_PATH_UNDEFINED;
    }

    private static <T> String findPath(List<T> bean){
        switch (bean.get(0).getClass().getSimpleName()){
            case "Profile":
                return Constants.CSV_PATH_PROFILE;
            case "Feedback":
                return Constants.CSV_PATH_FEEDBACK;
            case "ProfileEmployee":
                return Constants.CSV_PATH_PROFILE_EMPLOYEE;
            case "ProfileEmployer":
                return Constants.CSV_PATH_PROFILE_EMPLOYER;
            case "Vacancy":
                return Constants.CSV_PATH_VACANCY;
        }
        log.error("File not found!!!");
        return Constants.CSV_PATH_UNDEFINED;
    }



    @Override
    public List<Profile> selectProfile() {
        Profile profiles = new Profile();
        String method = currentThread().getStackTrace()[1].getMethodName();
        List<Profile> list = csvToBean(profiles);

        if (list != null) {
            sendHistory(profiles.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("Profile", method, null, Status.FAULT);
        }
        return list;
    }

    @Override
    public Profile profileGetById(long userId) {
        Profile profile = selectProfile().stream()
                .filter(beans -> (beans).getUserId() == userId)
                .findFirst().orElse(null);
        String method = currentThread().getStackTrace()[1].getMethodName();
        if (profile != null) {
            sendHistory(profile.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("Profile", method, null, Status.FAULT);
        }
        return profile;
    }

    @Override
    public void insertProfile(Profile profile) {
        List<Profile> profiles = csvToBean(profile);
        profiles.add(profile);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(profiles, profiles.getClass().getSimpleName(), method);
    }

    @Override
    public void deleteProfileById(long userId) {
        List<Profile> list = selectProfile();
        list.removeIf(beans -> (beans).getUserId()==userId);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);
    }

    @Override
    public void editProfile(long userId, Profile profile) {
        List<Profile> list = selectProfile();
        int index = list.indexOf(profileGetById(userId));
        list.set(index, profile);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);

    }

    @Override
    public List<Feedback> selectFeedback() {
        Feedback feedback = new Feedback();
        String method = currentThread().getStackTrace()[1].getMethodName();
        List<Feedback> list = csvToBean(feedback);

        if (list != null) {
            sendHistory(feedback.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("Feedback", method, null, Status.FAULT);
        }
        return list;
    }

    @Override
    public Feedback feedbackGetById(long feedbackId) {
        Feedback bean = selectFeedback().stream()
                .filter(beans -> (beans).getFeedbackId() == feedbackId)
                .findFirst().orElse(null);
        String method = currentThread().getStackTrace()[1].getMethodName();
        if (bean != null) {
            sendHistory(bean.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("Feedback", method, null, Status.FAULT);
        }
        return bean;
    }

    @Override
    public Status makeFeedback(Feedback feedback) {
        List<Feedback> feedbacks = csvToBean(feedback);
        feedbacks.add(feedback);
        String method = currentThread().getStackTrace()[1].getMethodName();
        Status status = beanToCsv(feedbacks, feedback.getClass().getSimpleName(), method);
        if (status == Status.SUCCESS){
            changeRating(feedback.getUserIdTo(), feedback.getEstimation());
            return Status.SUCCESS;
        }
        return Status.FAULT;
    }

    @Override
    public void changeRating(long userId, int newRating){
        Profile profile = profileGetById(userId);
        profile.setRating(newRating);
        editProfile(userId, profile);
    }

    @Override
    public void deleteFeedbackById(long feedbackId) {
        List<Feedback> list = selectFeedback();
        list.removeIf(beans -> (beans).getFeedbackId()==feedbackId);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);
    }

    @Override
    public void updateFeedbackById(long feedbackId, Feedback feedback) {
        List<Feedback> list = selectFeedback();
        int index = list.indexOf(feedbackGetById(feedbackId));
        list.set(index, feedback);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);
    }

    @Override
    public List<ProfileEmployee> selectProfileEmployee() {
        ProfileEmployee profileEmployee = new ProfileEmployee();
        String method = currentThread().getStackTrace()[1].getMethodName();
        List<ProfileEmployee> list = csvToBean(profileEmployee);

        if (list != null) {
            sendHistory(profileEmployee.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("ProfileEmployee", method, null, Status.FAULT);
        }
        return list;
    }

    @Override
    public List<ProfileEmployer> selectProfileEmployer() {
        ProfileEmployer profileEmployer = new ProfileEmployer();
        String method = currentThread().getStackTrace()[1].getMethodName();
        List<ProfileEmployer> list = csvToBean(profileEmployer);

        if (list != null) {
            sendHistory(profileEmployer.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("ProfileEmployer", method, null, Status.FAULT);
        }
        return list;
    }

    @Override
    public List<Vacancy> selectVacancy() {
        Vacancy vacancy = new Vacancy();
        String method = currentThread().getStackTrace()[1].getMethodName();
        List<Vacancy> list = csvToBean(vacancy);

        if (list != null) {
            sendHistory(vacancy.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("Vacancy", method, null, Status.FAULT);
        }
        return list;
    }

    @Override
    public ProfileEmployee profileEmployeeGetById(long profileEmployeeId) {
        ProfileEmployee bean = selectProfileEmployee().stream()
                .filter(beans -> (beans).getUserId() == profileEmployeeId)
                .findFirst().orElse(null);
        String method = currentThread().getStackTrace()[1].getMethodName();
        if (bean != null) {
            sendHistory(bean.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("ProfileEmployee", method, null, Status.FAULT);
        }
        return bean;
    }

    @Override
    public ProfileEmployer profileEmployerGetById(long profileEmployerId) {
        ProfileEmployer bean = selectProfileEmployer().stream()
                .filter(beans -> (beans).getUserId() == profileEmployerId)
                .findFirst().orElse(null);
        String method = currentThread().getStackTrace()[1].getMethodName();
        if (bean != null) {
            sendHistory(bean.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("ProfileEmployer", method, null, Status.FAULT);
        }
        return bean;
    }

    @Override
    public Vacancy vacancyGetById(long vacancyId) {
        Vacancy bean = selectVacancy().stream()
                .filter(beans -> (beans).getVacancyId() == vacancyId)
                .findFirst().orElse(null);
        String method = currentThread().getStackTrace()[1].getMethodName();
        if (bean != null) {
            sendHistory(bean.getClass().getSimpleName(), method, null, Status.SUCCESS);
        } else {
            sendHistory("Vacancy", method, null, Status.FAULT);
        }
        return bean;
    }

    @Override
    public void createProfileEmployee(ProfileEmployee profileEmployee) {
        List<ProfileEmployee> profileEmployees = csvToBean(profileEmployee);
        profileEmployees.add(profileEmployee);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(profileEmployees, profileEmployee.getClass().getSimpleName(), method);
    }

    @Override
    public Status createProfileEmployer(ProfileEmployer profileEmployer) {
        List<ProfileEmployer> profileEmployers = csvToBean(profileEmployer);
        profileEmployers.add(profileEmployer);
        String method = currentThread().getStackTrace()[1].getMethodName();
        Status status = beanToCsv(profileEmployers, profileEmployer.getClass().getSimpleName(), method);
        if (status == Status.SUCCESS){
            Vacancy vacancy = new Vacancy();
            postVacancy(vacancy);
            return Status.SUCCESS;
        }
        return Status.FAULT;
    }

    @Override
    public void postVacancy(Vacancy vacancy) {
        List<Vacancy> vacancys = csvToBean(vacancy);
        vacancys.add(vacancy);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(vacancys, vacancy.getClass().getSimpleName(), method);
    }

    @Override
    public void deleteProfileEmployeeBuId(long userId) {
        List<ProfileEmployee> list = selectProfileEmployee();
        list.removeIf(beans -> (beans).getUserId()==userId);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);
    }

    @Override
    public void deleteProfileEmployerById(long userId) {
        List<ProfileEmployer> list = selectProfileEmployer();
        list.removeIf(beans -> (beans).getUserId()==userId);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);
    }

    @Override
    public void deleteVacancyById(long vacancyId) {
        List<Vacancy> list = selectVacancy();
        list.removeIf(beans -> (beans).getVacancyId()==vacancyId);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);
    }

    @Override
    public void updateProfileEmployeeById(long userId, ProfileEmployee profileEmployee) {
        List<ProfileEmployee> list = selectProfileEmployee();
        int index = list.indexOf(profileEmployeeGetById(userId));
        list.set(index, profileEmployee);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);
    }

    @Override
    public void updateProfileEmployerById(long userId, ProfileEmployer profileEmployer) {
        List<ProfileEmployer> list = selectProfileEmployer();
        int index = list.indexOf(profileEmployerGetById(userId));
        list.set(index, profileEmployer);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);
    }

    @Override
    public void updateVacancyById(long vacancyId, Vacancy vacancy) {
        List<Vacancy> list = selectVacancy();
        int index = list.indexOf(vacancyGetById(vacancyId));
        list.set(index, vacancy);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToCsv(list, list.getClass().getSimpleName(), method);
    }

}
