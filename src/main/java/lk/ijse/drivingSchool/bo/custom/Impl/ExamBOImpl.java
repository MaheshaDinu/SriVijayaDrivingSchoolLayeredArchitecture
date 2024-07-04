package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.ExamBO;
import lk.ijse.drivingSchool.dao.custom.ExamDAO;
import lk.ijse.drivingSchool.dao.custom.impl.ExamDAOImpl;
import lk.ijse.drivingSchool.entity.Exam;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExamBOImpl implements ExamBO {
    ExamDAO examDAO = new ExamDAOImpl();
    public ArrayList<Exam> getFutureWrittenExams() throws SQLException{
        return examDAO.getFutureWrittenExams();
    }
    public  ArrayList<Exam> getFuturePracticalExams() throws SQLException{
        return examDAO.getFuturePracticalExams();
    }
}
