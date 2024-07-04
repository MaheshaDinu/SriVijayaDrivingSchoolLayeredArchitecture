package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.AddPracticalExamBO;
import lk.ijse.drivingSchool.dao.custom.ExamDAO;
import lk.ijse.drivingSchool.dao.custom.impl.ExamDAOImpl;
import lk.ijse.drivingSchool.entity.Exam;

import java.sql.SQLException;

public class AddPracticalExamBOImpl implements AddPracticalExamBO {
    ExamDAO examDAO = new ExamDAOImpl();
    public  boolean savePracticalExam(Exam exam) throws SQLException{
        return examDAO.savePracticalExam(exam);
    }
}
