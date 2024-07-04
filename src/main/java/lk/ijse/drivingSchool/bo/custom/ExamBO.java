package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.entity.Exam;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamBO {
    public ArrayList<Exam> getFutureWrittenExams() throws SQLException ;
    public  ArrayList<Exam> getFuturePracticalExams() throws SQLException;
}
