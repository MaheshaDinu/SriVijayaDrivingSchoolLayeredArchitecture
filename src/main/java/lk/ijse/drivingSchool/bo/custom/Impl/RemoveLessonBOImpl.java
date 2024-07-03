package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.RemoveLessonBO;
import lk.ijse.drivingSchool.dao.custom.LessonDAO;
import lk.ijse.drivingSchool.dao.custom.impl.LessonDAOImpl;

import java.sql.SQLException;

public class RemoveLessonBOImpl implements RemoveLessonBO {
    LessonDAO lessonDAO = new LessonDAOImpl();
    public  boolean delete(String date, String time) throws SQLException {
        return lessonDAO.delete(date,time);
    }
}
