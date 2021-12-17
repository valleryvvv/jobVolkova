package ru.sfedu.dataProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Constants;
import ru.sfedu.model.*;
import utils.MySQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sfedu.Constants.*;

public class DataProviderMySQL implements Interface{

    private static final Logger log = LogManager.getLogger(DataProviderMySQL.class.getName());
    Connection connection = MySQLUtil.connectToJDBC();

    @Override
    public List<Profile> selectProfile(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROFILE_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Profile> beans = new ArrayList<>();
            while(resultSet.next()) {
                beans.add(new Profile(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6),
                        resultSet.getBoolean(7)));
            }
            return beans;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Feedback> selectFeedback() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Constants.SELECT_FEEDBACK_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Feedback> beans = new ArrayList<>();
            while(resultSet.next()) {
                beans.add(new Feedback(resultSet.getLong(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6)));
            }
            return beans;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<ProfileEmployee> selectProfileEmployee() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROFILE_EMPLOYEE_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProfileEmployee> beans = new ArrayList<>();
            while(resultSet.next()) {
                beans.add(new ProfileEmployee(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6),
                        resultSet.getBoolean(7), resultSet.getString(8),
                        resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11), resultSet.getString(12),
                        resultSet.getBoolean(13)));
            }
            return beans;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<ProfileEmployer> selectProfileEmployer() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROFILE_EMPLOYER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProfileEmployer> beans = new ArrayList<>();
            while(resultSet.next()) {
                beans.add(new ProfileEmployer(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6),
                        resultSet.getBoolean(7), resultSet.getString(8)));
            }
            return beans;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Vacancy> selectVacancy() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VACANCY_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Vacancy> beans = new ArrayList<>();
            while(resultSet.next()) {
                beans.add(new Vacancy(resultSet.getLong(1), resultSet.getLong(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getBoolean(7), resultSet.getString(8),
                        resultSet.getFloat(9)));
            }
            return beans;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
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
    public ProfileEmployee profileEmployeeGetById(long profileEmployee) {
        return selectProfileEmployee().stream()
                .filter(beans -> (beans).getUserId() == profileEmployee)
                .findFirst().orElse(null);
    }

    @Override
    public ProfileEmployer profileEmployerGetById(long profileEmployerId) {
        return selectProfileEmployer().stream()
                .filter(beans -> (beans).getUserId() == profileEmployerId)
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
        try {
            String query = "insert into " + profile.getClass().getSimpleName() + " value (" +
                    profile.getUserId() + ", '" +
                    profile.getName() + "', '" +
                    profile.getLastName() + "', '" +
                    profile.getBirthday() + "', '" +
                    profile.getCity() + "', " +
                    profile.getRating() + ", " +
                    profile.isEmployer() + ");";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public Status makeFeedback(Feedback feedback) {
        try {
            String query = "insert into " + feedback.getClass().getSimpleName() + " (" +
                    "FeedbackId, UserIdTo, UserIdFrom, Estimation, Text, Date) value (" +
                    feedback.getFeedbackId() + ", " +
                    feedback.getUserIdTo() + ", " +
                    feedback.getUserIdFrom() + ", " +
                    feedback.getEstimation() + ", '" +
                    feedback.getText() + "', '" +
                    feedback.getDate() + "');";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
            return Status.SUCCESS;
        }catch (Exception e){
            log.error(e);
            return Status.FAULT;
        }
    }

    @Override
    public void createProfileEmployee(ProfileEmployee profileEmployee) {
        try {
            String query = "insert into " + profileEmployee.getClass().getSimpleName() + " value (" +
                    profileEmployee.getUserId() + ", '" +
                    profileEmployee.getName() + "', '" +
                    profileEmployee.getLastName() + "', '" +
                    profileEmployee.getBirthday() + "', '" +
                    profileEmployee.getCity() + "', " +
                    profileEmployee.getRating() + ", " +
                    profileEmployee.isEmployer() + ", '" +
                    profileEmployee.getWorkExperience() + "', '" +
                    profileEmployee.getEducation() + "', '" +
                    profileEmployee.getResume() + "', '" +
                    profileEmployee.getAreaWork() + "', '" +
                    profileEmployee.getPost() + "', " +
                    profileEmployee.getWorkRemotely() + ");";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public Status createProfileEmployer(ProfileEmployer profileEmployer) {
        try {
            String query = "insert into " + profileEmployer.getClass().getSimpleName() + " value (" +
                    profileEmployer.getUserId() + ", '" +
                    profileEmployer.getName() + "', '" +
                    profileEmployer.getLastName() + "', '" +
                    profileEmployer.getBirthday() + "', '" +
                    profileEmployer.getCity() + "', " +
                    profileEmployer.getRating() + ", " +
                    profileEmployer.isEmployer() + ", '" +
                    profileEmployer.getNameCompany() + "');";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
            return Status.SUCCESS;
        }catch (Exception e){
            log.error(e);
            return  Status.FAULT;
        }
    }

    @Override
    public void postVacancy(Vacancy vacancy) {
        try {
            String query = "insert into " + vacancy.getClass().getSimpleName() + " value (" +
                    vacancy.getVacancyId() + ", " +
                    vacancy.getIdProfileEmployer() + ", '" +
                    vacancy.getAreaWork() + "', '" +
                    vacancy.getTitleWork() + "', '" +
                    vacancy.getDescription() + "', '" +
                    vacancy.getCity() + "', " +
                    vacancy.getRemotely() + ", '" +
                    vacancy.getDeadline() + "', " +
                    vacancy.getPrice() + ");";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public Status editProfile(long userId, Profile profile) {
        if (profileGetById(userId) == null){
            return Status.FAULT;
        }
        try {
            String query = "update " + profile.getClass().getSimpleName() + " set " +
                    "UserId=" + profile.getUserId() + ", Name='" +
                    profile.getName() + "', lastName='" +
                    profile.getLastName() + "', Birthday='" +
                    profile.getBirthday() + "', City='" +
                    profile.getCity() + "', Rating=" +
                    profile.getRating() + ", Employer=" +
                    profile.isEmployer() + 
                    " where userId=" + userId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
            return Status.SUCCESS;
        }catch (Exception e){
            log.error(e);
            return Status.FAULT;
        }
    }

    @Override
    public void updateFeedbackById(long feedbackId, Feedback feedback) {
        try {
            String query = "update " + feedback.getClass().getSimpleName() + " set FeedbackId=" +
                    feedback.getFeedbackId() + ", UserIdTo=" +
                    feedback.getUserIdTo() + ", UserIdFrom=" +
                    feedback.getUserIdFrom() + ", Estimation=" +
                    feedback.getEstimation() + ", Text='" +
                    feedback.getText() + "', Date='" +
                    feedback.getDate() +
                    "' where feedbackId=" + feedbackId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public void updateProfileEmployeeById(long userId, ProfileEmployee profileEmployee) {
        try {
            String query = "update " + profileEmployee.getClass().getSimpleName() + " set UserId=" +
                    profileEmployee.getUserId() + ", Name='" +
                    profileEmployee.getName() + "', LastName='" +
                    profileEmployee.getLastName() + "', Birthday='" +
                    profileEmployee.getBirthday() + "', City='" +
                    profileEmployee.getCity() + "', Rating=" +
                    profileEmployee.getRating() + ", Employer=" +
                    profileEmployee.isEmployer() + ", WorkExperience='" +
                    profileEmployee.getWorkExperience() + "', Education='" +
                    profileEmployee.getEducation() + "', Resume='" +
                    profileEmployee.getResume() + "', AreaWork='" +
                    profileEmployee.getAreaWork() + "', Post='" +
                    profileEmployee.getPost() + "', WorkRemotely=" +
                    profileEmployee.getWorkRemotely() +
                    " where userId=" + userId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public void updateProfileEmployerById(long userId, ProfileEmployer profileEmployer) {
        try {
            String query = "update " + profileEmployer.getClass().getSimpleName() + " set UserId=" +
                    profileEmployer.getUserId() + ", Name='" +
                    profileEmployer.getName() + "', LastName='" +
                    profileEmployer.getLastName() + "', Birthday='" +
                    profileEmployer.getBirthday() + "', City='" +
                    profileEmployer.getCity() + "', Rating=" +
                    profileEmployer.getRating() + ", Employer=" +
                    profileEmployer.isEmployer() + ", NameCompany='" +
                    profileEmployer.getNameCompany() +
                    "' where userId=" + userId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public void updateVacancyById(long vacancyId, Vacancy vacancy) {
        try {
            String query = "update " + vacancy.getClass().getSimpleName() + " set VacancyId=" +
                    vacancy.getVacancyId() + ", IdProfileEmployer=" +
                    vacancy.getIdProfileEmployer() + ", AreaWork='" +
                    vacancy.getAreaWork() + "', TitleWork='" +
                    vacancy.getTitleWork() + "', Description='" +
                    vacancy.getDescription() + "', City='" +
                    vacancy.getCity() + "', Remotely=" +
                    vacancy.getRemotely() + ", Deadline='" +
                    vacancy.getDeadline() + "', Price=" +
                    vacancy.getPrice() +
                    " where vacancyId=" + vacancyId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public Status deleteProfileById(long userId) {
        Profile bean = new Profile();
        if (profileGetById(userId) == null){
            return Status.FAULT;
        }
        try {
            String query = "delete from " + bean.getClass().getSimpleName() +
                    " where userId=" + userId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
            return Status.SUCCESS;
        }catch(Exception e){
            log.error(e);
            return Status.FAULT;
        }
    }

    @Override
    public void deleteFeedbackById(long feedbackId) {
        Feedback bean = new Feedback();
        try {
            String query = "delete from " + bean.getClass().getSimpleName() +
                    " where feedbackId=" + feedbackId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }


    @Override
    public void deleteProfileEmployeeBuId(long userId) {
        ProfileEmployee bean = new ProfileEmployee();
        try {
            String query = "delete from " + bean.getClass().getSimpleName() +
                    " where userId=" + userId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public void deleteProfileEmployerById(long userId) {
        ProfileEmployer bean = new ProfileEmployer();
        try {
            String query = "delete from " + bean.getClass().getSimpleName() +
                    " where userId=" + userId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public void deleteVacancyById(long vacancyId) {
        Vacancy bean = new Vacancy();
        try {
            String query = "delete from " + bean.getClass().getSimpleName() +
                    " where vacancyId=" + vacancyId + ";";
            Statement Statement = connection.createStatement();
            Statement.executeUpdate(query);
        }catch (Exception e){
            log.error(e);
        }
    }

    @Override
    public void sendHistory(String className, String methodName, Object object, Status status) {

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
