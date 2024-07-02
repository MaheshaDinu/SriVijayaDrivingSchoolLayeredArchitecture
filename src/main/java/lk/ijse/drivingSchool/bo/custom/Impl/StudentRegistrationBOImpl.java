package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.bo.custom.StudentRegistrationBO;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.PaymentDAO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.UserDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.UserDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;
import lk.ijse.drivingSchool.dto.StudentDTO;
import lk.ijse.drivingSchool.entity.Payment;
import lk.ijse.drivingSchool.entity.Student;
import lk.ijse.drivingSchool.entity.User;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationBOImpl implements StudentRegistrationBO {
    UserDAO userDAO = new UserDAOImpl();
    VehicleClassDAO vehicleClassDAO = new VehicleClassDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    public  String getUserId(User user) throws SQLException {


        return userDAO.getUserId(user);
    }
    public  boolean studentRegistration(StudentDTO studentDTO, String initialPayment, String uId, String vcid) throws SQLException {
        Student student = new Student(studentDTO.getNIC(),studentDTO.getFirstName(),studentDTO.getLastName(),studentDTO.getHeight(),studentDTO.getWeight(),studentDTO.getDateOfBirth(),studentDTO.getBloodGroup(),studentDTO.getContactNo(),studentDTO.getAddress(),studentDTO.getUserId(),studentDTO.getVehicleClassId());
        Connection connection = DbConnnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
        boolean isStudentSaved = false;

            isStudentSaved = studentDAO.save(student);

        if (isStudentSaved){
            String amount = initialPayment;
            String date = String.valueOf(LocalDate.now());
            String studentId= studentDAO.getNextId();
            String vehicleClassId= vcid;

            String userId = uId;

            Payment payment = new Payment(amount,date,studentId,vehicleClassId,userId);
            boolean isPaymentSaved = paymentDAO.save(payment);
            if (isPaymentSaved){
                connection.commit();
                return true;
            }
        }
        connection.rollback();
        return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }
    public  String getFee(String vehicleClassId) throws SQLException {

            return vehicleClassDAO.getFee(vehicleClassId);

    }
    public  String getVehicleClass(String vehicleClassId) throws SQLException {

        return vehicleClassDAO.getVehicleClass(vehicleClassId);

    }
    public List<String> getAllVehicleClass() throws SQLException {

        return vehicleClassDAO.getAllVehicleClass();
    }
    public  String getId(String vehicleClass) throws SQLException {

        return vehicleClassDAO.getId(vehicleClass);
    }
}
