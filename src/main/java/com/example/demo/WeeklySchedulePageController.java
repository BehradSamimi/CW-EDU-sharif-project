package com.example.demo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.Course;
import logic.Professor;
import logic.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class WeeklySchedulePageController {
    public Professor professor;
    public Student student;
    public ArrayList<Course> courses;

    @FXML
    public Button SaturdayButton;
    @FXML
    public Button SundayButton;
    @FXML
    public Button MondayButton;
    @FXML
    public Button TuesdayButton;
    @FXML
    public Button WednesdayButton;
    @FXML
    public Button ThursdayButton;
    @FXML
    public Button FridayButton;

    @FXML
    public ListView<String> scheduleListView;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void setSaturdayButton() {
        logger.info("show Saturday Schedule");
        scheduleListView.getItems().clear();
        for (Course course : courses) {
            if (course.firstSession.date.equalsIgnoreCase("saturday")) {
                scheduleListView.getItems().add(course.firstSession.time + " -> " + course.name);
            }
            if (course.secondSession.date.equalsIgnoreCase("saturday")) {
                scheduleListView.getItems().add(course.secondSession.time + " -> " + course.name);
            }
        }
        FXCollections.sort(scheduleListView.getItems());
    }
    public void setSundayButton() {
        logger.info("show Sunday Schedule");

        scheduleListView.getItems().clear();
        for (Course course : courses) {
            if (course.firstSession.date.equalsIgnoreCase("sunday")) {
                scheduleListView.getItems().add(course.firstSession.time + " -> " + course.name);
            }
            if (course.secondSession.date.equalsIgnoreCase("sunday")) {
                scheduleListView.getItems().add(course.secondSession.time + " -> " + course.name);
            }
        }
        FXCollections.sort(scheduleListView.getItems());
    }

    public void setMondayButton() {
        logger.info("show monday Schedule");

        scheduleListView.getItems().clear();
        for (Course course : courses) {
            System.out.println("COURSE : " + course.name);
            if (course.firstSession.date.equalsIgnoreCase("monday")) {
                scheduleListView.getItems().add(course.firstSession.time + " -> " + course.name);
            }
            if (course.secondSession.date.equalsIgnoreCase("monday")) {
                scheduleListView.getItems().add(course.secondSession.time + " -> " + course.name);
            }
        }
        FXCollections.sort(scheduleListView.getItems());
    }

    public void setTuesdayButton() {
        logger.info("show Tuesday Schedule");

        scheduleListView.getItems().clear();
        for (Course course : courses) {
            if (course.firstSession.date.equalsIgnoreCase("tuesday")) {
                scheduleListView.getItems().add(course.firstSession.time + " -> " + course.name);
            }
            if (course.secondSession.date.equalsIgnoreCase("tuesday")) {
                scheduleListView.getItems().add(course.secondSession.time + " -> " + course.name);
            }
        }
        FXCollections.sort(scheduleListView.getItems());
    }

    public void setWednesdayButton() {
        logger.info("show wednesday Schedule");

        scheduleListView.getItems().clear();
        for (Course course : courses) {
            if (course.firstSession.date.equalsIgnoreCase("wednesday")) {
                scheduleListView.getItems().add(course.firstSession.time + " -> " + course.name);
            }
            if (course.secondSession.date.equalsIgnoreCase("wednesday")) {
                scheduleListView.getItems().add(course.secondSession.time + " -> " + course.name);
            }
        }
        FXCollections.sort(scheduleListView.getItems());
    }

    public void setThursdayButton() {
        logger.info("show thursday Schedule");
        scheduleListView.getItems().clear();
        for (Course course : courses) {
            if (course.firstSession.date.equalsIgnoreCase("thursday")) {
                scheduleListView.getItems().add(course.firstSession.time + " -> " + course.name);
            }
            if (course.secondSession.date.equalsIgnoreCase("thursday")) {
                scheduleListView.getItems().add(course.secondSession.time + " -> " + course.name);
            }
        }
        FXCollections.sort(scheduleListView.getItems());
    }

    public void setFridayButton() {
        logger.info("show friday Schedule");

        scheduleListView.getItems().clear();
        for (Course course : courses) {
            if (course.firstSession.date.equalsIgnoreCase("friday")) {
                scheduleListView.getItems().add(course.firstSession.time + " -> " + course.name);
            }
            if (course.secondSession.date.equalsIgnoreCase("friday")) {
                scheduleListView.getItems().add(course.secondSession.time + " -> " + course.name);
            }
        }
        FXCollections.sort(scheduleListView.getItems());
    }

    public void returnToMainPage() throws IOException {
        if (professor == null) {
            student.goToMainPage((Stage) SaturdayButton.getScene().getWindow());
        }
        else {
            professor.goToMainPage((Stage) SaturdayButton.getScene().getWindow());
        }
    }

}
