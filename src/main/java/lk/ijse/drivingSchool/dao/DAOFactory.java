package lk.ijse.drivingSchool.dao;

import lk.ijse.drivingSchool.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return (daoFactory ==null) ? daoFactory = new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        STUDENT,LESSON,INSTRUCTOR,VEHICLE,VEHICLE_CLASS,PAYMENT,ATTENDANCE,EXAM,USER
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            case LESSON:
                return new LessonDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case VEHICLE_CLASS:
                return new VehicleClassDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case EXAM:
                return new ExamDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
