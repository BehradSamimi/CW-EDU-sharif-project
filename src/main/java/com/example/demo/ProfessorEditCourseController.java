package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Course;
import logic.Professor;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ProfessorEditCourseController {
    public Professor professor;
    public Course course;
    @FXML
    public TextField courseProfessor;
    @FXML
    public TextField courseUnits;
    @FXML
    public TextField courseName;
    @FXML
    public TextField courseFirstSessionDate;
    @FXML
    public TextField courseFirstSessionTime;
    @FXML
    public TextField courseSecondSessionDate;
    @FXML
    public TextField courseSecondSessionTime;
    @FXML
    public TextField courseSection;
    @FXML
    public TextField examDate;
    @FXML
    public TextField examTime;
    @FXML
    public Button recordButton;
    @FXML
    public Button mainPageButton;
    @FXML
    public Button recordnewCourse;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void recordChange(ActionEvent actionEvent) {
        course.name = courseName.getText();
        course.units = Integer.parseInt(courseUnits.getText());
        course.firstSession.date = courseFirstSessionDate.getText();
        course.firstSession.time = courseFirstSessionTime.getText();
        course.secondSession.date = courseSecondSessionDate.getText();
        course.secondSession.time = courseSecondSessionTime.getText();
        course.section = courseSection.getText();
        course.exam.date = examDate.getText();
        course.exam.time = examTime.getText();
        course.professor.courses.remove(course);
        int id = Integer.parseInt(courseProfessor.getText());
        for (Professor pf : University.professors) {
            if (pf.professorNumber == id) {
                course.professor = pf;
                pf.courses.add(course);
                break;
            }
        }
        logger.info("course : " + course.name + " changed");
    }

    public void goToProfessorMainPage() throws IOException {
        professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }

    public void addNewCourse() throws IOException {
        Course course = new Course();
        course.name = courseName.getText();
        course.units = Integer.parseInt(courseUnits.getText());
        course.firstSession.date = courseFirstSessionDate.getText();
        course.firstSession.time = courseFirstSessionTime.getText();
        course.secondSession.date = courseSecondSessionDate.getText();
        course.secondSession.time = courseSecondSessionTime.getText();
        course.section = courseSection.getText();
        course.exam.date = examDate.getText();
        course.exam.time = examTime.getText();
        int id = Integer.parseInt(courseProfessor.getText());
        for (Professor pf : University.professors) {
            if (pf.professorNumber == id) {
                course.professor = pf;
                pf.courses.add(course);
                break;
            }
        }
        University.courses.add(course);
        course.faculty = professor.faculty;
        course.faculty.courses.add(course);
        logger.info("course : " + course.name + " added to courses");
        goToProfessorMainPage();
    }
}
