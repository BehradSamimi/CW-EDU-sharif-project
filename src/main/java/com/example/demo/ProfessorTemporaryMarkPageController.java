package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ProfessorTemporaryMarkPageController {
    public Professor professor;
    public Course course;
    @FXML
    public Button mainPageButton;

    @FXML
    public Button studentNumberFilterButton;
    @FXML
    public Button professorNumberFilterButton;
    @FXML
    public Button courseFilterButton;

    @FXML
    public TextField studentNumberTextField;
    @FXML
    public TextField professorNumberTextField;
    @FXML
    public TextField courseTextField;

    @FXML
    public ListView<String> courseListView;
    @FXML
    public ListView markListView;

    @FXML
    public Button temporaryRecordButton;
    @FXML
    public Button finalRecordButton;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public ObservableList<Mark> items = FXCollections.observableArrayList();
    public void initialize(){
        markListView.setItems(items);
        markListView.setCellFactory(new Callback<ListView<Mark>, MyListCell>() {
            @Override
            public MyListCell call(ListView<Mark> markListView) {
                return new MyListCell();
            }
        });
    }
    class MyListCell extends ListCell<Mark> {
        @Override
        protected void updateItem(Mark mark, boolean b) {
            super.updateItem(mark, b);
            if (mark == null) {
                setGraphic(null);
            }
            else {
                try {
                    setGraphic(mark.showForProfessor(professor));
                } catch (IOException ignored) {

                }
            }
        }
    }

    public void setMarkListViewByCourse() {
        logger.info("mark listView changed whit course filter");
        items.clear();
        temporaryRecordButton.setVisible(false);
        finalRecordButton.setVisible(false);
        String name = courseTextField.getText();
        for (Course pfcourse : professor.courses) {
            System.out.println(pfcourse.name);
            System.out.println(name);
            if (pfcourse.name.equals(name)) {
                temporaryRecordButton.setVisible(true);
                if (pfcourse.isFinalized) {
                    finalRecordButton.setVisible(true);
                }
                items.addAll(pfcourse.marks);
                course = pfcourse;
                return;
            }
        }
        temporaryRecordButton.setVisible(false);
        finalRecordButton.setVisible(false);
        if (professor.isEducationalAssistant) {
            for (Course pfcourse : professor.faculty.courses) {
                if (pfcourse.name.equals(name) && !pfcourse.isDone) {
                    items.addAll(pfcourse.marks);
                }
            }
        }

    }
    public void setMarkListViewByStudent() {
        logger.info("mark list view changed by student filter");
        items.clear();
        temporaryRecordButton.setVisible(false);
        finalRecordButton.setVisible(false);
        int number = Integer.parseInt(studentNumberTextField.getText());
        for (Student student : professor.faculty.students) {
            if (student.studentNumber == number) {
                items.addAll(student.temporaryMarks);
                return;
            }
        }
    }
    public void setMarkListViewByProfessor() {
        logger.info("mark list view changed by student filter");
        items.clear();
        temporaryRecordButton.setVisible(false);
        finalRecordButton.setVisible(false);
        int number = Integer.parseInt(professorNumberTextField.getText());
        for (Professor nwprofessor : professor.faculty.professors) {
            if (number == nwprofessor.professorNumber) {
                for (Course nwcourse : professor.courses) {
                    if (nwcourse.isFinalized) {
                        items.addAll(nwcourse.marks);
                    }
                }
                return;
            }
        }
    }

    public void setTemporaryRecordButton() {
        Boolean isFilled = true;
        for (Mark mark : course.marks) {
            if (mark.isMarkEntered == 0) {
                isFilled = false;
            }
        }
        if (isFilled) {
            logger.info("course : " + course.name + " temporary mark recorded");
            course.isFinalized = true;
            for (Mark mark : course.marks) {
                mark.student.temporaryMarks.add(mark);
            }
            finalRecordButton.setVisible(true);
            updateCourseList();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("خطا");
            alert.setHeaderText(null);
            alert.setContentText("همه نمرات را وارد کنید");
            alert.showAndWait();
        }
    }
    public void setFinalRecordButton() {
        if (!course.isFinalized) {
            return;
        }
        logger.info("course : " + course.name + " marked were finalized");
        course.isDone = true;
        course.professor.courses.remove(course);

        for (Student student : course.students) {
            student.courses.remove(course);
            student.passedCourses.add(course);
        }
        for (Mark mark : course.marks) {
            mark.student.temporaryMarks.remove(mark);
            mark.student.finalMarks.add(mark);
        }
        updateCourseList();
        items.clear();
        temporaryRecordButton.setVisible(false);
        finalRecordButton.setVisible(false);
    }

    public void returnToMainPage() throws IOException {
        professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
    public void updateCourseList() {
        logger.info("course list Updated");
        courseListView.getItems().clear();
        if (professor.isEducationalAssistant) {
            for (Course mycourse : professor.faculty.courses) {
                if (mycourse.isFinalized && !mycourse.isDone) courseListView.getItems().add(mycourse.name);
            }
        }
    }
}
