package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    public ArrayList<Student> getAll() throws SQLException ;
    public  String getId(String NIC) throws SQLException;
    public  String getVehicleClass(String vehicleClassId) throws SQLException;
}
