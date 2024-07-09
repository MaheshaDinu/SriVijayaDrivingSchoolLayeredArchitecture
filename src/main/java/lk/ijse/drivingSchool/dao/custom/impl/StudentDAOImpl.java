package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.entity.Student;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    public  ArrayList<Student> getAll() throws SQLException {

        ResultSet resultSet =null;
        ArrayList<Student> students = new ArrayList<>();


            /*String sql = "select * from student";
            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement ptsm = connection.prepareStatement(sql);*/
            resultSet = SQLUtil.execute("select * from student");
            while (resultSet.next()) {
                String id = resultSet.getString("student_id");
                String NIC = resultSet.getString("NIC");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String vehicleClassId = resultSet.getString("vehicle_class_id");

                Student student = new Student(NIC, firstName, lastName, resultSet.getString("height"), resultSet.getString("weight_kg"), resultSet.getString("date_of_birth"), resultSet.getString("blood_group"), resultSet.getString("contact_no"), resultSet.getString("address"), resultSet.getString("user_id"), vehicleClassId);
                students.add(student);
            }
            return students;


    }

    public  boolean save(Student student) throws SQLException {
        /*String sql = "INSERT INTO student (NIC, first_name, last_name, height, weight_kg, date_of_birth, blood_group, contact_no, address, user_id, vehicle_class_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm =connection.prepareStatement(sql);
            pstm.setObject(1,student.getNIC());
            pstm.setObject(2,student.getFirstName());
            pstm.setObject(3,student.getLastName());
            pstm.setObject(4,student.getHeight());
            pstm.setObject(5,student.getWeight());
            pstm.setObject(6,student.getDateOfBirth());
            pstm.setObject(7,student.getBloodGroup());
            pstm.setObject(8,student.getContactNo());
            pstm.setObject(9,student.getAddress());
            pstm.setObject(10,student.getUserId());
            pstm.setObject(11,student.getVehicleClassId());*/

            return SQLUtil.execute("INSERT INTO student (NIC, first_name, last_name, height, weight_kg, date_of_birth, blood_group, contact_no, address, user_id, vehicle_class_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)",student.getNIC(),student.getFirstName(),student.getLastName(),student.getHeight(),student.getWeight(),student.getDateOfBirth(),student.getBloodGroup(),student.getContactNo(),student.getAddress(),student.getUserId(),student.getVehicleClassId());

    }

    public  boolean delete(String nic) throws SQLException {
       /* String sql = "delete from student where NIC = ?";

        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,nic);*/
        return SQLUtil.execute("delete from student where NIC = ?",nic);
    }

    public  Student get(String nic) throws SQLException {
        /*String sql ="select * from student where NIC = ?";
        Connection connection = null;

            connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,nic);*/
            ResultSet resultSet = SQLUtil.execute("select * from student where NIC = ?",nic);
            Student student = null;
            if (resultSet.next()){
                student =new Student(resultSet.getString("NIC"),resultSet.getString("first_name"),resultSet.getString("last_name"),resultSet.getString("height"),resultSet.getString("weight_kg"),resultSet.getString("date_of_birth"),resultSet.getString("blood_group"),resultSet.getString("contact_no"),resultSet.getString("address"),resultSet.getString("user_id"),resultSet.getString("vehicle_class_id"));

            }
            return student;


    }


    public  String getNextId() throws SQLException {
        String nextId =null;

        /*String sql = "select student_id from student order by student_id desc limit 1";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtil.execute("select student_id from student order by student_id desc limit 1");
        if (resultSet.next()){
            nextId = resultSet.getString("student_id");

        }
        return nextId;

    }

    public  String getName(String studentId) throws SQLException {

        String SName = null;
       /* String sql = "select * from student where student_id =?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,studentId);*/
            ResultSet resultSet = SQLUtil.execute("select * from student where student_id =?",studentId);
            if (resultSet.next()){
                SName = resultSet.getString("first_name")+" "+resultSet.getString("last_name");
            }
            return SName;


    }

    public  String getId(String NIC) throws SQLException {

        String id =null;
        /*String sql ="select * from student where NIC = ?";
        Connection connection = null;


            connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,NIC);*/
            ResultSet resultSet = SQLUtil.execute("select * from student where NIC = ?",NIC);
            if (resultSet.next()){
                return id = resultSet.getString("student_id");
            }

        return id;

    }

    public  boolean updateStudent(String nic, String firstname, String lastname, String height, String weight, String dateOfBirth, String bloodGroup, String contactNo, String address, String vehicleClassId, String inputNIC) throws SQLException {
        /*String sql = "update student set NIC =?,first_name =?, last_name = ?, height =?, weight_kg =?, date_of_birth =?, blood_group =?, contact_no =?, address =?,vehicle_class_id =? where NIC = ?";

            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,nic);
            pstm.setObject(2,firstname);
            pstm.setObject(3,lastname);
            pstm.setObject(4,height);
            pstm.setObject(5,weight);
            pstm.setObject(6,dateOfBirth);
            pstm.setObject(7,bloodGroup);
            pstm.setObject(8,contactNo);
            pstm.setObject(9,address);
            pstm.setObject(10,vehicleClassId);
            pstm.setObject(11,inputNIC);*/

            return SQLUtil.execute("update student set NIC =?,first_name =?, last_name = ?, height =?, weight_kg =?, date_of_birth =?, blood_group =?, contact_no =?, address =?,vehicle_class_id =? where NIC = ?",nic,firstname,lastname,height,weight,dateOfBirth,bloodGroup,contactNo,address,vehicleClassId,inputNIC);

    }
}
