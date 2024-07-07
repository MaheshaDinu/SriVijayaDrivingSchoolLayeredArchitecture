package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.Impl.RemoveVehicleBOImpl;
import lk.ijse.drivingSchool.bo.custom.RemoveVehicleBO;
import lk.ijse.drivingSchool.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RemoveVehicleFormController {
    @FXML
    private Label lblManufacturer;

    @FXML
    private Label lblVehicleType;

    @FXML
    private Label lblYear;

    @FXML
    private TextField txtLicense;
    RemoveVehicleBO removeVehicleBO = (RemoveVehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REMOVE_VEHICLE);

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        String license = txtLicense.getText();
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Do you really want to remove this vehicle?", yes, no).showAndWait();
        try {
            if(type.orElse(no) == yes) {
                boolean isRemoved = removeVehicleBO.delete(license);
                if (isRemoved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Removed!").show();
                    txtLicense.setText("");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Vehicle Not Removed!").show();
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }




    }

    @FXML
    void txtLicenseOnAction(KeyEvent event) {
        String license = txtLicense.getText();
        try {
        Vehicle vehicle = removeVehicleBO.get(license);

            if (vehicle!=null) {
                lblVehicleType.setTextFill(Color.BLUE);
                lblVehicleType.setText(vehicle.getType());
                lblManufacturer.setTextFill(Color.BLUE);
                lblYear.setTextFill(Color.BLUE);
                lblManufacturer.setText(vehicle.getManufacturer());
                lblYear.setText(vehicle.getYear());
            } else {
                lblVehicleType.setTextFill(Color.RED);
                lblVehicleType.setText("Vehicle Does Not Exist!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
