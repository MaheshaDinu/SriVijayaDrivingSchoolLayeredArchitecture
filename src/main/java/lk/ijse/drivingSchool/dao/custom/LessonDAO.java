package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.dao.CrudDAO;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Lesson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface LessonDAO extends CrudDAO<Lesson> {
    public  boolean save(Lesson lesson) throws SQLException ;

    public  Lesson getFutureLessons() throws SQLException ;

    public  Lesson getLesson(String date, String time) throws SQLException ;

    public  boolean delete(String date, String time) throws SQLException ;
    public String getLessonId(String date,String time) throws SQLException;
}
