package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.PaymentBO;
import lk.ijse.drivingSchool.dao.custom.PaymentDAO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO =new PaymentDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    public ArrayList<Payment> getAll() throws SQLException{
        return paymentDAO.getAll();
    }
    public  String getName(String studentId) throws SQLException{
        return studentDAO.getName(studentId);
    }
    public String getPaymentId(String amount,String date,String studentId) throws SQLException{
        return paymentDAO.getPaymentId(amount,date,studentId);
    }
}
