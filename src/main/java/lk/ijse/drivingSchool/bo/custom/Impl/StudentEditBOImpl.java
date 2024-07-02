package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.StudentEditBO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentEditBOImpl implements StudentEditBO {
    StudentDAO studentDAO = new StudentDAOImpl();
    VehicleClassDAO vehicleClassDAO = new VehicleClassDAOImpl();
    public  boolean updateStudent(String nic, String firstname, String lastname, String height, String weight, String dateOfBirth, String bloodGroup, String contactNo, String address, String vehicleClassId, String inputNIC) throws SQLException{
        return studentDAO.updateStudent(nic,firstname,lastname,height,weight,dateOfBirth,bloodGroup,contactNo,address,vehicleClassId,inputNIC);
    }
    public Student get(String nic) throws SQLException{
        return studentDAO.get(nic);
    }
    public List<String> getAllVehicleClass() throws SQLException{
        return vehicleClassDAO.getAllVehicleClass();
    }
    public  String getId(String vehicleClass) throws SQLException{
        return vehicleClassDAO.getId(vehicleClass);
    }
    public  String getVehicleClass(String vehicleClassId) throws SQLException{
        return vehicleClassDAO.getVehicleClass(vehicleClassId);
    }
}
