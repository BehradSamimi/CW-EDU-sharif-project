package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.Professor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;

public class ProfessorMainPageController {
    public Professor professor;

    @FXML
    public Label localTime;
    @FXML
    public Label name;
    @FXML
    public Label mail;
    @FXML
    public Label lastEnter;
    @FXML
    public Button exit;
    @FXML
    public Pane barPane;
    @FXML
    public MenuButton second;
    @FXML
    public MenuButton first;
    @FXML
    public MenuButton third;
    @FXML
    public MenuItem courseList;
    @FXML
    public ImageView photo;

    public MenuItem add204 = new MenuItem("درخواست های ماینور");
    public MenuItem add205 = new MenuItem("درخواست های انصراف از تحصیل");
    public MenuItem add302 = new MenuItem("وضعیت تحصیلی دانشجویان");
    public MenuItem add103 = new MenuItem("افزودن دانشجو");

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void exit(ActionEvent e) throws IOException {
        professor.lastVisited = LocalDateTime.now();
        logIn.loadLogin((Stage) ((Node) e.getSource()).getScene().getWindow());
    }
    public void goToCourseList(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("ProfessorCourseList.fxml"));
        AnchorPane back = null;
        try {
            back = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        ProfessorCourseListController professorCourseListController = fxmlLoader.getController();
        professorCourseListController.professor = this.professor;
        professorCourseListController.updateCourseListView();
        if (!professor.isEducationalAssistant) {
            professorCourseListController.deleteButton.setVisible(false);
            professorCourseListController.addButton.setVisible(false);
            professorCourseListController.deleteCourseNumber.setVisible(false);
        }
        Stage stage =  (Stage) first.getScene().getWindow();
        logger.info("course list page loaded");
        stage.setScene(scene);
    }
    public void goToProfessorList(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("ProfessorProfessorList.fxml"));
        AnchorPane back = null;
        try {
            back = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        ProfessorProfessorListController professorProfessorListController = fxmlLoader.getController();
        professorProfessorListController.mainProfessor = professor;
        professorProfessorListController.setProfessorListView();
        if (!professor.isDean) {
            professorProfessorListController.newProfessorButton.setVisible(false);
            professorProfessorListController.deleteButton.setVisible(false);
            professorProfessorListController.deletedProfessorNumber.setVisible(false);
        }
        if (professor.isEducationalAssistant)
            professorProfessorListController.newProfessorButton.setVisible(true);
        Stage stage =  (Stage) first.getScene().getWindow();
        logger.info("professor list page loaded");
        stage.setScene(scene);
    }

    public void goToRecomPageList(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorRecomPage.fxml"));
        AnchorPane back = null;
        try {
            back = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        ProfessorRecomPageController professorRecomPageController = loader.getController();
        professorRecomPageController.professor = this.professor;
        professorRecomPageController.setRecomListView();
        Stage stage = (Stage) first.getScene().getWindow();
        logger.info("recommendation requests list page loaded");
        stage.setScene(scene);
    }

    public void goToTemporaryMarkPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorTemporaryMarkPage.fxml"));
        AnchorPane back = null;
        try {
            back = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        ProfessorTemporaryMarkPageController professorTemporaryMarkPageController = loader.getController();
        professorTemporaryMarkPageController.professor = this.professor;
        professorTemporaryMarkPageController.updateCourseList();

        professorTemporaryMarkPageController.temporaryRecordButton.setVisible(false);
        professorTemporaryMarkPageController.finalRecordButton.setVisible(false);
        if (!this.professor.isEducationalAssistant) {
            professorTemporaryMarkPageController.professorNumberFilterButton.setVisible(false);
            professorTemporaryMarkPageController.studentNumberFilterButton.setVisible(false);
            professorTemporaryMarkPageController.studentNumberTextField.setVisible(false);
            professorTemporaryMarkPageController.professorNumberTextField.setVisible(false);

            professorTemporaryMarkPageController.courseListView.setVisible(false);

        }
        Stage stage = (Stage) first.getScene().getWindow();
        logger.info("temporary mark page loaded");
        stage.setScene(scene);
    }

    public void goToWeeklySchedulePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("WeeklySchedulePage.fxml"));
        AnchorPane back = null;
        try {
            back = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        WeeklySchedulePageController weeklySchedulePageController = loader.getController();
        weeklySchedulePageController.professor = this.professor;
        weeklySchedulePageController.courses = this.professor.courses;
        Stage stage = (Stage) first.getScene().getWindow();
        logger.info("weekly schedule page loaded");
        stage.setScene(scene);
    }

    public void goToExamListPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ExamListView.fxml"));
        AnchorPane back = null;
        try {
            back = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        ExamListViewController examListViewController = loader.getController();
        examListViewController.professor = professor;
        examListViewController.courses = professor.courses;
        examListViewController.updatePage();
        Stage stage = (Stage) first.getScene().getWindow();
        logger.info("weekly schedule page loaded");
        stage.setScene(scene);
    }
    public void goToProfileView() throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorProfileView.fxml"));
        AnchorPane back = null;
        try {
            back = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        ProfessorProfileViewController professorProfileViewController = loader.getController();
        professorProfileViewController.professor = this.professor;
        professorProfileViewController.updatePage();
        Stage stage = (Stage) first.getScene().getWindow();
        logger.info("profile View Page loaded");
        stage.setScene(scene);
    }
}
