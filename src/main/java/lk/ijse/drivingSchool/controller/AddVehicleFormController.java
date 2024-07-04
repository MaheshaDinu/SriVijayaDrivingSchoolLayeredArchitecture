package lk.ijse.drivingSchool.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import lk.ijse.drivingSchool.bo.custom.AddVehicleBO;
import lk.ijse.drivingSchool.bo.custom.Impl.AddVehicleBOImpl;
import lk.ijse.drivingSchool.entity.Vehicle;


import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddVehicleFormController {
    @FXML
    private JFXComboBox<String> cmbFuelType;

    @FXML
    private JFXComboBox<String> cmbTransmissionType;

    @FXML
    private TextField txtColour;

    @FXML
    private TextField txtLicensePlate;

    @FXML
    private TextField txtManufacturer;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtYear;
    private String transmissionType;
    private String fuelType;
    private String availability;
    AddVehicleBO addVehicleBO = new AddVehicleBOImpl();
    @FXML
    public void initialize(){
        getTransmissionTypes();
        getFuelTypes();
        restrictNumeric(txtYear);
    }

    private void getFuelTypes() {
        cmbFuelType.setItems(FXCollections.observableArrayList("Petrol","Diesel"));
    }

    private void getTransmissionTypes() {
        cmbTransmissionType.setItems(FXCollections.observableArrayList("Manual","Auto"));
    }

    @FXML
    void btnAddVehicleOnAction(ActionEvent event) {
        String licensePlate = txtLicensePlate.getText();
        String type = txtType.getText();
        String manufacturer = txtManufacturer.getText();
        String model = txtModel.getText();
        String year = txtYear.getText();
        String colour = txtColour.getText();
        availability = "Available";

        Vehicle vehicle = new Vehicle(licensePlate,type,manufacturer,model,year,colour,transmissionType,fuelType,availability);

        try {
            boolean isSaved = addVehicleBO.save(vehicle);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Added!").show();
                txtLicensePlate.setText("");
                txtType.setText("");
                txtManufacturer.setText("");
                txtModel.setText("");
                txtYear.setText("");
                txtColour.setText("");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
        }

    }

    @FXML
    void cmbFuelTypeOnAction(ActionEvent event) {
        fuelType = cmbFuelType.getValue();

    }

    @FXML
    void cmbTransmissionTypeOnAction(ActionEvent event) {
        transmissionType = cmbTransmissionType.getValue();

    }

    @FXML
    void txtYearReleaseOnAction(KeyEvent event) {
        restrictNumeric(txtYear);
        Pattern yearPattern = Pattern.compile("^(19|20)\\d{2}$");
        if (!yearPattern.matcher(txtYear.getText()).matches()){
            addError(txtYear);
        }else {
            removeError(txtYear);
        }

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
