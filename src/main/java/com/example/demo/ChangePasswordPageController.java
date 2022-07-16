package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Professor;
import logic.Student;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ChangePasswordPageController {
    public Professor professor;
    public Student student;

    @FXML
    public Button changeButton;
    @FXML
    public Button loginPageButton;
    @FXML
    public TextField oldPass;
    @FXML
    public TextField newPass;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void setChangeButton() throws IOException {
        String oldPassword = oldPass.getText(), newPassword = newPass.getText();
        if (student != null) {
            if (!student.password.equals(University.hash(oldPassword))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("خطا");
                alert.setHeaderText(null);
                alert.setContentText("پسورد قدیمی وراد شده اشتباه هست");
                alert.showAndWait();
            }
            else {
                if (University.hash(oldPassword).equals(newPassword)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("خظا");
                    alert.setHeaderText(null);
                    alert.setContentText("پسورد قبلی با پسورد جدید برابر است");

                    alert.showAndWait();
                }
                else {
                    student.password = University.hash(newPassword);
                    student.goToMainPage((Stage) changeButton.getScene().getWindow());
                    logger.info("Student : " + student.username + " password Changed successfully");

                }
            }
        }
        else {
            if (!professor.password.equals(oldPassword)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("خطا");
                alert.setHeaderText(null);
                alert.setContentText("پسورد قدیمی وراد شده اشتباه هست");
                alert.showAndWait();
            }
            else {
                if (oldPassword.equals(newPassword)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("خظا");
                    alert.setHeaderText(null);
                    alert.setContentText("پسورد قبلی با پسورد جدید برابر است");

                    alert.showAndWait();
                }
                else {
                    professor.password = newPassword;
                    professor.goToMainPage((Stage) changeButton.getScene().getWindow());
                    logger.info("professor : " + professor.username + " password changed successfully");
                }
            }
        }
    }

    public void setLoginPageButton() throws IOException {
        logIn.loadLogin((Stage) loginPageButton.getScene().getWindow());
    }

}
