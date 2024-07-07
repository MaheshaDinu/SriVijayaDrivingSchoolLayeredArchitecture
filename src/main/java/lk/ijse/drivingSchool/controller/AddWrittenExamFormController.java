package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.AddWrittenExamBO;
import lk.ijse.drivingSchool.bo.custom.Impl.AddWrittenExamBOImpl;
import lk.ijse.drivingSchool.entity.Exam;


import java.sql.SQLException;

public class AddWrittenExamFormController {
    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtTime;
    AddWrittenExamBO addWrittenExamBO = (AddWrittenExamBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_WRITTEN_EXAM);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String date = txtDate.getText();
        String time = txtTime.getText();

        Exam exam = new Exam(date,time);

        try {
            boolean isSaved = addWrittenExamBO.saveWrittenExam(exam);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Saved!").show();
                txtDate.setText("");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
        }

    }
}
