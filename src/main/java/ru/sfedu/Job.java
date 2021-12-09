package ru.sfedu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.dataProvider.DataProviderCSV;
import ru.sfedu.model.EmployeeRequest;
import ru.sfedu.model.Feedback;
import ru.sfedu.model.Profile;
import ru.sfedu.model.ProfileEmployee;

import java.io.*;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.currentThread;

public class Job {
    private static final Logger log = LogManager.getLogger(Job.class.getName());

    public static void main(String[] args) throws FileNotFoundException {
        DataProviderCSV dpc = new DataProviderCSV();
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
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setAreaWork("Son");
        List<ProfileEmployee> b = dpc.employeeSearch(employeeRequest);
        System.out.println(b);

    }
}
