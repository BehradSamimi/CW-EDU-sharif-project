package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.Professor;
import logic.Recom;
import logic.Student;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class StudentRecomPageController {
    public Student student;
    @FXML
    public Button recordButton;
    @FXML
    public TextField professorNumber;
    @FXML
    public ListView recomAnswerListView;
    @FXML
    public Button mainPageButton;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void recordRecom(ActionEvent e) {
        int num = Integer.parseInt((String) professorNumber.getText());
        for (Professor professor : University.professors) {
            if (professor.professorNumber == num) {
                Recom.recoms.add(new Recom(student, professor));
                logger.info("new recom recorded for student : " + student.username + " professor : " + professor.username);
                break;
            }
        }
        this.setrecomAnswerListView();
    }

    public ObservableList<Recom> items = FXCollections.observableArrayList();

    public void initialize(){
        recomAnswerListView.setItems(items);
        recomAnswerListView.setCellFactory(new Callback<ListView<Recom>, MyListCell>() {
            @Override
            public MyListCell call(ListView<Recom> recomAnswerListView) {
                return new MyListCell();
            }
        });
    }
    static class MyListCell extends ListCell<Recom> {
        @Override
        protected void updateItem(Recom recom, boolean b) {
            super.updateItem(recom, b);
            if (recom == null) {
                setGraphic(null);
            }
            else {
                try {
                    setGraphic(recom.showForStudent());
                } catch (IOException ignored) {

                }
            }
        }
    }
    public void setrecomAnswerListView() {
        items.clear();
        for (Recom recom : Recom.recoms) {
            if (recom.student == student) {
                items.add(recom);
            }
        }
    }
    public void returnToMainPage() throws IOException {
        student.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
}
