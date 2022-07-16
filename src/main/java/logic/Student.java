package logic;

import com.example.demo.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class Student {
    public int viewTheme;
    public int degree = 0;

    //TODO -> okinLoad
    public transient ArrayList<Course> courses = new ArrayList<Course>();
    public ArrayList<Integer> coursesJSON = new ArrayList<>();

    public transient ArrayList<Course> passedCourses = new ArrayList<>();

    //TODO -> okinLoad
    public transient Faculty faculty;
    public int facultyJSON;

    //TODO -> okinLoad
    public transient Professor supervisor;
    public int supervisorJSON = -1;

    public StudentPersonalInformation personalInformation = new StudentPersonalInformation();
    public String username;
    public String password;
    public String major;
    public int studentNumber;
    public static int ID = 1;
    public boolean quitrequest;
    public int isQuitrequestAccepted;
    public int dormRequest;
    public String educationalState;
    public LocalDateTime lastVisited = LocalDateTime.now();
    public LocalDateTime lastEnter= LocalDateTime.now();

    //TODO -> okinLoad
    public transient MinorRequest minorRequest = new MinorRequest(this);

    //TODO -> okinLoad
    public transient ArrayList<Mark> temporaryMarks = new ArrayList<>();

    //TODO -> okinLoad
    public transient ArrayList<Mark> finalMarks = new ArrayList<>();

    public static Logger logger = LogManager.getLogger(logIn.class);

    public Student() {
        this.studentNumber = ID++;
    }

    public int getPassedUnits() {
        int rtr = 0;
        for (Course course : passedCourses)
            rtr += course.units;
        return rtr;
    }

    public double getGradePointAverage() {
        double ans = 0, rtr = 0;
        for (Course course : passedCourses) {
            ans += course.units * course.getMark(this);
            rtr += course.units;
        }
        ans = ans / rtr;
        return ans;
    }

    public void goToMainPage(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("MainStudentPage.fxml"));
            AnchorPane back = fxmlLoader.load();
            if (this.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
            else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
            Scene scene = new Scene(back);
            stage.setTitle("EDU");
            stage.setScene(scene);
            MainStudentPageController mainStudentPageController = fxmlLoader.getController();
            mainStudentPageController.student = this;
            mainStudentPageController.photo.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\profilePhoto.png"));
            // set Time
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
            Thread clock = new Thread()
         {
             public void run() {
                 try {
                    while(true) {
                        LocalDateTime now = LocalDateTime.now();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                mainStudentPageController.localTime.setText(dtf.format(now));
                            }
                        });
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        clock.start();
            // setName e-mail lastEnter
            mainStudentPageController.name.setText(this.personalInformation.fullName);
            mainStudentPageController.mail.setText(this.personalInformation.mail);
            //set last enter
            mainStudentPageController.lastEnter.setText(dtf.format(lastEnter));
            //correct second menu button
            fineSecondMenubar(mainStudentPageController.second, mainStudentPageController);
            //CorrectLabel
            mainStudentPageController.superVisorLabel.setAlignment(Pos.CENTER);
            mainStudentPageController.registerTimeLabel.setAlignment(Pos.CENTER);
            mainStudentPageController.permissionLabel.setAlignment(Pos.CENTER);
            if (this.supervisor == null) mainStudentPageController.superVisorLabel.setText("No SuperVisor");
            else mainStudentPageController.superVisorLabel.setText("Your SuperVisor is Mr/Ms : " + this.supervisor.personalInformation.name);
            mainStudentPageController.permissionLabel.setText("You can Register right now");
            mainStudentPageController.registerTimeLabel.setText("Your Register Time is : " + "1401/6/1 08:00");
            //Done
            logger.info("main page of Student : " + this.username + " loaded");
            stage.show();
    }

    public void fineSecondMenubar(MenuButton second, MainStudentPageController mainStudentPageController) {
        if (this.degree == 0)
            second.getItems().addAll(mainStudentPageController.add203, mainStudentPageController.add204, mainStudentPageController.add205, mainStudentPageController.add206);
        if (this.degree == 1)
            second.getItems().addAll(mainStudentPageController.add203, mainStudentPageController.add204, mainStudentPageController.add215, mainStudentPageController.add206);
        if (this.degree == 2)
            second.getItems().addAll(mainStudentPageController.add204, mainStudentPageController.add206, mainStudentPageController.add224);
        setActionOnSecondMenubar(second, mainStudentPageController);
    }
    public void setActionOnSecondMenubar(MenuButton second, MainStudentPageController mainStudentPageController) {
        //TODO
        setActionOnAdd203(second, mainStudentPageController);
        setActionOnAdd204(second, mainStudentPageController);
        setActionOnAdd206(second, mainStudentPageController);
        setActionOnAdd215(second, mainStudentPageController);
        setActionOnAdd224(second, mainStudentPageController);
        setActionOnAdd205(second, mainStudentPageController);
    }
    public void setActionOnAdd203(MenuButton second, MainStudentPageController mainStudentPageController) {
        mainStudentPageController.add203.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("StudentRecomPage.fxml"));
                Scene scene = null;
                AnchorPane back = null;
                try {
                    back = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (mainStudentPageController.student.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
                else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
                scene = new Scene(back);
                StudentRecomPageController studentRecomPageController = fxmlLoader.getController();
                studentRecomPageController.student = mainStudentPageController.student;
                studentRecomPageController.setrecomAnswerListView();
                Stage stage =  (Stage) second.getScene().getWindow();
                logger.info("Recommendation Page loaded");
                stage.setScene(scene);
            }
        });
    }
    public void setActionOnAdd204(MenuButton second, MainStudentPageController mainStudentPageController) {
        mainStudentPageController.add204.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("اشتغال به نحصیل");
                alert.setHeaderText(null);
                alert.setContentText("student " + mainStudentPageController.student.personalInformation.fullName + " is studing in major " + mainStudentPageController.student.major + " till 1404");
                logger.info("enrollment certificate dialog loaded");
                alert.showAndWait();
            }
        });
    }
    public void setActionOnAdd206(MenuButton second, MainStudentPageController mainStudentPageController) {
        mainStudentPageController.add206.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                mainStudentPageController.student.quitrequest = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ثبت درخواست");
                alert.setHeaderText(null);
                alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                if (mainStudentPageController.student.isQuitrequestAccepted == 0)
                    alert.setContentText("درخواست شما به معاون اموزشی دانشکده ازسال شد و در دست بررسی است");
                else if (mainStudentPageController.student.isQuitrequestAccepted == -1)
                    alert.setContentText("در خواست شما رد شده است");
                logger.info("quit request recorded");
                alert.showAndWait();
            }
        });
    }
    public void setActionOnAdd224(MenuButton second, MainStudentPageController mainStudentPageController) {
        mainStudentPageController.add224.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("نوبت پایان نامه");
                alert.setHeaderText(null);
                alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                alert.setContentText("برای شما نوبت در تاریخ " + "1404.02.06" + "ثبت شد");
                logger.info("dissertation time given");
                alert.showAndWait();
            }
        });
    }
    public void setActionOnAdd215(MenuButton second, MainStudentPageController mainStudentPageController) {
        mainStudentPageController.add215.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                logger.info("dorm request recorded");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("در خواست خوابگاه");
                alert.setHeaderText(null);
                alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                if (dormRequest == 0) {
                    dormRequest = (int) Math.floor(Math.random() * 10) % 2;
                    if (dormRequest == 0) dormRequest = 1;
                    else dormRequest = -1;
                }
                if (dormRequest == -1) {
                    logger.info("dorm request Accepted");
                    alert.setContentText("درخواست شما پدیرفته شد");
                }
                if (dormRequest == 1) {
                    logger.info("dorm request rejected");
                    alert.setContentText("درخواست شما رد شد");
                }
                alert.showAndWait();
            }
        });
    }
    public void setActionOnAdd205(MenuButton second, MainStudentPageController mainStudentPageController) {
        mainStudentPageController.add205.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("StudentMinor.fxml"));
                Scene scene = null;
                AnchorPane back = null;
                try {
                    back = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (mainStudentPageController.student.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
                else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
                scene = new Scene(back);
                StudentMinorController studentMinorController = fxmlLoader.getController();
                studentMinorController.student = mainStudentPageController.student;
                Stage stage =  (Stage) second.getScene().getWindow();
                studentMinorController.updatePage();
                logger.info("minor page loaded");
                stage.setScene(scene);
            }
        });
    }
    public AnchorPane showForQuit(ProfessorQuitPageController professorQuitPageController) throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorQuitCell.fxml"));
        loader.load();
        ProfessorQuitCellController professorQuitCellController = loader.getController();
        professorQuitCellController.student = this;
        professorQuitCellController.professorQuitPageController = professorQuitPageController;
        professorQuitCellController.studentName.setText(this.personalInformation.fullName);
        professorQuitCellController.studentNumber.setText(String.valueOf(this.studentNumber));
        return professorQuitCellController.back;
    }
    public void showChangePasswordButton(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ChangePasswordPage.fxml"));
        Scene scene = new Scene(loader.load());
        ChangePasswordPageController changePasswordPageController = loader.getController();
        changePasswordPageController.student = this;
        logger.info("change password page loaded");
        stage.setScene(scene);
    }
}
