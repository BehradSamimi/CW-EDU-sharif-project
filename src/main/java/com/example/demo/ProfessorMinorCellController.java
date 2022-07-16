package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import logic.MinorRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfessorMinorCellController {
    public MinorRequest minorRequest;
    public ProfessorMinorPageController professorMinorPageController;
    @FXML
    public Label studentNumber;
    @FXML
    public Label studentName;
    @FXML
    public Label sourceFaculty;
    @FXML
    public Label destinationFaculty;
    @FXML
    public Button acceptButton;
    @FXML
    public Button rejectButton;
    @FXML
    public AnchorPane back;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void setAcceptButton() {
        if (minorRequest.destinationFaculty.equals(professorMinorPageController.professor.faculty.name)) {
            minorRequest.destinationAccept = 1;
            logger.info("minor request for student : " + minorRequest.student.username + " accepted from destination faculty ");
        }
        else {
            minorRequest.sourceAccept = 1;
            logger.info("minor request for student : " + minorRequest.student.username + " accepted from source faculty ");

        }
        professorMinorPageController.setMinorListView();
    }
    public void setRejectButton() {
        if (minorRequest.destinationFaculty.equals(professorMinorPageController.professor.faculty.name)) {
            minorRequest.destinationAccept = -1;
            logger.info("minor request for student : " + minorRequest.student.username + " rejected from destination faculty ");

        }
        else {
            minorRequest.sourceAccept = -1;
            logger.info("minor request for student : " + minorRequest.student.username + " accepted from source faculty ");

        }
        professorMinorPageController.setMinorListView();
    }
}
