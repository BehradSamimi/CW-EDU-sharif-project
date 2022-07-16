package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Professor;
import logic.Student;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ProfessorAddStudentPageController {
    public Professor professor;
    @FXML
    public TextField userName;
    @FXML
    public TextField fullName;
    @FXML
    public TextField passWord;
    @FXML
    public TextField major;
    @FXML
    public TextField superVisorNumber;
    @FXML
    public Button createButton;
    @FXML
    public Button mainPageButton;
    public static Logger logger = LogManager.getLogger(logIn.class);


    public void returnToMainPage() throws IOException {
        professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }

    public void setCreateButton() throws IOException {
        for (Professor professor : University.professors) {
            if (professor.username.equals(userName.getText())) {
                return;
            }
        }
        for (Student student : University.students) {
            if (student.username.equals(userName.getText())) {
                return;
            }
        }
        Student student = new Student();
        student.faculty = professor.faculty;
        student.personalInformation.fullName = fullName.getText();
        student.username = userName.getText();
        student.password = University.hash(passWord.getText());
        student.major = major.getText();
        int id = Integer.parseInt(superVisorNumber.getText());
        for (Professor professor : professor.faculty.professors) {
            if (professor.professorNumber == id) {
                student.supervisor = professor;
                break;
            }
        }
        student.faculty.students.add(student);
        University.students.add(student);
        logger.info("student with student ID :" + student.studentNumber + " added to faculty : " + professor.faculty.name);
        returnToMainPage();
    }
}
