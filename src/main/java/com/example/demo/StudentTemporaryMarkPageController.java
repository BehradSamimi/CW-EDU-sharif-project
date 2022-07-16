package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.Course;
import logic.Mark;
import logic.Recom;
import logic.Student;

import java.io.BufferedInputStream;
import java.io.IOException;

public class StudentTemporaryMarkPageController {

    public Student student;

    @FXML
    public ListView markListView;
    @FXML
    public Button mainPageButton;

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
    static class MyListCell extends ListCell<Mark> {
        @Override
        protected void updateItem(Mark mark, boolean b) {
            super.updateItem(mark, b);
            if (mark == null) {
                setGraphic(null);
            }
            else {
                try {
                    setGraphic(mark.showForStudent());
                } catch (IOException ignored) {

                }
            }
        }
    }
    public void setMarkListView() {
        for (Course course : student.courses) {
            if (!course.isFinalized) {
                continue;
            }
            for (Mark mark : course.marks) {
                if (mark.student == student)  items.add(mark);
            }
        }
    }
    public void returnToMainPage() throws IOException {
        student.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
}
