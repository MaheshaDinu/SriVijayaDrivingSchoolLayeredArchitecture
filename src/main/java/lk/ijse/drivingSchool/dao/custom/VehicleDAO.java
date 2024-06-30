package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO {
    public List<String> getVehicleList() throws SQLException ;

    public  String getId(String vehicle) throws SQLException ;

    public  boolean saveVehicle(Vehicle vehicle) throws SQLException ;

    public  String getVehicle(String vehicleId) throws SQLException ;

    public  ArrayList<Vehicle> getAllVehicles() throws SQLException ;

    public  boolean removeVehicle(String license) throws SQLException ;

    public  Vehicle getVehicleFromLicense(String license) throws SQLException ;
}
