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

public class ProfessorMinorPageController {
    public Professor professor;
    @FXML
    public ListView minorListView;
    @FXML
    public Button mainPageButton;

    public ObservableList<MinorRequest> items = FXCollections.observableArrayList();


    public void initialize(){
        minorListView.setItems(items);
        minorListView.setCellFactory(new Callback<ListView<MinorRequest>, MyListCell>() {
            @Override
            public MyListCell call(ListView<MinorRequest> minorListView) {
                return new MyListCell();
            }
        });
    }
    class MyListCell extends ListCell<MinorRequest> {
        @Override
        protected void updateItem(MinorRequest minorRequest, boolean b) {
            super.updateItem(minorRequest, b);
            if (minorRequest == null) {
                setGraphic(null);
            }
            else {
                try {
                    setGraphic(minorRequest.showForProfessor(professor, ProfessorMinorPageController.this));
                } catch (IOException ignored) {

                }
            }
        }
    }
    public void setMinorListView() {
        items.clear();
        for (Student student : University.students) {
            if (student.minorRequest.sourceFaculty.equals(professor.faculty.name) && student.minorRequest.sourceAccept == 0 || student.minorRequest.destinationFaculty.equals(professor.faculty.name) && student.minorRequest.destinationAccept == 0) {
                items.add(student.minorRequest);
            }
        }
    }
    public void returnToMainPage() throws IOException {
        professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
}
