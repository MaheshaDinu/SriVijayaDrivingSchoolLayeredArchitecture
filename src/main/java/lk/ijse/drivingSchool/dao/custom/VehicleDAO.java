package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.dao.CrudDAO;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    public List<String> getVehicleList() throws SQLException ;



    public  String getVehicle(String vehicleId) throws SQLException ;


}
