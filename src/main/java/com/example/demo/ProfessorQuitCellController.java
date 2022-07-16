package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import logic.Student;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfessorQuitCellController {
    public Student student;
    public ProfessorQuitPageController professorQuitPageController;
    @FXML
    public Button acceptButton;
    @FXML
    public Button rejectButton;
    @FXML
    public AnchorPane back;
    @FXML
    public Label studentNumber;
    @FXML
    public Label studentName;
    public static Logger logger = LogManager.getLogger(logIn.class);


    public void setAcceptButton() {
        University.students.remove(student);
        student.isQuitrequestAccepted = 1;
        student.educationalState = "quited";
        logger.info("student : " + student.studentNumber + " quit request Accepted");
        professorQuitPageController.setQuitListView();
    }
    public void setRejectButton() {
        student.isQuitrequestAccepted = -1;
        logger.info("student : " + student.studentNumber + " quit request Rejected");
        professorQuitPageController.setQuitListView();
    }
}
