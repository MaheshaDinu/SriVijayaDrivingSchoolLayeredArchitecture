package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Exam;

import java.sql.SQLException;

public interface AddPracticalExamBO extends SuperBO {
    public  boolean savePracticalExam(Exam exam) throws SQLException;
}
