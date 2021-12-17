package ru.sfedu.dataProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.Constants;
import ru.sfedu.model.*;
import utils.HistoryMongo;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.currentThread;
import static ru.sfedu.Constants.*;
import static utils.ConfigurationUtil.getConfigurationEntry;

public class DataProviderXML implements Interface{

    private static final Logger log = LogManager.getLogger(DataProviderXML.class.getName());


    private <T> List<T> xmlToBean(T bean){
        try {
            FileReader fileReader = new FileReader(findPath(bean));
            Serializer serializer = new Persister();
            XmlList<T> xmlList = serializer.read(XmlList.class, fileReader);
            List<T> list = xmlList.getList();
            fileReader.close();
            return list;
        } catch (Exception e) {
            log.error(e);
        }
        return new ArrayList<>();
    }

    private <T> Status beanToXml(List<T> list, String className, String method, Object object){
        try {
            FileWriter fileWriter = new FileWriter(findPath(list));
            Serializer serializer = new Persister();
            XmlList<T> xmlList = new XmlList<>(list);
            serializer.write(xmlList, fileWriter);
            fileWriter.close();
            sendHistory(className, method, object.toString(), Status.SUCCESS);
            return Status.SUCCESS;
        } catch (Exception e) {
            log.error(e);
            sendHistory(className, method, null, Status.FAULT);
            return Status.FAULT;
        }

    }

    @Override
    public void sendHistory(String className, String methodName, Object object, Status status){
        long id = new Date().getTime();
        Date createdDate = new Date();
        HistoryContent historyContent = new HistoryContent(id, className, createdDate,
                Constants.DEFAULT_ACTOR, methodName, object, status);
        try {
            MongoClient client = new MongoClient(new MongoClientURI(getConfigurationEntry(MONGO_URI)));
            MongoDatabase database = client.getDatabase(getConfigurationEntry(MONGO_DATABASE));
            MongoCollection<Document> collection = database.getCollection(getConfigurationEntry(MONGO_COLLECTION));
            Document document = new Document();
            document.put("id", historyContent.getId());
            document.put("className", historyContent.getClassName());
            document.put("createdDate", historyContent.getCreatedDate());
            document.put("actor", historyContent.getActor());
            document.put("methodName", historyContent.getMethodName());
            document.put("object", historyContent.getObject());
            document.put("status", historyContent.getStatus().toString());
            collection.insertOne(document);
        }catch (Exception e){
            log.info(e);
        }
    }

    private static <T> String findPath(T bean){
        switch (bean.getClass().getSimpleName()){
            case "Profile":
                return Constants.XML_PATH_PROFILE;
            case "Feedback":
                return Constants.XML_PATH_FEEDBACK;
            case "ProfileEmployee":
                return Constants.XML_PATH_PROFILE_EMPLOYEE;
            case "ProfileEmployer":
                return Constants.XML_PATH_PROFILE_EMPLOYER;
            case "Vacancy":
                return Constants.XML_PATH_VACANCY;
        }
        log.error("File not found!!!");
        return Constants.PATH_UNDEFINED;
    }

    private static <T> String findPath(List<T> bean){
        switch (bean.get(0).getClass().getSimpleName()){
            case "Profile":
                return Constants.XML_PATH_PROFILE;
            case "Feedback":
                return Constants.XML_PATH_FEEDBACK;
            case "ProfileEmployee":
                return Constants.XML_PATH_PROFILE_EMPLOYEE;
            case "ProfileEmployer":
                return Constants.XML_PATH_PROFILE_EMPLOYER;
            case "Vacancy":
                return Constants.XML_PATH_VACANCY;
        }
        log.error("File not found!!!");
        return Constants.PATH_UNDEFINED;
    }

    @Override
    public List<Profile> selectProfile() {
        Profile profile = new Profile();
        return xmlToBean(profile);
    }

    @Override
    public List<Feedback> selectFeedback() {
        Feedback feedback = new Feedback();
        return xmlToBean(feedback);
    }

    @Override
    public List<ProfileEmployee> selectProfileEmployee() {
        ProfileEmployee profile = new ProfileEmployee();
        return xmlToBean(profile);
    }

