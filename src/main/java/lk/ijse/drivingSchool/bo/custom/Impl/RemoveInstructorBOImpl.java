package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.RemoveInstructorBO;
import lk.ijse.drivingSchool.dao.custom.InstructorDAO;
import lk.ijse.drivingSchool.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.SQLException;

public class RemoveInstructorBOImpl implements RemoveInstructorBO {
    InstructorDAO instructorDAO =new InstructorDAOImpl();
    public  boolean delete(String license) throws SQLException{
        return instructorDAO.delete(license);
    }
    public Instructor get(String license) throws SQLException{
        return instructorDAO.get(license);
    }
    public  String getInstructor(String instructorId) throws SQLException{
        return instructorDAO.getInstructor(instructorId);
    }
    public  String getId(String instructor) throws SQLException{
        return instructorDAO.getId(instructor);
    }
}
