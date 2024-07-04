package lk.ijse.drivingSchool.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.drivingSchool.Repository.ExamRepo;
import lk.ijse.drivingSchool.bo.custom.AddPracticalExamBO;
import lk.ijse.drivingSchool.bo.custom.Impl.AddPracticalExamBOImpl;
import lk.ijse.drivingSchool.entity.Exam;
import lk.ijse.drivingSchool.model.Exam;

import java.sql.SQLException;

public class AddPracticalExamFormController {
    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtTime;
    AddPracticalExamBO addPracticalExamBO = new AddPracticalExamBOImpl();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String date = txtDate.getText();
        String time = txtTime.getText();

        Exam exam = new Exam(date,time);

        try {
            boolean isSaved = addPracticalExamBO.savePracticalExam(exam);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Saved!").show();
                txtDate.setText("");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
        }

    }
}
