package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import logic.MinorRequest;
import logic.Student;
import logic.StudentPersonalInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class StudentMinorController {
    public Student student;
    @FXML
    public Button newRequest;
    @FXML
    public Button mainPageButton;
    @FXML
    public Label requestState;
    @FXML
    public Label destinationFaculty;

    public static Logger logger = LogManager.getLogger(logIn.class);

    public void recordNewMinorRequest(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("ثبت درخواست ماینور جدید");
        dialog.setHeaderText("توجه ! در صورت ثبت درخواست جدید, در خواست قبلی خود به خود حذف می شود");
        dialog.setContentText("نام دانشکده مقصد را به درستی وارد کنید");
        dialog.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            student.minorRequest = new MinorRequest(student);
            student.minorRequest.isEmpty = false;
            student.minorRequest.destinationFaculty = result.get();
            student.minorRequest.sourceFaculty = student.faculty.name;
            logger.info("student : " + student.username + " recorded new minor request for faculty : " + student.minorRequest.destinationFaculty);
        }
        updatePage();
    }
    public void updatePage() {
       destinationFaculty.setAlignment(Pos.CENTER);
       requestState.setAlignment(Pos.CENTER);
       if (student.minorRequest.isEmpty) {
           destinationFaculty.setText("درخواست داده نشده");
           destinationFaculty.setAlignment(Pos.CENTER);
           requestState.setText("درخواست داده نشده");
           requestState.setAlignment(Pos.CENTER);
       }
       else {
           destinationFaculty.setText(student.minorRequest.destinationFaculty);
           if (student.minorRequest.destinationAccept == -1 || student.minorRequest.sourceAccept == -1) {
               requestState.setText("درخواست شما رد شده است");
           }
           else if (student.minorRequest.destinationAccept == 1 && student.minorRequest.sourceAccept == 1) {
               requestState.setText("درخواست شما تایید شده است");
           }
           else {
               requestState.setText("درخواست شما در دست برسی است");
           }
       }

    }
    public void returnToMainPage() throws IOException {
        student.goToMainPage((Stage) mainPageButton.getScene().getWindow());
    }
}
