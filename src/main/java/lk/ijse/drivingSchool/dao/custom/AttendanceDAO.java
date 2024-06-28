package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AttendanceDAO {
    public  boolean saveAttendance(Attendance attendance) throws SQLException ;

    public  String getDaysAttended(String studentId) throws SQLException ;
}
