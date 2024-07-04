package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.AddInstructorBO;
import lk.ijse.drivingSchool.bo.custom.InstructorBO;
import lk.ijse.drivingSchool.dao.custom.InstructorDAO;
import lk.ijse.drivingSchool.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.drivingSchool.entity.Instructor;

import java.sql.SQLException;

public class AddInstructorBOImpl implements AddInstructorBO {
    InstructorDAO instructorDAO = new InstructorDAOImpl();
    public  boolean save(Instructor instructor) throws SQLException{
        return instructorDAO.save(instructor);
    }
}