    @Override
    public List<ProfileEmployer> selectProfileEmployer() {
        ProfileEmployer profile = new ProfileEmployer();
        return xmlToBean(profile);
    }

    @Override
    public List<Vacancy> selectVacancy() {
        Vacancy vacancy = new Vacancy();
        return xmlToBean(vacancy);
    }

    @Override
    public Profile profileGetById(long userId) {
        return selectProfile().stream()
                .filter(beans -> (beans).getUserId() == userId)
                .findFirst().orElse(null);
    }

    @Override
    public Feedback feedbackGetById(long feedbackId) {
        return selectFeedback().stream()
                .filter(beans -> (beans).getFeedbackId() == feedbackId)
                .findFirst().orElse(null);
    }

    @Override
    public ProfileEmployee profileEmployeeGetById(long userId) {
        return selectProfileEmployee().stream()
                .filter(beans -> (beans).getUserId() == userId)
                .findFirst().orElse(null);
    }

    @Override
    public ProfileEmployer profileEmployerGetById(long userId) {
        return selectProfileEmployer().stream()
                .filter(beans -> (beans).getUserId() == userId)
                .findFirst().orElse(null);
    }

    @Override
    public Vacancy vacancyGetById(long vacancyId) {
        return selectVacancy().stream()
                .filter(beans -> (beans).getVacancyId() == vacancyId)
                .findFirst().orElse(null);
    }

    @Override
    public void insertProfile(Profile profile) {
        List<Profile> profiles = xmlToBean(profile);
        profiles.add(profile);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(profiles, profiles.getClass().getSimpleName(), method, profile);
    }

    @Override
    public Status makeFeedback(Feedback feedback) {
        List<Feedback> feedbacks = xmlToBean(feedback);
        feedbacks.add(feedback);
        String method = currentThread().getStackTrace()[1].getMethodName();
        Status status = beanToXml(feedbacks, feedback.getClass().getSimpleName(), method, feedback);
        if (status == Status.SUCCESS){
            changeRating(feedback.getUserIdTo(), feedback.getEstimation());
        return Status.SUCCESS;
        }
        return Status.FAULT;
    }

    @Override
    public void createProfileEmployee(ProfileEmployee profileEmployee) {
        List<ProfileEmployee> profiles = xmlToBean(profileEmployee);
        profiles.add(profileEmployee);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(profiles, profileEmployee.getClass().getSimpleName(), method, profileEmployee);
    }

    @Override
    public Status createProfileEmployer(ProfileEmployer profileEmployer) {
        List<ProfileEmployer> profiles = xmlToBean(profileEmployer);
        profiles.add(profileEmployer);
        String method = currentThread().getStackTrace()[1].getMethodName();
        Status status = beanToXml(profiles, profileEmployer.getClass().getSimpleName(), method, profileEmployer);
        if (status == Status.SUCCESS){
            Vacancy vacancy = new Vacancy();
            postVacancy(vacancy);
            return Status.SUCCESS;
        }
        return Status.FAULT;
    }

    @Override
    public void postVacancy(Vacancy vacancy) {
        List<Vacancy> vacancies = xmlToBean(vacancy);
        vacancies.add(vacancy);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(vacancies, vacancy.getClass().getSimpleName(), method, vacancy);
    }

    @Override
    public Status editProfile(long userId, Profile profile) {
        try {
            List<Profile> list = selectProfile();
            int index = list.indexOf(profileGetById(userId));
            list.set(index, profile);
            String method = currentThread().getStackTrace()[1].getMethodName();
            beanToXml(list, profile.getClass().getSimpleName(), method, profile);
            return Status.SUCCESS;
        }catch (Exception e){
            log.error(e);
            return Status.FAULT;
        }
    }


    @Override
    public void updateFeedbackById(long feedbackId, Feedback feedback) {
        List<Feedback> list = selectFeedback();
        int index = list.indexOf(feedbackGetById(feedbackId));
        list.set(index, feedback);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(list, feedback.getClass().getSimpleName(), method, feedback);
    }

    @Override
    public void updateProfileEmployeeById(long userId, ProfileEmployee profileEmployee) {
        List<ProfileEmployee> list = selectProfileEmployee();
        int index = list.indexOf(profileEmployeeGetById(userId));
        list.set(index, profileEmployee);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(list, list.getClass().getSimpleName(), method, profileEmployee);
    }

