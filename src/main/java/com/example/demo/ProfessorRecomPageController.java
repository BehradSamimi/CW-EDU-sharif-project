package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.Professor;
import logic.Recom;
import logic.University;

import java.io.IOException;

public class ProfessorRecomPageController {
    public Professor professor;
    @FXML
    public ListView recomsListView;
    @FXML
    public Button mainPageButton;

    public ObservableList<Recom> items = FXCollections.observableArrayList();

    public void initialize(){
        recomsListView.setItems(items);
        recomsListView.setCellFactory(new Callback<ListView<Recom>, MyListCell>() {
            @Override
            public MyListCell call(ListView<Recom> recomListView) {
                return new MyListCell();
            }
        });
    }
    class MyListCell extends ListCell<Recom> {
        @Override
        protected void updateItem(Recom recom, boolean b) {
            super.updateItem(recom, b);
            if (recom == null) {
                setGraphic(null);
            }
            else {
                try {
                    setGraphic(recom.showForProfessor());
                } catch (IOException ignored) {

                }
            }
        }
    }
    public void setRecomListView() {
        items.clear();
        for (Recom recom : Recom.recoms) {
            if (recom.professor == professor && recom.answer == null) {
                items.add(recom);
            }
        }
    }
    public void returnToMainPage() throws IOException {
        professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }

}
