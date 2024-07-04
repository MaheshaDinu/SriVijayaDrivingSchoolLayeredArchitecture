package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.RemovePaymentBO;
import lk.ijse.drivingSchool.dao.custom.PaymentDAO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;

import java.sql.SQLException;

public class RemovePaymentBOImpl implements RemovePaymentBO {
    StudentDAO studentDAO = new StudentDAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    public  String getId(String NIC) throws SQLException{
        return studentDAO.getId(NIC);
    }
    public  boolean delete(String date, String studentId, String amount) throws SQLException{
        return paymentDAO.delete(date,studentId,amount);
    }
}
