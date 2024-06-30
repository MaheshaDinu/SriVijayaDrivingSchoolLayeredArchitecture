package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.InstructorDAO;
import lk.ijse.drivingSchool.entity.Instructor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {
    public  ArrayList<Instructor> getAllInstructors() throws SQLException {



            /*String sql = "select * from instructor";
            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement ptsm = connection.prepareStatement(sql);*/
            ResultSet resultSet = SQLUtil.execute("select * from instructor");
            ArrayList<Instructor> instructors = new ArrayList<>();
            while (resultSet.next()){
                Instructor instructor = new Instructor(resultSet.getString("first_name"),resultSet.getString("last_name"),resultSet.getString("license_number"),resultSet.getString("contact_no"));
                instructors.add(instructor);
            }

        return instructors;
    }

    public  List<String> getInstructorsList() throws SQLException {
        /*String sql = "select * from instructor";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtil.execute("select * from instructor");
        List<String> instructorList = new ArrayList<>();
        while (resultSet.next()){
            instructorList.add(resultSet.getString("first_name"));

        }
        return instructorList;
    }

    public  String getId(String instructor) throws SQLException {
        String id =null;
        /*String sql = "select * from instructor where first_name =?";

            Connection connection =DbConnnection.getInstance().getConnection();
            PreparedStatement pstm =connection.prepareStatement(sql);
            pstm.setObject(1,instructor);*/
            ResultSet resultSet =SQLUtil.execute("select * from instructor where first_name =?",instructor);
            if (resultSet.next()){
                id = resultSet.getString("instructor_id");
            }

        return id;
    }

    public  boolean saveInstructor(Instructor instructor) throws SQLException {
        /*String sql ="INSERT INTO instructor (first_name, last_name, license_number, contact_no) VALUES (?,?,?,?)";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, instructor.getFirstName());
        pstm.setObject(2,instructor.getLastname());
        pstm.setObject(3,instructor.getLicenseNumber());
        pstm.setObject(4,instructor.getContactNo());*/
        return SQLUtil.execute("INSERT INTO instructor (first_name, last_name, license_number, contact_no) VALUES (?,?,?,?)",instructor.getFirstName(),instructor.getLastname(),instructor.getLicenseNumber(),instructor.getContactNo());
    }

    public  String getInstructor(String instructorId) throws SQLException {

        String instructor=null;
        /*String sql = "select * from instructor where instructor_id = ?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,instructorId);*/
            ResultSet resultSet = SQLUtil.execute("select * from instructor where instructor_id = ?",instructorId);
            if (resultSet.next()) {
                String fName = resultSet.getString("first_name");
                String lName = resultSet.getString("last_name");
                instructor = fName + " " + lName;
            }
            return instructor;

    }

    public  boolean removeInstructor(String license) throws SQLException {
        /*String sql ="delete from instructor where license_number = ?";

            Connection connection =DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,license);*/
            return SQLUtil.execute("delete from instructor where license_number = ?",license);

    }

    public  Instructor getInstructorFromLicense(String license) throws SQLException {
        /*String sql = "select * from instructor where license_number = ?";


            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,license);*/
            ResultSet resultSet = SQLUtil.execute("select * from instructor where license_number = ?",license);
            return new Instructor(resultSet.getString("first_name"),resultSet.getString("last_name"),resultSet.getString("license_number"),resultSet.getString("contact_no"));

    }
}
