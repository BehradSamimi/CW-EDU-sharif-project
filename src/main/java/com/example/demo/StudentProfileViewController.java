package com.example.demo;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class StudentProfileViewController {
    public Student student;
    public Button mainPageButton;
    public ImageView photo;
    public Button changeThemeButton;
    public TextField mailTextField;
    public TextField phoneNumberTextField;
    public Label nationalNumberLabel;
    public Label facultyLabel;
    public Label studentNumberLabel;
    public Label GpaLabel;
    public Label sectionLabel;
    public Label entranceYearLabel;
    public Label educationalStateLabel;
    public Label nameLabel;
    public Label superVisorLabel;

    public void updatePage() {
        photo.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\profilePhoto.png"));
        mailTextField.setText(student.personalInformation.mail);
        phoneNumberTextField.setText(student.personalInformation.phoneNumber);
        nationalNumberLabel.setText(student.personalInformation.nationalNumber);
        facultyLabel.setText(student.faculty.name);
        studentNumberLabel.setText(String.valueOf(student.studentNumber));
        GpaLabel.setText(String.valueOf(student.getGradePointAverage()));
        entranceYearLabel.setText(student.personalInformation.entranceYear);
        educationalStateLabel.setText(student.educationalState);
        nameLabel.setText(student.personalInformation.fullName);
        //TODO
        if (student.degree == 0) {
            sectionLabel.setText("bachelor");
        }
        if (student.degree == 1) {
            sectionLabel.setText("master");
        }
        if (student.degree == 2) {
            sectionLabel.setText("PHD");
        }
        if (student.supervisor == null) superVisorLabel.setText("NO superVisor");
        else superVisorLabel.setText(student.supervisor.personalInformation.name);
    }
    public static Logger logger = LogManager.getLogger(logIn.class);

    public void updateMail() {
        student.personalInformation.mail = mailTextField.getText();
        logger.info("mail updated");
    }
    public void updatePhoneNumber() {
        student.personalInformation.phoneNumber = phoneNumberTextField.getText();
        logger.info("phone number updated");
    }
    public void returnToMainPage() throws IOException {
        student.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
    public void setChangeThemeButton() {
        logger.info("theme changed");
        student.viewTheme = 1 - student.viewTheme;
    }
}
