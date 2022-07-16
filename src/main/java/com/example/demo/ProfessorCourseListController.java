package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.Course;
import logic.Professor;
import logic.Recom;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.zip.CheckedOutputStream;

public class ProfessorCourseListController {
    public Professor professor;
    @FXML
    public TextField facultyTextbox;
    @FXML
    public TextField numberTextbox;
    @FXML
    public TextField sectionTextbox;
    @FXML
    public Button filterButton;
    @FXML
    public Button mainPageButton;
    @FXML
    public ListView courseListView;
    @FXML
    public Button addButton;
    @FXML
    public Button deleteButton;
    @FXML
    public TextField deleteCourseNumber;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void returnToMainPage() throws IOException {
        professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
    public ObservableList<Course> items = FXCollections.observableArrayList();

    public void initialize(){
        courseListView.setItems(items);
        courseListView.setCellFactory(new Callback<ListView<Course>, MyListCell>() {
            @Override
            public MyListCell call(ListView<Course> courseListView) {
                return new MyListCell();
            }
        });
    }
    class MyListCell extends ListCell<Course> {
        @Override
        protected void updateItem(Course course, boolean b) {
            super.updateItem(course, b);
            if (course == null) {
                setGraphic(null);
            }
            else {
                try {
                    setGraphic(course.showForProfessor(professor, (Stage) mainPageButton.getScene().getWindow()));
                } catch (IOException ignored) {

                }
            }
        }
    }
    public void updateCourseListView() {
        logger.info("course list updated");
        items.clear();
        for (Course course : University.courses) {
            items.add(course);
        }
    }
    public void Filter(ActionEvent actionEvent) {
        items.clear();
        String number = numberTextbox.getText();
        String section = sectionTextbox.getText();
        String faculty = facultyTextbox.getText();
        for (Course course : University.courses) {
            if (!course.section.equals(section) && !section.equals("-")) {
                continue;
            }
            if (!number.equals(Integer.toString(course.number)) && !number.equals("-")) {
                continue;
            }
            if (!course.faculty.name.equals(faculty) && !faculty.equals("-")) {
                continue;
            }
            items.add(course);
        }
    }

    public void deleteCourse() {
        int num = Integer.parseInt(deleteCourseNumber.getText());
        for (Course course : professor.faculty.courses) {
            if (course.number == num) {
                course.deleteIt(course);
                logger.info("course : " + course.name + " deleted");
                break;
            }
        }
        updateCourseListView();
    }

    public void creatNewCourse() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("ProfessorEditCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ProfessorEditCourseController professorEditCourseController = fxmlLoader.getController();
        professorEditCourseController.professor = this.professor;
        professorEditCourseController.recordButton.setVisible(false);
        Stage stage = (Stage) addButton.getScene().getWindow();
        logger.info("create course page loaded");
        stage.setScene(scene);
    }
}
