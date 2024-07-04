package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.RemoveVehicleBO;
import lk.ijse.drivingSchool.dao.custom.VehicleDAO;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.drivingSchool.entity.Vehicle;

import java.sql.SQLException;

public class RemoveVehicleBOImpl implements RemoveVehicleBO {
    VehicleDAO vehicleDAO = new VehicleDAOImpl();
    public  boolean delete(String license) throws SQLException{
        return vehicleDAO.delete(license);
    }
    public Vehicle get(String license) throws SQLException{
        return vehicleDAO.get(license);
    }
}
