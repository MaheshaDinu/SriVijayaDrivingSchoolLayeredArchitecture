package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Vehicle;

import java.sql.SQLException;

public interface AddVehicleBO extends SuperBO {
    public  boolean save(Vehicle vehicle) throws SQLException;
}
