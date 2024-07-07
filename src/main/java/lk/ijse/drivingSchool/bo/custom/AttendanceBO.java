package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Attendance;
import lk.ijse.drivingSchool.entity.Lesson;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;

public interface AttendanceBO extends SuperBO {
    public Lesson getLesson(String date, String time) throws SQLException ;
    public Student getStudent(String nic) throws SQLException;
    public  boolean save(Attendance attendance) throws SQLException;
    public  String getDaysAttended(String studentId) throws SQLException;
    public String getLessonId(String date,String time) throws SQLException;
    public  String getStudentId(String NIC) throws SQLException;
}
