package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.RegistrationBO;
import lk.ijse.drivingSchool.dao.custom.UserDAO;
import lk.ijse.drivingSchool.dao.custom.impl.UserDAOImpl;
import lk.ijse.drivingSchool.entity.User;

import java.sql.SQLException;

public class RegistrationBOImpl implements RegistrationBO {
    UserDAO userDAO = new UserDAOImpl();
    public  boolean save(User user) throws SQLException{
        return userDAO.save(user);
    }
}
