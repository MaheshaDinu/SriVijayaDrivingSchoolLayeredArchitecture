package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Vehicle;

import java.sql.SQLException;

public interface RemoveVehicleBO extends SuperBO {
    public  boolean delete(String license) throws SQLException ;
    public Vehicle get(String license) throws SQLException;
}
