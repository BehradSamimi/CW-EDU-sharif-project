package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.*;

import java.io.IOException;

public class ProfessorQuitPageController {
    public Professor professor;
    @FXML
    public ListView quitListView;
    @FXML
    public Button mainPageButton;

    public ObservableList<Student> items = FXCollections.observableArrayList();


    public void initialize(){
        quitListView.setItems(items);
        quitListView.setCellFactory(new Callback<ListView<Student>, MyListCell>() {
            @Override
            public MyListCell call(ListView<Student> quitListView) {
                return new MyListCell();
            }
        });
    }
    class MyListCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean b) {
            super.updateItem(student, b);
            if (student == null) {
                setGraphic(null);
            }
            else {
                try {
                    setGraphic(student.showForQuit(ProfessorQuitPageController.this));
                } catch (IOException ignored) {

                }
            }
        }
    }
    public void setQuitListView() {

        items.clear();
        for (Student student : professor.faculty.students) {
            if (student.quitrequest == true && student.isQuitrequestAccepted == 0) {
                items.add(student);
            }
        }
    }
    public void returnToMainPage() throws IOException {
        professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
}
