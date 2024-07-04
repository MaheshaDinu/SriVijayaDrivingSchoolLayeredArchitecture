package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.InstructorBO;
import lk.ijse.drivingSchool.dao.custom.InstructorDAO;
import lk.ijse.drivingSchool.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorBOImpl implements InstructorBO {
    InstructorDAO instructorDAO = new InstructorDAOImpl();
    public ArrayList<Instructor> getAll() throws SQLException{
        return instructorDAO.getAll();
    }
    public  String getId(String instructor) throws SQLException{
        return instructorDAO.getId(instructor);
    }
}
