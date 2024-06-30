package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface ExamDAO {
    public Exam getNextPracticalExam() throws SQLException ;

    public  Exam getNextWrittenExam() throws SQLException ;

    public  boolean saveWrittenExam(Exam exam) throws SQLException ;

    public  boolean savePracticalExam(Exam exam) throws SQLException ;

    public  Exam getFutureWrittenExams() throws SQLException ;

    public  Exam getFuturePracticalExams() throws SQLException ;
}
