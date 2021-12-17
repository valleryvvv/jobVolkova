package ru.sfedu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.dataProvider.DataProviderCSV;
import ru.sfedu.dataProvider.DataProviderMySQL;
import ru.sfedu.dataProvider.DataProviderXML;
import ru.sfedu.model.*;
import utils.MySQLUtil;

import java.io.*;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.currentThread;

public class Job {
    private static final Logger log = LogManager.getLogger(Job.class.getName());

    protected <T> List<T> jsonArrayToObjectList(List<Map<String, Object>> map, Class<T> tClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType listType = mapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, tClass);
            List<T> objects = mapper.convertValue(map, listType);
            return objects;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ClassCastException(ex.getMessage());
        }
    }


    public static void main(String[] args) throws IOException {
        DataProviderMySQL dataProviderMySQL = new DataProviderMySQL();
        String s = "update job.profile set name = 'ff', lastName = 'MM' where userId = 1; ";
        Vacancy vacancy = new Vacancy(1, 1,"Lala","Lala","Lala","Lala", true, "jj", 34);
        ProfileEmployee profile12 = new ProfileEmployee(9,"Lala","La","12.12.2000","c",99,false, "VVV", "VVV", "VVV", "VVV", "VVV", true);

        Feedback feedback = new Feedback(1, 16, 1, "Superu", 47, "24.11.2021");
        ProfileEmployer profile1 = new ProfileEmployer(9,"Lala","La","12.12.2000","c",99,false, "VVV");
        Profile profile = new Profile(9,"Lala","La","12.12.2000","c",99,false);
//        Gson gson = new Gson();
//        String json = gson.toJson(profile);
//        System.out.println(json);
        //        String query = "insert into " + profile.getClass().getSimpleName() + " value (" +
//                profile.getUserId() + ", '" +
//                profile.getName() + "', '" +
//                profile.getLastName() + "', '" +
//                profile.getBirthday() + "', '" +
//                profile.getCity() + "', " +
//                profile.getRating() + ", " +
//                profile.isEmployer() + ");";
//        System.out.println(query);
//        Profile profiles = dataProviderMySQL.profileGetById(1);
//        Status sq = dataProviderMySQL.editProfile(9766569, profile);
//        String query = "update " + profile.getClass().getSimpleName() + " set " +
//                "UserId=" + profile.getUserId() + ", Name='" +
//                profile.getName() + "', lastName='" +
//                profile.getLastName() + "', Birthday='" +
//                profile.getBirthday() + "', City='" +
//                profile.getCity() + "', Rating=" +
//                profile.getRating() + ", Employer=" +
//                profile.isEmployer() +
//                " where userId=" + 1 + ";";
//        System.out.println(query);
//        Arrays.asList(Optional<Profile> p = Optional.ofNullable(profiles));
//        System.out.println(p);
//        System.out.println(profiles.getBirthday());
//        List<Profile> profiles = dataProviderMySQL.selectProfile();
//        for (Profile e : profiles){
//            log.info(e.getName());
//        }
//        DataProviderXML dpc = new DataProviderXML();
//        Profile profile = new Profile(3,"Lala","La","12.12.2000","c",99,false);
//        dpc.insertProfile(profile);
//        Profile profile1 = dpc.profileGetById(3);
//        System.out.println(profile1);
//        long id = new Date().getTime();
        //Profile profile = new Profile(id,"Rara","Ra","12.12.2001","Rostov",56,true);
//        Feedback feedback = new Feedback(id, 1638816111562L, 1, "Superu", 47, "24.11.2021");
//        dpc.insertFeedback(feedback);
//        Profile profile = dpc.profileGetById(feedback.getUserIdFrom());
//        System.out.println(profile.getFeedback());
//        ProfileEmployee profileEmployee = new ProfileEmployee();
//        profileEmployee.setCity("T");
//        List<Feedback> beaen = dpc.selectFeedback().stream()
//                .filter(beans -> (beans).getUserIdTo() == 1 && (beans).getText().equals("Super")).collect(Collectors.toList());
//        for (Feedback e : beaen) {
//            log.info(e.getText());
//        }
//        EmployeeRequest employeeRequest = new EmployeeRequest();
//        employeeRequest.setAreaWork("Son");
//        List<ProfileEmployee> b = dpc.employeeSearch(employeeRequest);
//        System.out.println(b);

    }
}


