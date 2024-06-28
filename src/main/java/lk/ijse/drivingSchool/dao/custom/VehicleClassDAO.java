package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.Db.DbConnnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleClassDAO {
    public  String getVehicleClass(String vehicleClassId) throws SQLException ;

    public List<String> getAllVehicleClass() throws SQLException ;

    public  String getId(String vehicleClass) throws SQLException;

    public  String getFee(String vehicleClassId) throws SQLException;
}
