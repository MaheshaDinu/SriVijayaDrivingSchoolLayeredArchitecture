package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.CrudDAO;
import lk.ijse.drivingSchool.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {
    public  String getTotalIncome() throws SQLException;



    public  boolean delete(String date, String studentId, String amount) throws SQLException;

    public  String getAllStudentPayment(String studentId) throws SQLException;
}
