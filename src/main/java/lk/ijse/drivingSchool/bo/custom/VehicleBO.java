package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleBO {
    public ArrayList<Vehicle> getAll() throws SQLException;
    public  String getId(String vehicle) throws SQLException;
}
