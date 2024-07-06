package lk.ijse.drivingSchool.dao.custom.impl;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.dao.SQLUtil;
import lk.ijse.drivingSchool.dao.custom.UserDAO;
import lk.ijse.drivingSchool.entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException {
        return null;
    }

    public  boolean save(User user) throws SQLException {

        /*String sql = "insert into user (user_name, password, first_name, last_name, contact_no, address)values(?,?,?,?,?,?)";


            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement ptsm = connection.prepareStatement(sql)){

            ptsm.setObject(1, user.getUserName());
            ptsm.setObject(2, user.getPassword());
            ptsm.setObject(3, user.getFirstName());
            ptsm.setObject(4, user.getLastName());
            ptsm.setObject(5, user.getContactNo());
            ptsm.setObject(6, user.getAddress());*/

            return SQLUtil.execute("insert into user (user_name, password, first_name, last_name, contact_no, address)values(?,?,?,?,?,?)",user.getUserName(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getContactNo(),user.getAddress());



    }

    @Override
    public boolean delete(String nic) throws SQLException {
        return false;
    }

    @Override
    public User get(String nic) throws SQLException {
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        return null;
    }

    @Override
    public String getName(String id) {
        return null;
    }

    @Override
    public String getId(String contactNo) throws SQLException {
        String id = null;
        ResultSet resultSet = SQLUtil.execute("select * from user where contact_no =?",contactNo);
        if (resultSet.next()){
            id = resultSet.getString("user_id");
        }
        return id;
    }

    public  User userNameCheck(String userName) throws SQLException {
        /*String sql ="select * from user where user_name =?";
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement ptsm = connection.prepareStatement(sql);
        ptsm.setObject(1,userName);*/
        User user = null;
        ResultSet resultSet = SQLUtil.execute("select * from user where user_name =?",userName);
        if (resultSet.next()){
            user=  new User(resultSet.getString("user_name"),resultSet.getString("first_name"),resultSet.getString("last_name"),resultSet.getString("contact_no"),resultSet.getString("address"),resultSet.getString("password"));
        }

        return user;
    }



    public  String getUserId(User user) throws SQLException {
        String userId = null;
        /*String sql ="select * from user where user_name = ?";


            Connection connection = DbConnnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,user.getUserName());*/
            ResultSet resultSet = SQLUtil.execute("select * from user where user_name = ?",user.getUserName());
            if (resultSet.next()){
                userId = resultSet.getString("user_id");
            }

        return userId;
    }
}
