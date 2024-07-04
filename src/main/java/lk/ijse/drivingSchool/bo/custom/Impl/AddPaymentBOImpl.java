package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.AddPaymentBO;
import lk.ijse.drivingSchool.dao.custom.PaymentDAO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.UserDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.UserDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;
import lk.ijse.drivingSchool.entity.Payment;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;

public class AddPaymentBOImpl implements AddPaymentBO {
    UserDAO userDAO = new UserDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    VehicleClassDAO vehicleClassDAO = new VehicleClassDAOImpl();
    public String getId(String contactNo) throws SQLException{
        return userDAO.getId(contactNo);
    }
    public Student get(String nic) throws SQLException{
        return studentDAO.get(nic);
    }
    public  boolean save(Payment payment) throws SQLException{
        return paymentDAO.save(payment);
    }
    public  String getVehicleClass(String vehicleClassId) throws SQLException{
        return vehicleClassDAO.getVehicleClass(vehicleClassId);
    }
    public  String getStudentId(String NIC) throws SQLException{
        return studentDAO.getId(NIC);
    }
}
