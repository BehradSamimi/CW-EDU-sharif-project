package com.example.demo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import logic.Course;
import logic.Professor;
import logic.Student;

import java.io.IOException;
import java.util.ArrayList;

public class ExamListViewController {
    public Student student;
    public Professor professor;
    public ArrayList<Course> courses;

    @FXML
    public ListView<String> examListView;
    @FXML
    public Label examListLabel;
    @FXML
    public Button mainPageButton;

    public void returnToMainPage() throws IOException {
        if (professor == null) {
            student.goToMainPage((Stage) mainPageButton.getScene().getWindow());
        }
        else {
            professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
        }
    }

    public void updatePage() {
        examListLabel.setAlignment(Pos.CENTER);
        for (Course course : courses) {
            examListView.getItems().add("( " + course.exam.date + "   ->   " + course.exam.time + " )  ->  " + course.name);
        }
        FXCollections.sort(examListView.getItems());
    }
}
