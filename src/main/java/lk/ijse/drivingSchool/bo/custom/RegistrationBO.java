package lk.ijse.drivingSchool.bo.custom;

import lk.ijse.drivingSchool.bo.SuperBO;
import lk.ijse.drivingSchool.entity.User;

import java.sql.SQLException;

public interface RegistrationBO extends SuperBO {
    public  boolean save(User user) throws SQLException;
}
