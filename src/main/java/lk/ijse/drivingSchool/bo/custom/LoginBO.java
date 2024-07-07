package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.User;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public User userNameCheck(String userName) throws SQLException ;
}
