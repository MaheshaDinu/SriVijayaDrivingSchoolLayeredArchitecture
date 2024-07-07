package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;

public interface StudentRemoveBO extends SuperBO {
    public  boolean delete(String nic) throws SQLException ;
    public Student get(String nic) throws SQLException;
    public  String getName(String studentId) throws SQLException;
    public  String getId(String NIC) throws SQLException;
    public  String getVehicleClass(String vehicleClassId) throws SQLException;
}
