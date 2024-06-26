package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO {
    public  ArrayList<Student> getAllStudents() throws SQLException ;

    public  boolean saveStudent(Student student) throws SQLException ;

    public  boolean deleteStudentByNIC(String nic) throws SQLException ;

    public  ResultSet getStudent(String nic) throws SQLException ;


    public  String getNextStudentId() throws SQLException ;

    public  String getStudentName(String studentId) ;

    public  String getStudentId(String NIC) ;

    public  boolean updateStudent(String nic, String firstname, String lastname, String height, String weight, String dateOfBirth, String bloodGroup, String contactNo, String address, String vehicleClassId, String inputNIC) ;
}
