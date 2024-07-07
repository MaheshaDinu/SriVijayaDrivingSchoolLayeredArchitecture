package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Exam;
import lk.ijse.drivingSchool.entity.Instructor;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashboardBO extends SuperBO {
    public Exam getNextPracticalExam() throws SQLException ;
    public  Exam getNextWrittenExam() throws SQLException;
    public  String getTotalIncome() throws SQLException;
    public ArrayList<Instructor> getAllInstructors() throws SQLException;
    public  ArrayList<Student> getAllStudents() throws SQLException;
}
