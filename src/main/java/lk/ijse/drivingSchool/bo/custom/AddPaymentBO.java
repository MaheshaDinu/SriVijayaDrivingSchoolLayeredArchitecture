package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.entity.Payment;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;

public interface AddPaymentBO {
    public String getId(String contactNo) throws SQLException ;
    public Student get(String nic) throws SQLException;
    public  boolean save(Payment payment) throws SQLException;
    public  String getVehicleClass(String vehicleClassId) throws SQLException;
    public  String getStudentId(String NIC) throws SQLException;
}
