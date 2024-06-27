package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;
import lk.ijse.drivingSchool.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class StudentRemoveFormController {
    @FXML
    private Label lblStudentName;

    @FXML
    private Label lblVehicleClass;

    @FXML
    private TextField txtNIC;
    public BorderPane borderPane;

    StudentDAO studentDAO = new StudentDAOImpl();
    VehicleClassDAO vehicleClassDAO = new VehicleClassDAOImpl();

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        String NIC = txtNIC.getText();

        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Do you really want to remove this student?", yes, no).showAndWait();

        if(type.orElse(no) == yes) {
            try {
                boolean isDeleted = studentDAO.deleteStudentByNIC(NIC);
                if (isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted!").show();
                    txtNIC.setText("");
                } else {
                    new Alert(Alert.AlertType.ERROR,"NIC Does Not Exist!");
                    txtNIC.setText("");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Sorry Something Happened!");
                txtNIC.setText("");
            }
        }



    }
@FXML
    public void initializer(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    @FXML
    void txtNICReleaseOnAction(KeyEvent event) {
        String NIC = txtNIC.getText();
        try {
            Student student = studentDAO.getStudent(NIC);
            if (student!=null){
                lblStudentName.setTextFill(Color.BLUE);
                lblStudentName.setText(studentDAO.getStudentName(studentDAO.getStudentId(student.getNIC())));
                lblVehicleClass.setTextFill(Color.BLUE);
                lblVehicleClass.setText(vehicleClassDAO.getVehicleClass(student.getVehicleClassId()));
            }
            else {
                lblStudentName.setTextFill(Color.RED);
                lblStudentName.setText("Student NIC does not Exist!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
