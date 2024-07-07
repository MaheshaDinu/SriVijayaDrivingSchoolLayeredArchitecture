package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public ArrayList<Payment> getAll() throws SQLException ;
    public  String getName(String studentId) throws SQLException;
    public String getPaymentId(String amount,String date,String studentId) throws SQLException;
}
