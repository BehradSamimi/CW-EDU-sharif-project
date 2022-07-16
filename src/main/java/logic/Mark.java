package logic;

import com.example.demo.ProfessorTemporaryMarkCellController;
import com.example.demo.StudentTemporaryMarkCellController;
import com.example.demo.logIn;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Mark {
    public Student student;
    public Course course;
    public double markValue;
    public int isMarkEntered = 0;
    public String objectionText, objectionAnswer;
    public void correctMarkValue() {
        //markValue = Math.((double) (markValue * (double) 100)) / (double) 100;
        if (markValue > 20) markValue = 20;
        if (markValue < 0) markValue = 0;
        markValue = (double) markValue * 100;
        markValue = (int) markValue;
        markValue = markValue - (markValue % 25);
        markValue = (double) markValue / (double) 100;
    }

    public AnchorPane showForStudent() throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("StudentTemporaryMarkCell.fxml"));
        loader.load();
        StudentTemporaryMarkCellController studentTemporaryMarkCellController = loader.getController();
        studentTemporaryMarkCellController.mark = this;
        studentTemporaryMarkCellController.updatePage();
        return studentTemporaryMarkCellController.back;
    }

    public AnchorPane showForProfessor(Professor professor) throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorTemporaryMarkCell.fxml"));
        loader.load();
        ProfessorTemporaryMarkCellController professorTemporaryMarkCellController = loader.getController();
        professorTemporaryMarkCellController.mark = this;
        professorTemporaryMarkCellController.updatePage();
        if (professor != this.course.professor) {
            professorTemporaryMarkCellController.updatePageForEducationalAssistant();
        }
        return professorTemporaryMarkCellController.back;
    }

}
