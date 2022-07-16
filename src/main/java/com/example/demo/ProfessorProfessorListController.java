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
import logic.Professor;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ProfessorProfessorListController {
    public Professor mainProfessor;
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
    @FXML
    public Button deleteButton;
    @FXML
    public Button newProfessorButton;
    @FXML
    public TextField deletedProfessorNumber;
    @FXML
    public Button mainPageButton;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public ObservableList<Professor> items = FXCollections.observableArrayList();

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
                    setGraphic(professor.showForProfessor(mainProfessor, (Stage) mainPageButton.getScene().getWindow()));
                } catch (IOException ignored) {

                }
            }
        }
    }
    public void setProfessorListView() {
        logger.info("professor ListView updated");
        items.clear();
        items.addAll(University.professors);
    }

    public void returnToMainPage() throws IOException {
        mainProfessor.goToMainPage((Stage) filterButton.getScene().getWindow());
    }

    public void Filter(ActionEvent actionEvent) {
        logger.info("professor list View filtered");
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

    public void setNewProfessorButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("ProfessorEditProfessor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ProfessorEditProfessorController professorEditProfessorController = fxmlLoader.getController();
        professorEditProfessorController.changeProfessorButton.setVisible(false);
        professorEditProfessorController.addEducationalAssistant.setVisible(false);
        professorEditProfessorController.deleteEducationalAssistant.setVisible(false);
        professorEditProfessorController.mainProfessor = this.mainProfessor;
        Stage stage = (Stage) newProfessorButton.getScene().getWindow();
        logger.info("new professor Page loaded");
        stage.setScene(scene);
    }

    public void setDeleteButton() {
        int num = Integer.parseInt(deletedProfessorNumber.getText());
        for (Professor professor : mainProfessor.faculty.professors) {
            if (professor.professorNumber == num) {
                professor.deleteIt();
                logger.info("professor : " + professor.username + " deleted successfully");
                break;
            }
        }
        setProfessorListView();
    }
}
