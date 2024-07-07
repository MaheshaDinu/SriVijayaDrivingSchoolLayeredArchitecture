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


import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.Impl.StudentEditBOImpl;
import lk.ijse.drivingSchool.bo.custom.StudentEditBO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;



public class StudentEditFormController {
    @FXML
    private JFXComboBox<String> cmbVehicleClass;

    @FXML
    private Label lblContactNoErrorMsg;

    @FXML
    private Label lblDateErrorMsg;

    @FXML
    private Label lblNICErrorMsg;


    @FXML
    private Label lblVehicleClass;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtBloodGroup;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNICInput;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtWeight;
    StudentEditBO studentEditBO = (StudentEditBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT_EDIT);


    public void initialize(){
        getVehicleClass();
        restrictNumeric(txtWeight);
        restrictNumeric(txtHeight);
    }

    public void getVehicleClass(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> vehicleClassList = studentEditBO.getAllVehicleClass();
            for (String vehicleClass : vehicleClassList){
                obList.add(vehicleClass);
            }
            cmbVehicleClass.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            String inputNIC = txtNICInput.getText();
            String NIC = txtNIC.getText();
            String firstname = txtFirstName.getText();
            String lastname = txtLastName.getText();
            String height = txtHeight.getText();
            String weight = txtWeight.getText();
            String dateOfBirth = txtDOB.getText();
            String bloodGroup = txtBloodGroup.getText();
            String contactNo = txtContactNo.getText();
            String address = txtAddress.getText();
            String vehicleClassId = studentEditBO.getId(lblVehicleClass.getText());

            Pattern phonePattern = Pattern.compile("^\\+94\\d{9}$");
            Pattern datePattern = Pattern.compile("^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[01])$");
            Pattern NICPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");

            if (isValidInput(NICPattern, phonePattern, datePattern)) {


                boolean isUpdated = studentEditBO.updateStudent(NIC, firstname, lastname, height, weight, dateOfBirth, bloodGroup, contactNo, address, vehicleClassId, inputNIC);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Changes Saved!").show();
                    txtNIC.setText("");
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtHeight.setText("");
                    txtWeight.setText("");
                    txtDOB.setText("");
                    txtBloodGroup.setText("");
                    txtContactNo.setText("+94");
                    txtAddress.setText("");
                    lblVehicleClass.setText("");
                    txtNICInput.setText("");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Changes Not Saved!").show();
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private boolean isValidInput(Pattern nicPattern, Pattern phonePattern, Pattern datePattern) {
        boolean isValid = true;
        if (!nicPattern.matcher(txtNIC.getText()).matches()){
            lblNICErrorMsg.setText("Invalid NIC format");
            addError(txtNIC);
            isValid =false;
        }else {
            removeError(txtNIC);
        }
        if (!phonePattern.matcher(txtContactNo.getText()).matches()){
            lblContactNoErrorMsg.setText("Invalid Contact Number");
            addError(txtContactNo);
            isValid =false;
        }else {
            removeError(txtContactNo);
        }
        if (!datePattern.matcher(txtDOB.getText()).matches()){
            lblDateErrorMsg.setText("Invalid Date Format");
            addError(txtDOB);
            isValid = false;
        }else {
            removeError(txtDOB);
        }
        return isValid;
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String NIC = txtNICInput.getText();
        try {
            Student student = studentEditBO.get(NIC);
            if (student !=null){
                txtNIC.setText(student.getNIC());
                txtFirstName.setText(student.getFirstName());
                txtLastName.setText(student.getLastName());
                txtHeight.setText(student.getHeight());
                txtWeight.setText(student.getWeight());
                txtDOB.setText(student.getDateOfBirth());
                txtBloodGroup.setText(student.getBloodGroup());
                txtContactNo.setText(student.getContactNo());
                txtAddress.setText(student.getAddress());
                lblVehicleClass.setText(studentEditBO.getVehicleClass(student.getVehicleClassId()));
            }else {
                new Alert(Alert.AlertType.ERROR, "NIC Does Not Exist!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbVehicleClassOnAction(ActionEvent event) {
        lblVehicleClass.setText(cmbVehicleClass.getValue());

    }

    @FXML
    void txtContactNoKeyReleasedOnAction(KeyEvent event) {
        Pattern phonePattern = Pattern.compile("^\\+94\\d{9}$");
        if (!phonePattern.matcher(txtContactNo.getText()).matches()){
            addError(txtContactNo);
        }else {
            removeError(txtContactNo);
        }
    }

    @FXML
    void txtDOBReleasedOnAction(KeyEvent event) {
        Pattern datePattern = Pattern.compile("^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[01])$");
        if (!datePattern.matcher(txtDOB.getText()).matches()){
            addError(txtDOB);
        }else {
            removeError(txtDOB);
        }
    }

    @FXML
    void txtHeightKeyOnReleased(KeyEvent event) {
        restrictNumeric(txtHeight);
    }

    @FXML
    void txtNICReleasedOnAction(KeyEvent event) {
        Pattern NICPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        if (!NICPattern.matcher(txtNIC.getText()).matches()){
            addError(txtNIC);
        }else {
            removeError(txtNIC);
        }
    }

    @FXML
    void txtWeightReleasedOnAction(KeyEvent event) {
        restrictNumeric(txtWeight);
    }

    private void restrictNumeric(TextField textField){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                textField.setText("");
            } else {
                if (!newValue.matches("\\d*")) {
                    textField.setText(oldValue);
                }
            }
        });
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }


    }
