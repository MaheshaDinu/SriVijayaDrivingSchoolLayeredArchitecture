package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.LessonScheduleBO;
import lk.ijse.drivingSchool.dao.custom.InstructorDAO;
import lk.ijse.drivingSchool.dao.custom.LessonDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleDAO;
import lk.ijse.drivingSchool.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.LessonDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.drivingSchool.entity.Lesson;

import java.sql.SQLException;

public class LessonScheduleBOImpl implements LessonScheduleBO {
    LessonDAO lessonDAO = new LessonDAOImpl();
    InstructorDAO instructorDAO = new InstructorDAOImpl();
    VehicleDAO vehicleDAO = new VehicleDAOImpl();
    public Lesson getFutureLessons() throws SQLException{
        return lessonDAO.getFutureLessons();
    }
    public  String getInstructor(String instructorId) throws SQLException{
        return instructorDAO.getInstructor(instructorId);
    }
    public  String getVehicle(String vehicleId) throws SQLException{
        return vehicleDAO.getVehicle(vehicleId);
    }
}
