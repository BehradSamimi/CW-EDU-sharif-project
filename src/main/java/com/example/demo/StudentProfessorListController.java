package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.Course;
import logic.Professor;
import logic.Student;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class StudentProfessorListController {
    Student student;
    @FXML
    public ListView professorListView;
    @FXML
    public TextField professorFaculty;
    @FXML
    public TextField professorName ;
    @FXML
    public TextField professorDegree;
    @FXML
    public Button filterButton;

    public ObservableList<Professor> items = FXCollections.observableArrayList();
    public static Logger logger = LogManager.getLogger(logIn.class);


    public void initialize(){
        professorListView.setItems(items);
        professorListView.setCellFactory(new Callback<ListView<Professor>, MyListCell>() {
            @Override
            public MyListCell call(ListView<Professor> professorListView) {
                return new MyListCell();
            }
        });
    }
    class MyListCell extends ListCell<Professor> {
        @Override
        protected void updateItem(Professor professor, boolean b) {
            super.updateItem(professor, b);
            if (professor == null) {
                setGraphic(null);
            }
            else {
                try {
                    setGraphic(professor.showForStudent());
                } catch (IOException ignored) {

                }
            }
        }
    }
    public void setProfessorListView() {
        logger.info("professor listView updated");
        items.addAll(University.professors);
    }
    public void Filter(ActionEvent actionEvent) {
        logger.info("professor List view filtered");
        items.clear();
        String name = professorName.getText();
        String degree = professorDegree.getText();
        String faculty = professorFaculty.getText();
        for (Professor professor : University.professors) {
            if (!professor.personalInformation.name.equals(name) && !name.equals("-")) {
                continue;
            }
            if (!professor.faculty.name.equals(faculty) && !faculty.equals("-")) {
                continue;
            }
            if (!professor.degree.equals(degree) && !degree.equals("-")) {
                continue;
            }
            items.add(professor);
        }
    }
    public void returnToMainPage() throws IOException {
        student.goToMainPage((Stage) filterButton.getScene().getWindow());
    }
}
