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
import java.util.ArrayList;

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

    public  ArrayList<Exam> getFutureWrittenExams() throws SQLException {
        ArrayList<Exam>exams = new ArrayList<>();
        Exam exam = null;
        String now = String.valueOf(LocalDate.now());
        /*String sql ="select * from writtenExam where date >= ?";
            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,now);*/
            ResultSet resultSet = SQLUtil.execute("select * from writtenExam where date >= ?",now);
        if (resultSet.next()){
            exam = new Exam(resultSet.getString("date"),resultSet.getString("time"));
            exams.add(exam);
        }
            return exams;

    }

    public  ArrayList<Exam> getFuturePracticalExams() throws SQLException {
        ArrayList<Exam>exams = new ArrayList<>();
        Exam exam = null;
        String now = String.valueOf(LocalDate.now());
        /*String sql ="select * from practicalExam where date >= ?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,now);*/
            ResultSet resultSet = SQLUtil.execute("select * from practicalExam where date >= ?",now);
        if (resultSet.next()){
            exam = new Exam(resultSet.getString("date"),resultSet.getString("time"));
            exams.add(exam);
        }
            return exams;

    }

    @Override
    public ArrayList<Exam> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Exam entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String nic) throws SQLException {
        return false;
    }

    @Override
    public Exam get(String nic) throws SQLException {
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        return null;
    }

    @Override
    public String getName(String id) throws SQLException {
        return null;
    }

    @Override
    public String getId(String NIC) throws SQLException {
        return null;
    }
}
