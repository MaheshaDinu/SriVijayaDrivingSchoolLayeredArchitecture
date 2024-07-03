package lk.ijse.drivingSchool.bo.custom;

import java.sql.SQLException;

public interface RemoveLessonBO {
    public  boolean delete(String date, String time) throws SQLException;
}
