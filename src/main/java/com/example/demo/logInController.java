package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.Professor;
import logic.Student;
import logic.University;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class logInController {
    public int captchaNumber = 0;
    public String captcha;
    @FXML
    public Button loginButton;
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public ImageView captchaView;
    @FXML
    public TextField captchaTextField;
    @FXML
    public Button changeCaptchaButton;
    @FXML
    public ImageView backgroundImage;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void checkLogin(ActionEvent e) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String captchaEntered = captchaTextField.getText();
        if (!captchaEntered.equals(captcha)) {
            setCaptch();
            return;
        }
        for (Student student : University.students) {
            if (University.correctLogin(student, username, password)) {
                LocalDateTime nw = LocalDateTime.now();
                Duration duration = Duration.between(student.lastVisited, nw);
                if (duration.getSeconds() < 15) {
                    student.lastEnter = LocalDateTime.now();
                    student.goToMainPage((Stage) ((Node) e.getSource()).getScene().getWindow());
                }
                else {
                    student.showChangePasswordButton((Stage) ((Node) e.getSource()).getScene().getWindow());
                }
                return;
            }
        }
        for (Professor professor : University.professors) {
            if (University.correctLogin(professor, username, password)) {
                LocalDateTime nw = LocalDateTime.now();
                Duration duration = Duration.between(professor.lastVisited, nw);
                if (duration.getSeconds() < (long) 180 * (long) 60) {
                    professor.lastEnter = LocalDateTime.now();
                    professor.goToMainPage((Stage) ((Node) e.getSource()).getScene().getWindow());
                }
                else {
                    professor.showChangePasswordButton((Stage) ((Node) e.getSource()).getScene().getWindow());
                }
                return;
            }
        }
    }

    public void setCaptch() {
        logger.info("new captcha set");
        captchaNumber++;
        captchaNumber = captchaNumber % 5;
        if (captchaNumber == 0) {
            captcha = "qGphJ";
            captchaView.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\captcha1.jpg"));
        }
        if (captchaNumber == 1) {
            captcha = "ibE2";
            captchaView.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\captcha2.png"));
        }
        if (captchaNumber == 2) {
            captcha = "iMKiZ";
            captchaView.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\captcha3.png"));
        }
        if (captchaNumber == 3) {
            captcha = "fdamc";
            captchaView.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\captcha4.png"));
        }
        if (captchaNumber == 4) {
            captcha = "ic8";
            captchaView.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\captcha5.jpg"));
        }
    }
}