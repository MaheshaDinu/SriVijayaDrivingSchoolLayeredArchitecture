package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.bo.custom.StudentProfileBO;
import lk.ijse.drivingSchool.dao.DAOFactory;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.AttendanceDAO;
import lk.ijse.drivingSchool.dao.custom.PaymentDAO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentProfileBOImpl implements StudentProfileBO {
    VehicleClassDAO vehicleClassDAO = (VehicleClassDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE_CLASS);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    public  String getVehicleClass(String vehicleClassId) throws SQLException {

        return vehicleClassDAO.getVehicleClass(vehicleClassId);

    }
    public  String getStudentId(String NIC) throws SQLException {

        return studentDAO.getId(NIC);

    }
    public  String getDaysAttended(String studentId) throws SQLException {

        return attendanceDAO.getDaysAttended(studentId);

    }
    public  String getFee(String vehicleClassId) throws SQLException {


        return vehicleClassDAO.getFee(vehicleClassId);

    }
    public  String getAllStudentPayment(String studentId) throws SQLException {


        return paymentDAO.getAllStudentPayment(studentId);

    }
}