    @Override
    public void updateProfileEmployerById(long userId, ProfileEmployer profileEmployer) {
        List<ProfileEmployer> list = selectProfileEmployer();
        int index = list.indexOf(profileEmployerGetById(userId));
        list.set(index, profileEmployer);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(list, list.getClass().getSimpleName(), method, profileEmployer);
    }

    @Override
    public void updateVacancyById(long vacancyId, Vacancy vacancy) {
        List<Vacancy> list = selectVacancy();
        int index = list.indexOf(vacancyGetById(vacancyId));
        list.set(index, vacancy);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(list, list.getClass().getSimpleName(), method, vacancy);
    }

    @Override
    public Status deleteProfileById(long userId) {
        try{
            List<Profile> list = selectProfile();
            list.removeIf(beans -> (beans).getUserId()==userId);
            Profile profile = profileGetById(userId);
            String method = currentThread().getStackTrace()[1].getMethodName();
            beanToXml(list, profile.getClass().getSimpleName(), method, profile);
            return Status.SUCCESS;
        }catch(Exception e){
            log.error(e);
            return Status.FAULT;
        }
    }


    @Override
    public void deleteFeedbackById(long feedbackId) {
        List<Feedback> list = selectFeedback();
        list.removeIf(beans -> (beans).getFeedbackId()==feedbackId);
        Feedback feedback = feedbackGetById(feedbackId);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(list, feedback.getClass().getSimpleName(), method, feedback);
    }

    @Override
    public void deleteProfileEmployeeBuId(long userId) {
        List<ProfileEmployee> list = selectProfileEmployee();
        list.removeIf(beans -> (beans).getUserId()==userId);
        ProfileEmployee profileEmployee = profileEmployeeGetById(userId);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(list, list.getClass().getSimpleName(), method, profileEmployee);
    }

    @Override
    public void deleteProfileEmployerById(long userId) {
        List<ProfileEmployer> list = selectProfileEmployer();
        list.removeIf(beans -> (beans).getUserId()==userId);
        ProfileEmployer profileEmployer = profileEmployerGetById(userId);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(list, list.getClass().getSimpleName(), method, profileEmployer);
    }

    @Override
    public void deleteVacancyById(long vacancyId) {
        List<Vacancy> list = selectVacancy();
        list.removeIf(beans -> (beans).getVacancyId()==vacancyId);
        Vacancy vacancy = vacancyGetById(vacancyId);
        String method = currentThread().getStackTrace()[1].getMethodName();
        beanToXml(list, list.getClass().getSimpleName(), method, vacancy);
    }


    @Override
    public List<ProfileEmployee> employeeSearch(EmployeeRequest employeeRequest) {
        return selectProfileEmployee().stream()
                .filter(beans -> (beans).getWorkExperience().equals(employeeRequest.getWorkExperience()) ||
                        (beans).getEducation().equals(employeeRequest.getEducation()) ||
                        (beans).getResume().equals(employeeRequest.getResume()) ||
                        (beans).getAreaWork().equals(employeeRequest.getAreaWork()) ||
                        (beans).getPost().equals(employeeRequest.getPost()) ||
                        (beans).getCity().equals(employeeRequest.getCity()) ||
                        (beans).getWorkRemotely().equals(employeeRequest.getWorkRemotely())
                ).collect(Collectors.toList());
    }

    @Override
    public List<Vacancy> partTimeJobSearch(JobRequest jobRequest) {
        return selectVacancy().stream()
                .filter(beans -> (beans).getAreaWork().equals(jobRequest.getAreaWork()) ||
                        (beans).getTitleWork().equals(jobRequest.getTitleWork()) ||
                        (beans).getDescription().equals(jobRequest.getDescription()) ||
                        (beans).getCity().equals(jobRequest.getCity()) ||
                        (beans).getPrice() == jobRequest.getPrice()
                ).collect(Collectors.toList());
    }

    @Override
    public void changeRating(long userId, int newRating) {
        Profile profile = profileGetById(userId);
        profile.setRating(newRating);
        editProfile(userId, profile);
    }

}
