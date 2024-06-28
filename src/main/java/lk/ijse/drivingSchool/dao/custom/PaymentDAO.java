package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PaymentDAO {
    public  ResultSet getTotalIncome() throws SQLException;

    public  boolean savePayment(Payment payment) throws SQLException ;

    public  ResultSet getAllPayments() throws SQLException;

    public  boolean removePayment(String date, String studentId, String amount) throws SQLException;

    public  String getAllStudentPayment(String studentId) throws SQLException;
}
