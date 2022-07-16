package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Course;
import logic.Professor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ProfessorCourseListCellController {
    public Professor professor;
    public Course course;
    public Stage stage;
    @FXML
    public Label courseName;
    @FXML
    public Label courseUnits;
    @FXML
    public Label courseNumber;
    @FXML
    public Label courseProfessor;
    @FXML
    public Label firstSession;
    @FXML
    public Label secondSession;
    @FXML
    public Button editButton;
    @FXML
    public ListView<String> stdListView;
    @FXML
    public AnchorPane back;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void setEditButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("ProfessorEditCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ProfessorEditCourseController professorEditCourseController = fxmlLoader.getController();
        professorEditCourseController.professor = this.professor;
        professorEditCourseController.course = this.course;
        professorEditCourseController.recordnewCourse.setVisible(false);
        logger.info("edit course Page for course : " + course.name + " loaded");
        stage.setScene(scene);
    }
}
