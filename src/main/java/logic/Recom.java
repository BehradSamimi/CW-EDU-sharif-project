package logic;

import com.example.demo.ProfessorRecomCellController;
import com.example.demo.StudentRecomController;
import com.example.demo.logIn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class Recom {
    public static ArrayList<Recom> recoms = new ArrayList<>();
    public Student student;
    public Professor professor;
    public String answer;
    public Recom (Student student, Professor professor) {
        this.student = student;
        this.professor = professor;
    }

    public AnchorPane showForStudent() throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("StudentRecom.fxml"));
        loader.load();
        StudentRecomController studentRecomController = loader.getController();
        if (this.answer != null)    studentRecomController.professorRecom.setText(this.answer);
        else studentRecomController.professorRecom.setText("No Answer\n\nplease Wait...");
        studentRecomController.professorName.setText(this.professor.personalInformation.name);
        return studentRecomController.back;
    }

    public AnchorPane showForProfessor() throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorRecomCell.fxml"));
        loader.load();
        ProfessorRecomCellController professorRecomCellController = loader.getController();
        professorRecomCellController.studentName.setText(this.student.personalInformation.fullName);
        professorRecomCellController.studentNumber.setText(String.valueOf(this.student.studentNumber));
        professorRecomCellController.recom = this;
        return professorRecomCellController.back;
    }
}
