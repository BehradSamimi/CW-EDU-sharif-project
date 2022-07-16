package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Professor;
import logic.Student;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ProfessorEditProfessorController {
    public Professor mainProfessor, professor;
    @FXML
    public TextField professorUsername;
    @FXML
    public TextField professorPassword;
    @FXML
    public TextField professorName;
    @FXML
    public TextField professorRoomID;
    @FXML
    public TextField professorDegree;
    @FXML
    public Button recordNewProfessorButton;
    @FXML
    public Button changeProfessorButton;
    @FXML
    public Button addEducationalAssistant;
    @FXML
    public Button deleteEducationalAssistant;
    @FXML
    public Button mainPageButton;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void setChangeProfessorButton() {
        professor.username = professorUsername.getText();
        professor.password = University.hash(professorPassword.getText());
        professor.personalInformation.name = professorName.getText();
        professor.roomID = Integer.parseInt(professorRoomID.getText());
        professor.degree = professorDegree.getText();
        logger.info("professor :" + professor.username + " changed");
    }

    public void goToProfessorMainPage() throws IOException {
        mainProfessor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }

    public void setRecordNewProfessorButton() throws IOException {
        for (Professor professor : University.professors) {
            if (professor.username.equals(professorUsername.getText())) {
                return;
            }
        }
        for (Student student : University.students) {
            if (student.username.equals(professorUsername.getText())) {
                return;
            }
        }
        Professor nwProfessor = new Professor();
        nwProfessor.degree = professorDegree.getText();
        nwProfessor.personalInformation.name = professorName.getText();
        nwProfessor.username = professorUsername.getText();
        nwProfessor.password = University.hash(professorPassword.getText());
        nwProfessor.roomID = Integer.parseInt(professorRoomID.getText());
        nwProfessor.faculty = mainProfessor.faculty;
        University.professors.add(nwProfessor);
        mainProfessor.faculty.professors.add(nwProfessor);
        logger.info("professor : " + nwProfessor.username + " added to faculty : " + nwProfessor.faculty.name);
        goToProfessorMainPage();
    }

    public void setAddEducationalAssistant() {
        if (mainProfessor.faculty.educationalAssistant != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            alert.setTitle("عدم موفقیت");
            alert.setHeaderText(null);
            alert.setContentText("در دانشکده شما معاون اموزشی وجود داشته ابتدا باید ان را عزل کنید" + "\n" + "معاون قبلی : " + mainProfessor.faculty.educationalAssistant.personalInformation.name);
            alert.showAndWait();
        }
        else {
            logger.info("professor : " + professor.username + " set as a educational assistant");
            mainProfessor.faculty.educationalAssistant = professor;
            professor.isEducationalAssistant = true;
        }
    }
    public void setDeleteEducationalAssistant() {
        if (mainProfessor.faculty.educationalAssistant == professor) {
            mainProfessor.faculty.educationalAssistant = null;
            professor.isEducationalAssistant = false;
            logger.info("professor " + professor.username + " has been fired to be a educational assistant");
        }
    }
}
