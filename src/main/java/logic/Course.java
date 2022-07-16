package logic;

import com.example.demo.ListCellForStudentController;
import com.example.demo.ProfessorCourseListCellController;
import com.example.demo.logIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Course {
    //TODO -> okinLoad
    public transient ArrayList<Student> students = new ArrayList<Student>();
    public ArrayList<Integer> studentsJSON = new ArrayList<>();

    //TODO -> okinLoad
    public transient Professor professor;
    public int professorJSON;

    //TODO -> okinLoad
    public transient Faculty faculty;
    public int facultyJSON;

    public int units = 0, number = 0;
    public static int ID = 1;
    public String name = "";
    public String section = "";

    //TODO -> okinLoad
    public transient ArrayList<Mark> marks = new ArrayList<>();

    public Boolean isDeleted = false;
    public Session firstSession = new Session(), secondSession = new Session();
    public Exam exam = new Exam();
    public boolean isFinalized = false;
    public boolean isDone = false;

    public Course () {
        this.number = ID++;
    }
    public double getMark(Student student) {
        for (Mark mark : marks) {
            if (mark.student == student) {
                return mark.markValue;
            }
        }
        return 0.0;
    }
    public AnchorPane showForStudent() throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ListCellForStudent.fxml"));
        loader.load();
        ListCellForStudentController listCellForStudentController = loader.getController();
        AnchorPane root = listCellForStudentController.back;
        listCellForStudentController.courseName.setAlignment(Pos.CENTER);
        listCellForStudentController.courseName.setText(this.name);
        listCellForStudentController.courseNumber.setAlignment(Pos.CENTER);
        listCellForStudentController.courseNumber.setText(Integer.toString(this.number));
        listCellForStudentController.courseSection.setAlignment(Pos.CENTER);
        listCellForStudentController.courseSection.setText(this.section);
        listCellForStudentController.courseProfessor.setAlignment(Pos.CENTER);
        listCellForStudentController.courseProfessor.setText(this.professor.personalInformation.name);
        listCellForStudentController.courseUnits.setAlignment(Pos.CENTER);
        listCellForStudentController.courseUnits.setText(Integer.toString(this.units));
        listCellForStudentController.firstSession.setText(this.firstSession.date + ", " + this.firstSession.time);
        listCellForStudentController.secondSession.setText(this.secondSession.date + ", " + this.secondSession.time);

        return root;
    }
    public AnchorPane showForProfessor(Professor professor, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorCourseListCell.fxml"));
        loader.load();
        ProfessorCourseListCellController professorCourseListCellController = loader.getController();
        professorCourseListCellController.course = this;
        professorCourseListCellController.stage = stage;
        professorCourseListCellController.professor = professor;
        professorCourseListCellController.courseName.setText(this.name);
        professorCourseListCellController.courseNumber.setText(String.valueOf(this.number));
        professorCourseListCellController.courseProfessor.setText(this.professor.personalInformation.name);
        professorCourseListCellController.courseUnits.setText(String.valueOf(this.units));
        professorCourseListCellController.firstSession.setText(this.firstSession.date + " , " + this.firstSession.time);
        professorCourseListCellController.secondSession.setText(this.secondSession.date + " , " + this.secondSession.time);

        for (Student student : this.students)
            professorCourseListCellController.stdListView.getItems().add(student.personalInformation.fullName);
        if (!professor.isEducationalAssistant || this.faculty != professor.faculty)
            professorCourseListCellController.editButton.setVisible(false);
        return professorCourseListCellController.back;
    }
    public void deleteIt(Course course) {
        University.courses.remove(course);
        this.faculty.courses.remove(course);
        this.professor.courses.remove(course);
        for (Student student : this.students) {
            student.courses.remove(course);
            break;
        }
        course.isDeleted = true;
    }
}
