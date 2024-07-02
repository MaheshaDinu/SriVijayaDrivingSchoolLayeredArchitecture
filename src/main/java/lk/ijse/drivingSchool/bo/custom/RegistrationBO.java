package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.entity.User;

import java.sql.SQLException;

public interface RegistrationBO {
    public  boolean save(User user) throws SQLException;
}
