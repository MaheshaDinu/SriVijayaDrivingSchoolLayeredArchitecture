package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.entity.Exam;

import java.sql.SQLException;

public interface AddWrittenExamBO {
    public  boolean saveWrittenExam(Exam exam) throws SQLException;
}
