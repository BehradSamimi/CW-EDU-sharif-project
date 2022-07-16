package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import logic.Mark;
import logic.Professor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfessorTemporaryMarkCellController {
    public Mark mark;
    @FXML
    public Label studentName;
    @FXML
    public Label courseName;
    @FXML
    public Label professorName;

    @FXML
    public TextField markValue;
    @FXML
    public Button recordMarkButton;

    @FXML
    public TextArea objectionAnswer;
    @FXML
    public TextArea objectionText;
    @FXML
    public Button recordObjectionAnswerButton;

    @FXML
    public AnchorPane back;

    public static Logger logger = LogManager.getLogger(logIn.class);


    public void updatePage() {
        studentName.setText(mark.student.personalInformation.fullName);
        professorName.setText(mark.course.professor.personalInformation.name);
        courseName.setText(mark.course.name);

        markValue.setText(String.valueOf(mark.markValue));

        objectionText.setText(mark.objectionText);
        objectionAnswer.setText(mark.objectionAnswer);

        objectionText.setEditable(false);

    }

    public void updatePageForEducationalAssistant() {

        recordMarkButton.setVisible(false);
        recordObjectionAnswerButton.setVisible(false);

        objectionAnswer.setEditable(false);

        markValue.setEditable(false);
    }

    public void setMark() {
        logger.info("mark for stident : " + mark.student.username + " recorded");
        mark.markValue = Double.parseDouble(markValue.getText());
        mark.correctMarkValue();
        mark.isMarkEntered = 1;
    }
    public void setRecordObjectionAnswerButton() {
        logger.info("objection answer for student : " + mark.student.username + " recorded");
        mark.objectionAnswer = objectionAnswer.getText();
    }
}
