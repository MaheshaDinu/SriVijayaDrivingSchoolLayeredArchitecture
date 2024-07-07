package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Lesson;

import java.sql.SQLException;
import java.util.List;

public interface AddLessonBO extends SuperBO {
    public List<String> getVehicleList() throws SQLException ;
    public  List<String> getInstructorsList() throws SQLException;
    public  boolean save(Lesson lesson) throws SQLException;
    public  String getInstructorId(String instructor) throws SQLException;
    public  String getVehicleId(String vehicle) throws SQLException ;
}
