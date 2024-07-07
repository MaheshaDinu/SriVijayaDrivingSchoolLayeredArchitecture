package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.AddInstructorBO;
import lk.ijse.drivingSchool.bo.custom.Impl.AddInstructorBOImpl;
import lk.ijse.drivingSchool.entity.Instructor;


import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddInstructorFormController {

    @FXML
    private Label lblContactNoErrorMsg;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtLicenseNumber;
    AddInstructorBO addInstructorBO = (AddInstructorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_INSTRUCTOR);

    @FXML
    void btnAddInstructorOnAction(ActionEvent event) {
        String firstName =txtFirstName.getText();
        String lastName = txtLastName.getText();
        String licenseNumber = txtLicenseNumber.getText();
        String contactNo = txtContactNumber.getText();

        Pattern phonePattern = Pattern.compile("^\\+94\\d{9}$");
        if (isValidInput(phonePattern)) {

            Instructor instructor = new Instructor(firstName, lastName, licenseNumber, contactNo);

            try {
                boolean isSaved = addInstructorBO.save(instructor);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Instructor Registered!").show();
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtLicenseNumber.setText("");
                    txtContactNumber.setText("");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
            }
        }

    }

    private boolean isValidInput(Pattern phonePattern) {
        boolean isValid = true;
        if (!phonePattern.matcher(txtContactNumber.getText()).matches()){
            lblContactNoErrorMsg.setText("Invalid Contact Number");
            addError(txtContactNumber);
            isValid =false;
        }else {
            removeError(txtContactNumber);
        }
        return isValid;
    }

    @FXML
    void txtContactNoReleasedOnAction(KeyEvent event) {
        Pattern phonePattern = Pattern.compile("^\\+94\\d{9}$");
        if (!phonePattern.matcher(txtContactNumber.getText()).matches()){
            addError(txtContactNumber);
        }else {
            removeError(txtContactNumber);
        }

    }

    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }

}
