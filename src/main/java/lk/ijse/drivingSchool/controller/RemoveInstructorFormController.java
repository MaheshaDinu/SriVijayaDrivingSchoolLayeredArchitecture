package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.Impl.RemoveInstructorBOImpl;
import lk.ijse.drivingSchool.bo.custom.RemoveInstructorBO;
import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class RemoveInstructorFormController {
    @FXML
    private Label lblInstructorName;


    @FXML
    private TextField txtLicense;
    RemoveInstructorBO removeInstructorBO = (RemoveInstructorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REMOVE_INSTRUCTOR);

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        String license = txtLicense.getText();
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Do you really want to remove this instructor?", yes, no).showAndWait();
        try {
            if(type.orElse(no) == yes) {
                boolean isRemoved = removeInstructorBO.delete(license);
                if (isRemoved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Instructor Removed!").show();
                    txtLicense.setText("");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Instructor Not Removed!").show();
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }



    }

    @FXML
    void txtLicenseReleaseOnAction(KeyEvent event) {
        String license = txtLicense.getText();


        try {
            Instructor instructor = removeInstructorBO.get(license);
            if (instructor!=null){
                lblInstructorName.setTextFill(Color.BLUE);
                lblInstructorName.setText(removeInstructorBO.getInstructor(removeInstructorBO.getId(instructor.getFirstName())));
            }else {
                lblInstructorName.setTextFill(Color.RED);
                lblInstructorName.setText("License Number Does Not Exists!");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
