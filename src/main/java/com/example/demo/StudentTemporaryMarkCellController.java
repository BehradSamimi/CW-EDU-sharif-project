package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import logic.Mark;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentTemporaryMarkCellController {
    public Mark mark;

    @FXML
    public Label courseName;
    @FXML
    public Label courseProfessor;
    @FXML
    public Label courseMark;
    @FXML
    public Label objectionAnswer;
    @FXML
    public TextField objectionText;
    @FXML
    public AnchorPane back;
    @FXML
    public Button objectionRecordButton;

    public void updatePage() {
        courseName.setText(mark.course.name);
        courseProfessor.setText(mark.course.professor.personalInformation.name);
        courseMark.setText(" Mark : " + mark.markValue);
        objectionText.setText(mark.objectionText);
        objectionAnswer.setText(mark.objectionAnswer);
    }

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void recordObjection(ActionEvent actionEvent) {
        mark.objectionText = objectionText.getText();
        logger.info("new objection text recorded for student : " + mark.student.username);
        updatePage();
    }

}
