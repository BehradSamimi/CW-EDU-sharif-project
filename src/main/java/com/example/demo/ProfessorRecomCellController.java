package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import logic.Recom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfessorRecomCellController {
    public Recom recom;
    @FXML
    public Label studentName;
    @FXML
    public Label studentNumber;
    @FXML
    public TextArea answerTextArea;
    @FXML
    public Button recordButton;
    @FXML
    public AnchorPane back;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void setRecordButton() {
        logger.info("recom for student : " + recom.student.username + " sent");
        recom.answer = answerTextArea.getText();
    }

}
