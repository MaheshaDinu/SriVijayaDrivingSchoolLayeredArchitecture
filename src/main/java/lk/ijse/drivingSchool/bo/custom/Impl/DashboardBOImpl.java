package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.DashboardBO;
import lk.ijse.drivingSchool.dao.custom.ExamDAO;
import lk.ijse.drivingSchool.dao.custom.InstructorDAO;
import lk.ijse.drivingSchool.dao.custom.PaymentDAO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.impl.ExamDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.entity.Exam;
import lk.ijse.drivingSchool.entity.Instructor;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardBOImpl implements DashboardBO {
    ExamDAO examDAO = new ExamDAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    InstructorDAO instructorDAO = new InstructorDAOImpl();
    StudentDAO studentDAO =new StudentDAOImpl();
    public Exam getNextPracticalExam() throws SQLException{
        return examDAO.getNextPracticalExam();
    }
    public  Exam getNextWrittenExam() throws SQLException{
        return examDAO.getNextWrittenExam();
    }
    public  String getTotalIncome() throws SQLException{
        return paymentDAO.getTotalIncome();
    }
    public ArrayList<Instructor> getAllInstructors() throws SQLException{
        return instructorDAO.getAll();
    }
    public  ArrayList<Student> getAllStudents() throws SQLException{
        return studentDAO.getAll();
    }
}
