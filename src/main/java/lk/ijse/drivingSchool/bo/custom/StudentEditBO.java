package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentEditBO extends SuperBO {
    public  boolean updateStudent(String nic, String firstname, String lastname, String height, String weight, String dateOfBirth, String bloodGroup, String contactNo, String address, String vehicleClassId, String inputNIC) throws SQLException ;
    public Student get(String nic) throws SQLException;
    public List<String> getAllVehicleClass() throws SQLException;
    public  String getId(String vehicleClass) throws SQLException;
    public  String getVehicleClass(String vehicleClassId) throws SQLException;
}
