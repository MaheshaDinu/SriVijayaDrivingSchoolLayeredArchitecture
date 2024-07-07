package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.SQLException;

public interface AddInstructorBO extends SuperBO {
    public  boolean save(Instructor instructor) throws SQLException;
}
