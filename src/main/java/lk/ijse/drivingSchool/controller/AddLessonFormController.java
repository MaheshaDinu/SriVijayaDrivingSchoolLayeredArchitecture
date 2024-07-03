package lk.ijse.drivingSchool.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import lk.ijse.drivingSchool.bo.custom.AddLessonBO;
import lk.ijse.drivingSchool.bo.custom.Impl.AddLessonBOImpl;
import lk.ijse.drivingSchool.entity.Lesson;


import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class AddLessonFormController {
    @FXML
    private JFXComboBox<String> cmbInstructor;

    @FXML
    private JFXComboBox<String> cmbTime;

    @FXML
    private JFXComboBox<String> cmbVehicle;

    @FXML
    private Label lblDateErrorMsg;

    @FXML
    private TextField txtDate;
    private String vehicleId;
    private String instructorId;
    private String time;
    AddLessonBO addLessonBO = new AddLessonBOImpl();
    @FXML
    public void initialize(){
        getInstructors();
        getVehicles();
        getTimes();

    }

    private void getTimes() {
        cmbTime.setItems(FXCollections.observableArrayList("10.00 AM","03.00 PM"));
    }

    private void getVehicles() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> vehicleList = addLessonBO.getVehicleList();
            for(String vehicle : vehicleList){
                obList.add(vehicle);
            }
            cmbVehicle.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getInstructors() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> instructorList = addLessonBO.getInstructorsList();
            for(String instructor : instructorList){
                obList.add(instructor);
            }
            cmbInstructor.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddLessonOnAction(ActionEvent event) {
        String date = txtDate.getText();
        Pattern datePattern = Pattern.compile("^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[01])$");
        if (isValidInput(datePattern)) {

            Lesson lesson = new Lesson(date, time, instructorId, vehicleId);
            try {
                boolean isSaved = addLessonBO.save(lesson);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Lesson Added!").show();
                    txtDate.setText("");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
                txtDate.setText("");
            }
        }

    }

    private boolean isValidInput(Pattern datePattern) {
        boolean isValid = true;
        if (!datePattern.matcher(txtDate.getText()).matches()){
            lblDateErrorMsg.setText("Invalid Date Format");
            addError(txtDate);
            isValid = false;
        }else {
            removeError(txtDate);
        }
        return isValid;
    }

    @FXML
    void cmbInstructorOnAction(ActionEvent event) {
        try {
            String instructor = cmbInstructor.getValue();
            this.instructorId = addLessonBO.getInstructorId(instructor);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbTimeOnAction(ActionEvent event) {
        time = cmbTime.getValue();

    }

    @FXML
    void cmbVehicleOnAction(ActionEvent event) {

        String vehicle = cmbVehicle.getValue();
        try {
            vehicleId = addLessonBO.getVehicleId(vehicle);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void txtDateReleaseOnAction(KeyEvent event) {
        Pattern datePattern = Pattern.compile("^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[01])$");
        if (!datePattern.matcher(txtDate.getText()).matches()){
            addError(txtDate);
        }else {
            removeError(txtDate);
        }

    }

    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }
}
