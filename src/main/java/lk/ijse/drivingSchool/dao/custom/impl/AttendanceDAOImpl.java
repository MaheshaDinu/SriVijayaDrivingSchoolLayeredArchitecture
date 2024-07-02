package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.AttendanceDAO;
import lk.ijse.drivingSchool.entity.Attendance;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public ArrayList<Attendance> getAll() throws SQLException {
        return null;
    }

    public  boolean save(Attendance attendance) throws SQLException {
        /*String sql = "INSERT INTO attendanceRecord (lesson_id, student_id, date, start_time, end_time, attendance_status) VALUES (?,?,?,?,?,?)";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,attendance.getLessonId());
        pstm.setObject(2,attendance.getStudentId());
        pstm.setObject(3,attendance.getDate());
        pstm.setObject(4,attendance.getStartTime());
        pstm.setObject(5,attendance.getEndTime());
        pstm.setObject(6,attendance.getAttendanceStatus());*/
        return SQLUtil.execute("INSERT INTO attendanceRecord (lesson_id, student_id, date, start_time, end_time, attendance_status) VALUES (?,?,?,?,?,?)",attendance.getLessonId(),attendance.getStudentId(),attendance.getDate(),attendance.getStartTime(),attendance.getEndTime(),attendance.getAttendanceStatus());
    }

    @Override
    public boolean delete(String nic) throws SQLException {
        return false;
    }

    @Override
    public Attendance get(String nic) throws SQLException {
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

    public  String getDaysAttended(String studentId) throws SQLException {
        String daysCount = "0";
       /* String sql = "select count(student_id) as days_attended from attendanceRecord where student_id =?";


            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,studentId);*/
            ResultSet resultSet = SQLUtil.execute("select count(student_id) as days_attended from attendanceRecord where student_id =?",studentId);
            if (resultSet.next()){
                daysCount = resultSet.getString("days_attended");
            }
            return daysCount;

    }
}
