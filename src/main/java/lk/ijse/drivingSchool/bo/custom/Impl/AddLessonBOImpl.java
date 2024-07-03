package lk.ijse.drivingSchool.bo.custom.Impl;

import lk.ijse.drivingSchool.bo.custom.AddLessonBO;
import lk.ijse.drivingSchool.dao.custom.InstructorDAO;
import lk.ijse.drivingSchool.dao.custom.LessonDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleDAO;
import lk.ijse.drivingSchool.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.LessonDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.drivingSchool.entity.Lesson;

import java.sql.SQLException;
import java.util.List;

public class AddLessonBOImpl implements AddLessonBO {
    VehicleDAO vehicleDAO = new VehicleDAOImpl();
    InstructorDAO instructorDAO = new InstructorDAOImpl();
    LessonDAO lessonDAO = new LessonDAOImpl();
    public List<String> getVehicleList() throws SQLException{
        return vehicleDAO.getVehicleList();
    }
    public  List<String> getInstructorsList() throws SQLException{
        return instructorDAO.getInstructorsList();
    }
    public  boolean save(Lesson lesson) throws SQLException{
        return lessonDAO.save(lesson);
    }
    public  String getInstructorId(String instructor) throws SQLException{
        return instructorDAO.getId(instructor);
    }
    public  String getVehicleId(String vehicle) throws SQLException {
        return vehicleDAO.getId(vehicle);
    }
}
