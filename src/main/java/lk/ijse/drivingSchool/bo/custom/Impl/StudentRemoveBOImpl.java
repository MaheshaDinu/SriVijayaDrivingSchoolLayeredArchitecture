package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.StudentRemoveBO;
import lk.ijse.drivingSchool.dao.DAOFactory;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;

public class StudentRemoveBOImpl implements StudentRemoveBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    VehicleClassDAO vehicleClassDAO = (VehicleClassDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE_CLASS);

    public  boolean delete(String nic) throws SQLException{
        return studentDAO.delete(nic);
    }
    public Student get(String nic) throws SQLException{
        return studentDAO.get(nic);
    }
    public  String getName(String studentId) throws SQLException{
        return studentDAO.getName(studentId);
    }
    public  String getId(String NIC) throws SQLException{
        return studentDAO.getId(NIC);
    }
    public  String getVehicleClass(String vehicleClassId) throws SQLException{
        return vehicleClassDAO.getVehicleClass(vehicleClassId);
    }
}
