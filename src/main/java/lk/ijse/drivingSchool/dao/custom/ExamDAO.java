package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.CrudDAO;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ExamDAO extends CrudDAO<Exam> {
    public Exam getNextPracticalExam() throws SQLException ;

    public  Exam getNextWrittenExam() throws SQLException ;

    public  boolean saveWrittenExam(Exam exam) throws SQLException ;

    public  boolean savePracticalExam(Exam exam) throws SQLException ;

    public ArrayList<Exam> getFutureWrittenExams() throws SQLException ;

    public  ArrayList<Exam> getFuturePracticalExams() throws SQLException ;
}
