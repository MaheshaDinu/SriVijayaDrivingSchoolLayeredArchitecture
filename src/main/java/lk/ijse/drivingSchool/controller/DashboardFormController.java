package lk.ijse.drivingSchool.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.DashboardBO;
import lk.ijse.drivingSchool.bo.custom.Impl.DashboardBOImpl;
import lk.ijse.drivingSchool.entity.Exam;
import lk.ijse.drivingSchool.entity.Instructor;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardFormController {

    @FXML
    private Label lblInstructorCount;

    @FXML
    private Label lblStudentCount;

    @FXML
    private Label lblTotalIncome;
    @FXML
    private Label lblPracticalExamDate;

    @FXML
    private Label lblPracticalExamTime;
    @FXML
    private Label lblWrittenExamDate;

    @FXML
    private Label lblWrittenExamTime;
    DashboardBO dashboardBO = (DashboardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);

    @FXML
    private void initialize(){
        setStudentCount();
        setInstructorCount();
        setTotalIncome();
        setNextExamDates();
    }

    private void setNextExamDates() {

        try {
            setWrittenExamDateAndTime();
            setPracticalExamDateAndTime();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setPracticalExamDateAndTime() throws SQLException {
        Exam exam = dashboardBO.getNextPracticalExam();
        if (exam!=null) {
            lblPracticalExamDate.setText(exam.getDate());
            lblPracticalExamTime.setText(exam.getTime());
        } else {
            lblPracticalExamDate.setText("No Date");
            lblPracticalExamTime.setText("No Time");
        }
    }

    private void setWrittenExamDateAndTime() throws SQLException {
        Exam exam = dashboardBO.getNextWrittenExam();
        if (exam!=null) {
            lblWrittenExamDate.setText(exam.getDate());
            lblWrittenExamTime.setText(exam.getTime());
        } else {
            lblWrittenExamDate.setText("No Date");
            lblWrittenExamTime.setText("No Time");
        }

    }

    private void setTotalIncome() {
        String totalIncome;

        try {

                totalIncome = dashboardBO.getTotalIncome();
                lblTotalIncome.setText("Rs:"+totalIncome);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setInstructorCount() {
        int count=0;
        try {
        ArrayList<Instructor> instructors = dashboardBO.getAllInstructors();
        for (Instructor i: instructors){



            count++;
        }
        lblInstructorCount.setText(String.valueOf(count));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void setStudentCount()  {
        int count=0;
        try {
        ArrayList<Student> students = dashboardBO.getAllStudents();
        for (Student s : students){



            count++;
        }
        lblStudentCount.setText(String.valueOf(count));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
