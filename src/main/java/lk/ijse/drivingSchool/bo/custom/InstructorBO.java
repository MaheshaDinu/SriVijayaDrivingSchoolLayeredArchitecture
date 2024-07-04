package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InstructorBO {
    public ArrayList<Instructor> getAll() throws SQLException;
    public  String getId(String instructor) throws SQLException;
}
