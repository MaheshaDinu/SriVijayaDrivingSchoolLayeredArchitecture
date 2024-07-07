package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;

import java.sql.SQLException;

public interface RemoveLessonBO extends SuperBO {
    public  boolean delete(String date, String time) throws SQLException;
}
