package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.Course;
import logic.Student;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class StudentCourseListController {
    Student student;
    @FXML
    public ListView courseListView;
    @FXML
    public TextField facultyTextbox;
    @FXML
    public TextField numberTextbox;
    @FXML
    public TextField sectionTextbox;
    @FXML
    public Button filterButton;

    public ObservableList<Course> items = FXCollections.observableArrayList();

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void initialize(){
        courseListView.setItems(items);
        courseListView.setCellFactory(new Callback<ListView<Course>, ListCell<Course>>() {
            @Override
            public ListCell<Course> call(ListView<Course> courseListView) {
                return new MyListCell();
            }
        });
    }
    static class MyListCell extends ListCell<Course> {
        @Override
        protected void updateItem(Course course, boolean b) {
            super.updateItem(course, b);
            if (course == null) {
                setGraphic(null);
            }
            else {
                try {
                    setGraphic(course.showForStudent());
                } catch (IOException ignored) {

                }
            }
        }
    }
    public void setCourseListView() {
        for (Course myCourse : University.courses) {
            items.add(myCourse);
        }
    }
    public void Filter(ActionEvent actionEvent) {
        logger.info("course list filtered");
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
    public void returnToMainPage() throws IOException {
        student.goToMainPage((Stage) filterButton.getScene().getWindow());
    }
}

