package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.SQLException;

public interface AddInstructorBO {
    public  boolean save(Instructor instructor) throws SQLException;
}
