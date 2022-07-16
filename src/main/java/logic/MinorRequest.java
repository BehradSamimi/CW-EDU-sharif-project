package logic;

import com.example.demo.ProfessorMinorCellController;
import com.example.demo.ProfessorMinorPageController;
import com.example.demo.logIn;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MinorRequest {
    public Student student;
    public String sourceFaculty = "";
    public String destinationFaculty = "";
    public int sourceAccept = 0, destinationAccept = 0;
    public Boolean isEmpty = true;
    public MinorRequest(Student student) {
        this.student = student;
    }

    public AnchorPane showForProfessor(Professor professor, ProfessorMinorPageController professorMinorPageController) throws IOException {
        FXMLLoader loader = new FXMLLoader(logIn.class.getResource("ProfessorMinorCell.fxml"));
        loader.load();
        ProfessorMinorCellController professorMinorCellController = loader.getController();
        professorMinorCellController.minorRequest = this;
        professorMinorCellController.professorMinorPageController = professorMinorPageController;
        professorMinorCellController.studentName.setText(student.personalInformation.fullName);
        professorMinorCellController.studentNumber.setText(String.valueOf(student.studentNumber));
        professorMinorCellController.sourceFaculty.setText(sourceFaculty);
        professorMinorCellController.destinationFaculty.setText(destinationFaculty);
        return professorMinorCellController.back;
    }

}
