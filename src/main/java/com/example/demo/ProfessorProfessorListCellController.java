package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Professor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ProfessorProfessorListCellController {
    public Professor mainProfessor;
    public Professor professor;
    public Stage stage;
    @FXML
    public Label professorName;
    @FXML
    public Label professorDegree;
    @FXML
    public Label professorFaculty;
    @FXML
    public Label professorNumber;
    @FXML
    public Label professorMail;
    @FXML
    public Button editButton;
    @FXML
    public AnchorPane back;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void setEditButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("ProfessorEditProfessor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ProfessorEditProfessorController professorEditProfessorController = fxmlLoader.getController();
        professorEditProfessorController.recordNewProfessorButton.setVisible(false);
        professorEditProfessorController.mainProfessor = this.mainProfessor;
        professorEditProfessorController.professor = professor;
        professorEditProfessorController.professorUsername.setPromptText(professor.username);
        professorEditProfessorController.professorPassword.setPromptText(professor.password);
        professorEditProfessorController.professorName.setPromptText(professor.personalInformation.name);
        professorEditProfessorController.professorRoomID.setPromptText(String.valueOf(professor.roomID));
        professorEditProfessorController.professorDegree.setPromptText(professor.degree);
        logger.info("edit page for Professor : " + professor + " loaded");
        stage.setScene(scene);
    }
}
