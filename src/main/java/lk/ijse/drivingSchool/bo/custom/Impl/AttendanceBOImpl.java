package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.AttendanceBO;
import lk.ijse.drivingSchool.dao.custom.AttendanceDAO;
import lk.ijse.drivingSchool.dao.custom.LessonDAO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.LessonDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.entity.Attendance;
import lk.ijse.drivingSchool.entity.Lesson;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;

public class AttendanceBOImpl implements AttendanceBO {
    LessonDAO lessonDAO = new LessonDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
    public Lesson getLesson(String date, String time) throws SQLException{
        return lessonDAO.getLesson(date,time);
    }
    public Student getStudent(String nic) throws SQLException{
        return studentDAO.get(nic);
    }
    public  boolean save(Attendance attendance) throws SQLException{
        return attendanceDAO.save(attendance);
    }
    public  String getDaysAttended(String studentId) throws SQLException{
        return attendanceDAO.getDaysAttended(studentId);
    }
    public String getLessonId(String date,String time) throws SQLException{
        return lessonDAO.getLessonId(date, time);
    }
    public  String getStudentId(String NIC) throws SQLException{
        return attendanceDAO.getId(NIC);
    }
}
