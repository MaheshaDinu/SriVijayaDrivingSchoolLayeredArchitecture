package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Exam;

import java.sql.SQLException;

public interface AddWrittenExamBO extends SuperBO {
    public  boolean saveWrittenExam(Exam exam) throws SQLException;
}
