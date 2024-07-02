package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.dao.CrudDAO;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AttendanceDAO extends CrudDAO<Attendance> {


    public  String getDaysAttended(String studentId) throws SQLException ;
}
