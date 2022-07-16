package com.example.demo;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.Professor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ProfessorProfileViewController {
    public Professor professor;

    public Button mainPageButton;
    public ImageView photo;
    public Button changeThemeButton;
    public TextField mailTextField;
    public TextField phoneNumberTextField;
    public Label nationalNumberLabel;
    public Label facultyLabel;
    public Label professorNumberLabel;
    public Label degreeLabel;
    public Label roomIDLabel;
    public Label nameLabel;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void updatePage() {
        photo.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\profilePhoto.png"));
        mailTextField.setText(professor.personalInformation.mail);
        phoneNumberTextField.setText(professor.personalInformation.phoneNumber);
        nationalNumberLabel.setText(professor.personalInformation.nationalNumber);
        facultyLabel.setText(professor.faculty.name);
        nameLabel.setText(professor.personalInformation.name);
        professorNumberLabel.setText(String.valueOf(professor.professorNumber));
        degreeLabel.setText(professor.degree);
        roomIDLabel.setText(String.valueOf(professor.roomID));
    }
    public void updateMail() {
        professor.personalInformation.mail = mailTextField.getText();
        logger.info("mail changed");
    }
    public void updatePhoneNumber() {
        professor.personalInformation.phoneNumber = phoneNumberTextField.getText();
        logger.info("phone number changed");
    }
    public void returnToMainPage() throws IOException {
        professor.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
    public void setChangeThemeButton() {
        logger.info("theme changed");
        professor.viewTheme = 1 - professor.viewTheme;
    }
}
