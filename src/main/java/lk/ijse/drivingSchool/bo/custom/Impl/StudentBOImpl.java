package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.StudentBO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = new StudentDAOImpl();
    VehicleClassDAO vehicleClassDAO = new VehicleClassDAOImpl();

    public ArrayList<Student> getAll() throws SQLException{
        return studentDAO.getAll();
    }
    public  String getId(String NIC) throws SQLException{
        return studentDAO.getId(NIC);
    }
    public  String getVehicleClass(String vehicleClassId) throws SQLException{
        return vehicleClassDAO.getVehicleClass(vehicleClassId);
    }
}
