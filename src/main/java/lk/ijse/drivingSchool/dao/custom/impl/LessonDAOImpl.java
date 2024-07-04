package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.LessonDAO;
import lk.ijse.drivingSchool.entity.Lesson;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LessonDAOImpl implements LessonDAO {

    @Override
    public ArrayList<Lesson> getAll() throws SQLException {
        return null;
    }

    public  boolean save(Lesson lesson) throws SQLException {
        /*String sql ="INSERT INTO  lesson (date, time, instructor_id, vehicle_id) VALUES(?,?,?,?)";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm =connection.prepareStatement(sql);
        pstm.setObject(1,lesson.getDate());
        pstm.setObject(2,lesson.getTime());
        pstm.setObject(3,lesson.getInstructorId());
        pstm.setObject(4,lesson.getVehicleId());*/

        return SQLUtil.execute("INSERT INTO  lesson (date, time, instructor_id, vehicle_id) VALUES(?,?,?,?)",lesson.getDate(),lesson.getTime(),lesson.getInstructorId(),lesson.getVehicleId());
    }

    @Override
    public boolean delete(String nic) throws SQLException {
        return false;
    }

    @Override
    public Lesson get(String nic) throws SQLException {
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

    public  Lesson getFutureLessons() throws SQLException {
        LocalDate now =LocalDate.now();
        Lesson lesson = null;
        /*String sql = "select * from lesson where date>= ? order by date asc";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,String.valueOf(now));*/
            ResultSet resultSet = SQLUtil.execute("select * from lesson where date>= ? order by date asc",String.valueOf(now));
            if (resultSet.next()){
                lesson = new Lesson(resultSet.getString("date"),resultSet.getString("time"),resultSet.getString("instructor_id"),resultSet.getString("vehicle_id"));
            };
            return lesson;


    }

    public  Lesson getLesson(String date, String time) throws SQLException {
        Lesson lesson = null;
        /*String sql = "select * from lesson where date =? and time = ?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,date);
            pstm.setObject(2,time);*/

            ResultSet resultSet = SQLUtil.execute("select * from lesson where date =? and time = ?",date,time);
            if (resultSet.next()) {
                 lesson = new Lesson(resultSet.getString("date"), resultSet.getString("time"), resultSet.getString("instructor_id"), resultSet.getString("vehicle_id"));
            }
            return lesson;

    }

    public  boolean delete(String date, String time) throws SQLException {
        /*String sql = "delete from lesson where date = ? and time = ?";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,date);
        pstm.setObject(2,time);*/
        return SQLUtil.execute("delete from lesson where date = ? and time = ?",date,time);
    }
    public String getLessonId(String date,String time) throws SQLException{
        String id = null;
        ResultSet resultSet = SQLUtil.execute("select * from lesson where date =? and time = ?",date,time);
        if (resultSet.next()){
            id = resultSet.getString("lesson_id");
        }
        return id;
    }
}
