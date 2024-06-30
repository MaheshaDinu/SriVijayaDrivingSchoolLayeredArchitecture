package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.ExamDAO;
import lk.ijse.drivingSchool.entity.Exam;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExamDAOImpl implements ExamDAO {
    public  Exam getNextPracticalExam() throws SQLException {
        Exam exam = null;
        LocalDate now = LocalDate.now();
        /*String sql = "select * from practicalExam where date > ? order by date limit 1";


            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,String.valueOf(now));*/
            ResultSet resultSet = SQLUtil.execute("select * from practicalExam where date > ? order by date limit 1",String.valueOf(now));
            if (resultSet.next()){
                exam = new Exam(resultSet.getString("date"),resultSet.getString("time"));
            }


        return exam;
    }

    public  Exam getNextWrittenExam() throws SQLException {
        Exam exam = null;
        LocalDate now = LocalDate.now();
        /*String sql = "select * from writtenExam where date > ? order by date limit 1";


            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,String.valueOf(now));*/
            ResultSet resultSet = SQLUtil.execute("select * from writtenExam where date > ? order by date limit 1",String.valueOf(now));
        if (resultSet.next()){
            exam = new Exam(resultSet.getString("date"),resultSet.getString("time"));
        }


        return exam;
    }

    public  boolean saveWrittenExam(Exam exam) throws SQLException {
        String sql = "INSERT INTO writtenExam (date, time) VALUES (?,?)";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,exam.getDate());
        pstm.setObject(2,exam.getTime());

        return pstm.executeUpdate()>0;

    }

    public  boolean savePracticalExam(Exam exam) throws SQLException {
        String sql = "INSERT INTO practicalExam (date, time) VALUES (?,?)";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,exam.getDate());
        pstm.setObject(2,exam.getTime());

        return pstm.executeUpdate()>0;
    }

    public  Exam getFutureWrittenExams() throws SQLException {
        Exam exam = null;
        String now = String.valueOf(LocalDate.now());
        /*String sql ="select * from writtenExam where date >= ?";
            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,now);*/
            ResultSet resultSet = SQLUtil.execute("select * from writtenExam where date >= ?",now);
        if (resultSet.next()){
            exam = new Exam(resultSet.getString("date"),resultSet.getString("time"));
        }
            return exam;

    }

    public  Exam getFuturePracticalExams() throws SQLException {
        Exam exam = null;
        String now = String.valueOf(LocalDate.now());
        /*String sql ="select * from practicalExam where date >= ?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,now);*/
            ResultSet resultSet = SQLUtil.execute("select * from practicalExam where date >= ?",now);
        if (resultSet.next()){
            exam = new Exam(resultSet.getString("date"),resultSet.getString("time"));
        }
            return exam;

    }
}
