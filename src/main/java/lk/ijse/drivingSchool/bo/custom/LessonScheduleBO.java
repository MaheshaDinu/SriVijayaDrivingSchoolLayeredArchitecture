package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Lesson;

import java.sql.SQLException;

public interface LessonScheduleBO extends SuperBO {
    public Lesson getFutureLessons() throws SQLException ;
    public  String getInstructor(String instructorId) throws SQLException;
    public  String getVehicle(String vehicleId) throws SQLException;
}
