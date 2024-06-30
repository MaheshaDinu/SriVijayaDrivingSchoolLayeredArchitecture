package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InstructorDAO {
    public ArrayList<Instructor> getAllInstructors() throws SQLException ;

    public List<String> getInstructorsList() throws SQLException ;

    public  String getId(String instructor) throws SQLException ;

    public  boolean saveInstructor(Instructor instructor) throws SQLException ;

    public  String getInstructor(String instructorId) throws SQLException ;
    public  boolean removeInstructor(String license) throws SQLException ;

    public  Instructor getInstructorFromLicense(String license) throws SQLException ;
}
