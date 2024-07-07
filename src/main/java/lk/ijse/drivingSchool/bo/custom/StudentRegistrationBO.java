package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.dto.StudentDTO;
import lk.ijse.drivingSchool.entity.Payment;
import lk.ijse.drivingSchool.entity.Student;
import lk.ijse.drivingSchool.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface StudentRegistrationBO extends SuperBO {
    public  String getUserId(User user) throws SQLException ;
    public  boolean studentRegistration(StudentDTO studentDTO, String initialPayment, String uId, String vcid) throws SQLException ;
    public  String getFee(String vehicleClassId) throws SQLException;
    public  String getVehicleClass(String vehicleClassId) throws SQLException ;
    public List<String> getAllVehicleClass() throws SQLException ;
    public  String getId(String vehicleClass) throws SQLException;
}
