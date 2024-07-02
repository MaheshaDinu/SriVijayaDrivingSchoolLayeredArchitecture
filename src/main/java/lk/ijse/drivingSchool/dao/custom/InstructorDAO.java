package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.dao.CrudDAO;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InstructorDAO extends CrudDAO<Instructor> {


    public List<String> getInstructorsList() throws SQLException ;



    public  String getInstructor(String instructorId) throws SQLException ;

}
