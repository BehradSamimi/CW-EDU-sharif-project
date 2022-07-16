package logic;

import com.example.demo.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Professor {
    public int viewTheme;

    //TODO -> okinLoad
    public transient ArrayList<Course> courses = new ArrayList<Course>();
    public ArrayList<Integer> coursesJSON = new ArrayList<Integer>();

    public ProfessorPersonalInformation personalInformation = new ProfessorPersonalInformation();
    public String username = "";
    public String password = "";
    public String degree = "";

    //TODO -> okinLoad
    public transient Faculty faculty;
    public int facultyJSON;

    public int professorNumber;
    public static int ID = 1;
    public Boolean isEducationalAssistant = false;
    public Boolean isDean = false;
    public Boolean isDeleted = false;
    public int roomID = 0;
    public LocalDateTime lastVisited = LocalDateTime.now();
    public LocalDateTime lastEnter = LocalDateTime.now();
    public static Logger logger = LogManager.getLogger(logIn.class);
    public Professor() {
        this.professorNumber = ID++;
    }

    public void goToMainPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("ProfessorMainPage.fxml"));
        AnchorPane back = null;
        try {
            back = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
        else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
        Scene scene = new Scene(back);
        stage.setTitle("EDU");
        stage.setScene(scene);
        ProfessorMainPageController professorMainPageController = fxmlLoader.getController();
        professorMainPageController.professor = this;
        professorMainPageController.photo.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\profilePhoto.png"));
        // set Time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        professorMainPageController.localTime.setText(dtf.format(now));
        // setName e-mail lastEnter
        professorMainPageController.name.setText(this.personalInformation.name);
        professorMainPageController.mail.setText(this.personalInformation.mail);
        //set laseEnter
        professorMainPageController.lastEnter.setText(dtf.format(lastEnter));
        //correct second menu button
        fineFirstMenuBar(professorMainPageController);
        fineSecondMenuBar(professorMainPageController);
        fineThirdMenuBar(professorMainPageController);
        logger.info("main page for professor : " + this.username + " loaded");
        stage.show();
    }

    public void fineFirstMenuBar(ProfessorMainPageController professorMainPageController) {
        if (this.isEducationalAssistant)
            professorMainPageController.first.getItems().add(professorMainPageController.add103);
        setActionOnFirstMenuBar(professorMainPageController);
    }

    public void fineSecondMenuBar(ProfessorMainPageController professorMainPageController) {
        if (this.isEducationalAssistant)
            professorMainPageController.second.getItems().addAll(professorMainPageController.add204, professorMainPageController.add205);
        setActionOnSecondMenuBar(professorMainPageController);
    }

    public void fineThirdMenuBar(ProfessorMainPageController professorMainPageController) {
        if (this.isEducationalAssistant)
            professorMainPageController.third.getItems().add(professorMainPageController.add302);
        setActionOnThirdMenuBar(professorMainPageController);
    }

    public void setActionOnFirstMenuBar(ProfessorMainPageController professorMainPageController) {
        professorMainPageController.add103.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorAddStudentPage.fxml"));
                AnchorPane back = null;
                try {
                    back = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (professorMainPageController.professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
                else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
                Scene scene = new Scene(back);
                ProfessorAddStudentPageController professorAddStudentPageController = loader.getController();
                professorAddStudentPageController.professor = professorMainPageController.professor;
                Stage stage = (Stage) professorMainPageController.second.getScene().getWindow();
                logger.info("add student page loaded");
                stage.setScene(scene);
            }

        });
    }

    public void setActionOnSecondMenuBar(ProfessorMainPageController professorMainPageController) {
        professorMainPageController.add204.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorMinorPage.fxml"));
                AnchorPane back = null;
                try {
                    back = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (professorMainPageController.professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
                else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
                Scene scene = new Scene(back);
                ProfessorMinorPageController professorMinorPageController = loader.getController();
                professorMinorPageController.professor = professorMainPageController.professor;
                professorMinorPageController.setMinorListView();
                Stage stage = (Stage) professorMainPageController.second.getScene().getWindow();
                logger.info("minor request page loaded");
                stage.setScene(scene);
            }

        });
        professorMainPageController.add205.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfesserQuitPage.fxml"));
                AnchorPane back = null;
                try {
                    back = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (professorMainPageController.professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
                else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
                Scene scene = new Scene(back);
                ProfessorQuitPageController professorQuitPageController = loader.getController();
                professorQuitPageController.professor = professorMainPageController.professor;
                professorQuitPageController.setQuitListView();
                Stage stage = (Stage) professorMainPageController.second.getScene().getWindow();
                logger.info("quit request page loaded");
                stage.setScene(scene);
            }

        });
    }

    public void setActionOnThirdMenuBar(ProfessorMainPageController professorMainPageController) {
        professorMainPageController.add302.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                FXMLLoader loader = new FXMLLoader(logIn.class.getResource("EducationalAssistantStudentStates.fxml"));
                AnchorPane back = null;
                try {
                    back = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (professorMainPageController.professor.viewTheme == 1) back.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, new Insets(0))));
                else back.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(0))));
                Scene scene = new Scene(back);
                EducationalAssistantStudentStatesController educationalAssistantStudentStatesController = loader.getController();
                educationalAssistantStudentStatesController.studentFinallMarksLabel.setAlignment(Pos.CENTER);
                educationalAssistantStudentStatesController.studentStatesLabel.setAlignment(Pos.CENTER);
                educationalAssistantStudentStatesController.professor = professorMainPageController.professor;
                educationalAssistantStudentStatesController.setListViews();
                Stage stage = (Stage) professorMainPageController.second.getScene().getWindow();
                logger.info("students educational states loaded");
                stage.setScene(scene);
            }

        });
    }

    public AnchorPane showForStudent() throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorListCellForStudent.fxml"));
        loader.load();
        ProfessorListCellForStudentController professorListCellForStudentController = loader.getController();
        AnchorPane root = professorListCellForStudentController.back;
        professorListCellForStudentController.name.setAlignment(Pos.CENTER);
        professorListCellForStudentController.name.setText(this.personalInformation.name);
        professorListCellForStudentController.mail.setAlignment(Pos.CENTER);
        professorListCellForStudentController.mail.setText(this.personalInformation.mail);
        professorListCellForStudentController.faculty.setAlignment(Pos.CENTER);
        professorListCellForStudentController.faculty.setText(this.faculty.name);
        professorListCellForStudentController.degree.setAlignment(Pos.CENTER);
        professorListCellForStudentController.degree.setText(this.degree);
        return root;
    }

    public AnchorPane showForProfessor(Professor professor, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorProfessorListCell.fxml"));
        loader.load();
        ProfessorProfessorListCellController professorProfessorListCellController = loader.getController();
        professorProfessorListCellController.mainProfessor = professor;
        professorProfessorListCellController.professor = this;
        professorProfessorListCellController.stage = stage;
        professorProfessorListCellController.professorName.setText(this.personalInformation.name);
        professorProfessorListCellController.professorMail.setText(this.personalInformation.mail);
        professorProfessorListCellController.professorDegree.setText(this.degree);
        professorProfessorListCellController.professorFaculty.setText(this.faculty.name);
        professorProfessorListCellController.professorNumber.setText(String.valueOf(this.professorNumber));
        if (!professor.isDean || professor.faculty != this.faculty) professorProfessorListCellController.editButton.setVisible(false);
        else professorProfessorListCellController.editButton.setVisible(true);

        return professorProfessorListCellController.back;
    }

    public void deleteIt() {
        if (this.faculty.dean == this) {
            return;
        }
        if (this.faculty.educationalAssistant == this) this.faculty.educationalAssistant = null;
        University.professors.remove(this);
        this.faculty.professors.remove(this);
        this.isDeleted = true;
        for (Student student : this.faculty.students) {
            if (student.supervisor == this)
                student.supervisor = null;
        }
    }

    public void showChangePasswordButton(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ChangePasswordPage.fxml"));
        Scene scene = new Scene(loader.load());
        ChangePasswordPageController changePasswordPageController = loader.getController();
        changePasswordPageController.professor = this;
        logger.info("change password page loaded");
        stage.setScene(scene);
    }

}
