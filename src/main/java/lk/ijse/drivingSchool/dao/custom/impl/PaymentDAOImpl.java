package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.PaymentDAO;
import lk.ijse.drivingSchool.entity.Payment;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {
    public  ResultSet getTotalIncome() throws SQLException {

        /*String sql ="select sum(amount) as total_income from payment";


            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement ptsm = connection.prepareStatement(sql);*/
        return SQLUtil.execute("select sum(amount) as total_income from payment");


    }

    public  boolean savePayment(Payment payment) throws SQLException {
        /*String sql = "INSERT INTO payment (amount, date, student_id, vehicle_class_id, user_id) VALUES (?,?,?,?,?)";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,payment.getAmount());
        pstm.setObject(2,payment.getDate());
        pstm.setObject(3,payment.getStudentId());
        pstm.setObject(4,payment.getVehicleClassId());
        pstm.setObject(5,payment.getUserId());*/

        return SQLUtil.execute("INSERT INTO payment (amount, date, student_id, vehicle_class_id, user_id) VALUES (?,?,?,?,?)",payment.getAmount(),payment.getDate(),payment.getStudentId(),payment.getVehicleClassId(),payment.getUserId());
    }

    public  ResultSet getAllPayments() throws SQLException {
        /*String sql ="select * from payment";

            Connection connection =DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);*/
        return SQLUtil.execute("select * from payment");

    }

    public  boolean removePayment(String date, String studentId, String amount) throws SQLException {
        /*String sql ="delete from payment where date =? and student_id =? and amount =?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,date);
            pstm.setObject(2,studentId);
            pstm.setObject(3,amount);*/
            return SQLUtil.execute("delete from payment where date =? and student_id =? and amount =?",date,studentId,amount);

    }

    public  String getAllStudentPayment(String studentId) throws SQLException {

        String paid = "0";
        /*String sql ="select sum(amount) as paid from payment where student_id = ?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,studentId);*/
            ResultSet resultSet = SQLUtil.execute("select sum(amount) as paid from payment where student_id = ?",studentId);
            if (resultSet.next()){
                paid = resultSet.getString("paid");
            }
            return paid;

    }
}
