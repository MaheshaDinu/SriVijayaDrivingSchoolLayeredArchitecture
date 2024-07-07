package lk.ijse.drivingSchool.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.Impl.VehicleBOImpl;
import lk.ijse.drivingSchool.bo.custom.VehicleBO;
import lk.ijse.drivingSchool.entity.User;
import lk.ijse.drivingSchool.entity.Vehicle;
import lk.ijse.drivingSchool.view.tdm.VehicleTm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleFormController {
    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLicence;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<VehicleTm> tblVehicle;
    private ObservableList<VehicleTm> vehicleTmObservableList = FXCollections.observableArrayList();
    public BorderPane borderPane;
    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICLE);
    @FXML
    public void initialize(User user, BorderPane borderPane) {
        setValueFactory();
        loadVehicleTable();
        this.borderPane = borderPane;
    }

    private void loadVehicleTable() {
        try{
        ArrayList<Vehicle> vehicles = vehicleBO.getAll();

            for (Vehicle v:vehicles){
                String id = vehicleBO.getId(v.getType());
                String type = v.getType();
                String license = v.getLicensePlate();
                String availability = v.getAvailability();

                VehicleTm vehicleTm = new VehicleTm(id,type,license,availability);
                vehicleTmObservableList.add(vehicleTm);
            }
            tblVehicle.setItems(vehicleTmObservableList);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colLicence.setCellValueFactory(new PropertyValueFactory<>("license"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }

    @FXML
    void btnAddVehicleOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/addvehicle_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnVehicleRemoveOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/removevehicle_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
