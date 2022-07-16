package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.Course;
import logic.Student;

import java.io.IOException;

public class StudentEducationalStateController {
    public Student student;

    @FXML
    public Label unitsPassed;
    @FXML
    public Label gradePointAverage;
    @FXML
    public ListView<String> finalMark;
    @FXML
    public Button mainPageButton;

    public void returnToMainPage(ActionEvent actionEvent) throws IOException {
        student.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
    public void setFinalMark() {
        for (Course course : student.passedCourses)
            finalMark.getItems().add(" course : " + course.name + " ,, final mark : " + String.valueOf(course.getMark(student)));
    }

}
