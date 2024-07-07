package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.Impl.RegistrationBOImpl;
import lk.ijse.drivingSchool.bo.custom.RegistrationBO;
import lk.ijse.drivingSchool.entity.User;


import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;


public class RegistrationFormController {
    @FXML
    private Label lblContactNoErrorMsg;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;


    @FXML
    private TextField txtUserName;
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);

    @FXML
    void btnCreateOnAction(ActionEvent event) {
        String userName = txtUserName.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String contactNo = txtContactNo.getText();
        String address = txtAddress.getText();
        String password = txtPassword.getText();

        Pattern phonePattern = Pattern.compile("^\\+94\\d{9}$");

        if (!userName.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !contactNo.isEmpty() && !address.isEmpty() && !password.isEmpty() ) {
            User user = new User(userName, firstName, lastName, contactNo, address, password);
            if (!phonePattern.matcher(contactNo).matches()){
                lblContactNoErrorMsg.setText("Invalid phone number! Please enter a valid phone number");
                addError(txtContactNo);
                return;
            }


            try {
                boolean isSaved = registrationBO.save(user);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Account Created!").show();
                    txtUserName.setText("");
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtContactNo.setText("");
                    txtAddress.setText("");
                    txtPassword.setText("");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Account Not Created!").show();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
            }



        }else  {
            new Alert(Alert.AlertType.ERROR, "Please Fill All The Necessary Information!").show();
        }

    }
    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            BorderPane rootNode = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));

            Scene scene = new Scene(rootNode);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Log in");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.getStackTrace();
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtContactNoReleasedOnAction(KeyEvent event) {
        Pattern phonePattern = Pattern.compile("^\\+94\\d{9}$");
        if (!phonePattern.matcher(txtContactNo.getText()).matches()){
            addError(txtContactNo);
        }else {
            removeError(txtContactNo);
        }
    }

    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }


}
