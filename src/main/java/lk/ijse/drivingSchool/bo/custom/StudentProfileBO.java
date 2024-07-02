package lk.ijse.drivingSchool.bo.custom;

import java.sql.SQLException;

public interface StudentProfileBO {
    public  String getVehicleClass(String vehicleClassId) throws SQLException ;
    public  String getStudentId(String NIC) throws SQLException;
    public  String getDaysAttended(String studentId) throws SQLException ;
    public  String getFee(String vehicleClassId) throws SQLException ;
    public  String getAllStudentPayment(String studentId) throws SQLException ;
}
