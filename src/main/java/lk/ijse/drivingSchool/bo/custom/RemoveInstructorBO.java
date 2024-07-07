package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.SQLException;

public interface RemoveInstructorBO extends SuperBO {
    public  boolean delete(String license) throws SQLException ;
    public Instructor get(String license) throws SQLException;
    public  String getInstructor(String instructorId) throws SQLException;
    public  String getId(String instructor) throws SQLException;
}
