package ph.edu.liceo.portal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ph.edu.liceo.portal.MainApp;
import ph.edu.liceo.portal.model.Student;

public class ProfileController {

    @FXML private Label initialsLabel;
    @FXML private Label nameLabel;
    @FXML private Label studentNoLabel;
    @FXML private Label courseLabel;
    @FXML private Label emailLabel;

    // TODO 12: Copy student values into the five labels
    public void setStudent(Student student) {
        if (student == null) return;

        nameLabel.setText(student.getFullName());
        studentNoLabel.setText(student.getStudentNo());
        courseLabel.setText(student.getCourse() + " - Year " + student.getYearLevel());
        emailLabel.setText(student.getEmail());

        // Extract first and last initials (e.g. "Ana Marie Dela Cruz" -> "AC")
        String[] nameParts = student.getFullName().trim().split("\\s+");
        String initials = "";
        if (nameParts.length > 0 && !nameParts[0].isEmpty()) {
            initials += nameParts[0].substring(0, 1).toUpperCase();
        }
        if (nameParts.length > 1) {
            initials += nameParts[nameParts.length - 1].substring(0, 1).toUpperCase();
        }
        initialsLabel.setText(initials);
    }

    @FXML
    private void handleLogout() {
        MainApp.showLogin();
    }
}