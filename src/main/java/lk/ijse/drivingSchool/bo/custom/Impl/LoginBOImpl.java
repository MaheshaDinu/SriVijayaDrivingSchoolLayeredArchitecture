package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.LoginBO;
import lk.ijse.drivingSchool.dao.DAOFactory;
import lk.ijse.drivingSchool.dao.custom.UserDAO;
import lk.ijse.drivingSchool.dao.custom.impl.UserDAOImpl;
import lk.ijse.drivingSchool.entity.User;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    public User userNameCheck(String userName) throws SQLException{
        return userDAO.userNameCheck(userName);
    }
}
