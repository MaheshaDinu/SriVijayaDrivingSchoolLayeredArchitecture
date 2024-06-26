package lk.ijse.drivingSchool.dao.custom;

import lk.ijse.drivingSchool.Db.DbConnnection;
import lk.ijse.drivingSchool.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO {
    public  boolean saveUser(User user) throws SQLException ;

    public  User userNameCheck(String userName) throws SQLException ;



    public  String getUserId(User user) throws SQLException;
}
