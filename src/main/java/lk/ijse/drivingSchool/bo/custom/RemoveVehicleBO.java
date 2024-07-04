package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.entity.Vehicle;

import java.sql.SQLException;

public interface RemoveVehicleBO {
    public  boolean delete(String license) throws SQLException ;
    public Vehicle get(String license) throws SQLException;
}
