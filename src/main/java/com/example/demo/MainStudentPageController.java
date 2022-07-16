package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import logic.Course;
import logic.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;

public class MainStudentPageController {
    public Student student;

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
    public Label superVisorLabel;
    @FXML
    public Label registerTimeLabel;
    @FXML
    public Label permissionLabel;
    @FXML
    public ImageView photo;
    // adad aval = shomare menuButton , adad dovom => degree avalin Student, adad sevom => shomare zirmenu
    public MenuItem add203 = new MenuItem("درخواست توصیه نامه");
    public MenuItem add204 = new MenuItem("درخواست اشتغال به تحصیل");
    public MenuItem add205 = new MenuItem("درخواست ماینور");
    public MenuItem add206 = new MenuItem("درخواست انصراف از تحصیل");

    public MenuItem add215 = new MenuItem("درخواست خوابگاه");

    public MenuItem add224 = new MenuItem("درخواست نوبت دفاع پایان نامه");

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void exit(ActionEvent e) throws IOException {
        student.lastVisited = LocalDateTime.now();
        logIn.loadLogin((Stage) ((Node) e.getSource()).getScene().getWindow());
    }
    public void goToCourseList(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("StudentCourseList.fxml"));
        AnchorPane back = null;
        try {
            back = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (student.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        StudentCourseListController studentCourseListController = fxmlLoader.getController();
        studentCourseListController.student = student;
        studentCourseListController.setCourseListView();
        Stage stage =  (Stage) first.getScene().getWindow();
        logger.info("course List loaded");
        stage.setScene(scene);
    }
    public void goToProfessorList(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("StudentProfessorList.fxml"));
        AnchorPane back = null;
        try {
            back = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (student.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        StudentProfessorListController studentProfessorListController = fxmlLoader.getController();
        studentProfessorListController.student = student;
        studentProfessorListController.setProfessorListView();
        Stage stage =  (Stage) first.getScene().getWindow();
        logger.info("professor List loaded");
        stage.setScene(scene);
    }
    public void goToTemporaryMarkList(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("StudentTemporaryMarkPage.fxml"));
        AnchorPane back = null;
        try {
            back = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (student.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        StudentTemporaryMarkPageController studentTemporaryMarkPageController = fxmlLoader.getController();
        studentTemporaryMarkPageController.student = student;
        studentTemporaryMarkPageController.setMarkListView();
        Stage stage =  (Stage) first.getScene().getWindow();
        logger.info("temporary mark list loaded");
        stage.setScene(scene);
    }

    public void goToEducationalState(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("StudentEducationalState.fxml"));
        AnchorPane back = null;
        try {
            back = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (student.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        StudentEducationalStateController studentEducationalStateController = fxmlLoader.getController();
        studentEducationalStateController.student = this.student;
        studentEducationalStateController.setFinalMark();
        studentEducationalStateController.unitsPassed.setText(String.valueOf(student.getPassedUnits()));
        studentEducationalStateController.gradePointAverage.setText(String.valueOf(student.getGradePointAverage()));
        Stage stage =  (Stage) first.getScene().getWindow();
        logger.info("educational state page loaded");
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
        if (student.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        WeeklySchedulePageController weeklySchedulePageController = loader.getController();
        weeklySchedulePageController.student = this.student;
        weeklySchedulePageController.courses = this.student.courses;
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
        if (student.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        ExamListViewController examListViewController = loader.getController();
        examListViewController.student = student;
        examListViewController.courses = student.courses;
        examListViewController.updatePage();
        Stage stage = (Stage) first.getScene().getWindow();
        logger.info("exam list page loaded");
        stage.setScene(scene);
    }

    public void goToProfileView() throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("StudentProfileView.fxml"));
        AnchorPane back = null;
        try {
            back = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (student.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        StudentProfileViewController studentProfileViewController = loader.getController();
        studentProfileViewController.student = this.student;
        studentProfileViewController.updatePage();
        Stage stage = (Stage) first.getScene().getWindow();
        logger.info("profile View page loaded");
        stage.setScene(scene);
    }
}
