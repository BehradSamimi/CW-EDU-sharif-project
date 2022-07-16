package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Course;
import logic.Professor;
import logic.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EducationalAssistantStudentStatesController {
    public Professor professor;

    @FXML
    public Button mainPageButton;

    @FXML
    public Button studentNumberFilterButton;
    @FXML
    public Button studentNameFilterButton;

    @FXML
    public TextField studentNumberTextField;
    @FXML
    public TextField studentNameTextField;

    @FXML
    public ListView<String> studentStatesListView;
    @FXML
    public ListView<String> studentFinallMarksListView;

    @FXML
    public Label studentStatesLabel;
    @FXML
    public Label studentFinallMarksLabel;

    public static Logger logger = LogManager.getLogger(logIn.class);


    public void returnToMainPage() throws IOException {
        professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
    public void setListViews() {
        studentStatesListView.getItems().clear();
        studentFinallMarksListView.getItems().clear();
        for (Student student : professor.faculty.students) {
            for (Course course : student.passedCourses) {
                studentFinallMarksListView.getItems().add(student.personalInformation.fullName + " | " + course.name + " : " + course.getMark(student));
            }
        }
        for (Student student : professor.faculty.students) {
            studentStatesListView.getItems().add(student.personalInformation.fullName + " | " + "PassedUnits : " + student.getPassedUnits() + " , GPA : " + student.getGradePointAverage());
        }
    }
    public void setStudentNumberFilterButton() {
        logger.info("filtered data with student number");
        studentStatesListView.getItems().clear();
        studentFinallMarksListView.getItems().clear();
        for (Student student : professor.faculty.students) {
            if (student.studentNumber == Integer.parseInt(studentNumberTextField.getText())) {
                for (Course course : student.passedCourses) {
                    studentFinallMarksListView.getItems().add(student.personalInformation.fullName + " | " + course.name + " : " + course.getMark(student));
                }
            }
        }
        for (Student student : professor.faculty.students) {
            if (student.studentNumber == Integer.parseInt(studentNumberTextField.getText())) {
                studentStatesListView.getItems().add(student.personalInformation.fullName + " | " + "PassedUnits : " + student.getPassedUnits() + " , GPA : " + student.getGradePointAverage());
            }
        }
    }
    public void setStudentNameFilterButton() {
        logger.info("filtered data with student name");
        studentStatesListView.getItems().clear();
        studentFinallMarksListView.getItems().clear();
        for (Student student : professor.faculty.students) {
            if (student.personalInformation.fullName.equals(studentNameTextField.getText())) {
                for (Course course : student.passedCourses) {
                    studentFinallMarksListView.getItems().add(student.personalInformation.fullName + " | " + course.name + " : " + course.getMark(student));
                }
            }
        }
        for (Student student : professor.faculty.students) {
            if (student.personalInformation.fullName.equals(studentNameTextField.getText())) {
                studentStatesListView.getItems().add(student.personalInformation.fullName + " | " + "PassedUnits : " + student.getPassedUnits() + " , GPA : " + student.getGradePointAverage());
            }
        }
    }
}
