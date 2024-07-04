package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.AddVehicleBO;
import lk.ijse.drivingSchool.dao.custom.VehicleDAO;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.drivingSchool.entity.Vehicle;

import java.sql.SQLException;

public class AddVehicleBOImpl implements AddVehicleBO {
    VehicleDAO vehicleDAO = new VehicleDAOImpl();
    public  boolean save(Vehicle vehicle) throws SQLException{
        return vehicleDAO.save(vehicle);
    }
}
