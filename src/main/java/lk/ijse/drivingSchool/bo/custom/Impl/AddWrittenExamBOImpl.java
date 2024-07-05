package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.AddWrittenExamBO;
import lk.ijse.drivingSchool.dao.DAOFactory;
import lk.ijse.drivingSchool.dao.custom.ExamDAO;
import lk.ijse.drivingSchool.dao.custom.impl.ExamDAOImpl;
import lk.ijse.drivingSchool.entity.Exam;

import java.sql.SQLException;

public class AddWrittenExamBOImpl implements AddWrittenExamBO {
    ExamDAO examDAO = (ExamDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EXAM);
    public  boolean saveWrittenExam(Exam exam) throws SQLException{
        return examDAO.saveWrittenExam(exam);
    }
}
