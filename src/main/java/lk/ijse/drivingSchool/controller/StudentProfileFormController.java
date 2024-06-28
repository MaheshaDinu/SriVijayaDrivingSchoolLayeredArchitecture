package lk.ijse.drivingSchool.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import lk.ijse.drivingSchool.bo.custom.Impl.StudentProfileBOImpl;
import lk.ijse.drivingSchool.bo.custom.StudentProfileBO;
import lk.ijse.drivingSchool.entity.Student;


import java.sql.SQLException;


public class StudentProfileFormController {
    @FXML
    private Label lblAddress;

    @FXML
    private Label lblBloodGroup;

    @FXML
    private Label lblContactNo;

    @FXML
    private Label lblDOB;

    @FXML
    private Label lblDaysAttendedForLessons;

    @FXML
    private Label lblHeight;

    @FXML
    private Label lblNIC;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPaid;

    @FXML
    private Label lblRemaining;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private Label lblVehicleClass;

    @FXML
    private Label lblWeight;
    StudentProfileBO studentProfileBO = new StudentProfileBOImpl();

    @FXML
    public void initialize(Student student){
        try {
            setLabels(student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setLabels(Student student) throws SQLException {
        lblName.setText(student.getFullName());
        lblNIC.setText(student.getNIC());
        lblDOB.setText(student.getDateOfBirth());
        lblHeight.setText(student.getHeight()+" cm");
        lblWeight.setText(student.getWeight()+" kg");
        lblBloodGroup.setText(student.getBloodGroup());
        lblContactNo.setText(student.getContactNo());
        lblAddress.setText(student.getAddress());
        lblVehicleClass.setText(studentProfileBO.getVehicleClass(student.getVehicleClassId()));

        String studentId = studentProfileBO.getStudentId(student.getNIC());
        lblDaysAttendedForLessons.setText(studentProfileBO.getDaysAttended(studentId));

        String totalAmount = studentProfileBO.getFee(student.getVehicleClassId());
        lblTotalAmount.setText(totalAmount);

        String paid = studentProfileBO.getAllStudentPayment(studentProfileBO.getStudentId(student.getNIC()));
        lblPaid.setText(paid);

        double remaining = Double.parseDouble(totalAmount) - Double.parseDouble(paid);
        lblRemaining.setText(String.valueOf(remaining));
    }
}
