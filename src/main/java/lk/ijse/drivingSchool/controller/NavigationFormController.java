package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lk.ijse.drivingSchool.entity.User;
import lk.ijse.drivingSchool.model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NavigationFormController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label lblUserName;

    private String firstName;
    private String lastName;


    public User user;


    public void getName(User user) throws SQLException {


            firstName = user.getFirstName();
            lastName = user.getLastName();
            setName(firstName, lastName);

    }

    private void setName(String firstName, String lastName) {

        lblUserName.setText(firstName+" "+lastName);
    }


    @FXML
    public void initialize(User user) throws IOException, SQLException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        borderPane.setCenter(rootNode);
        getName(user);
        this.user = user;





    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        borderPane.setCenter(rootNode);

    }

    @FXML
    void btnExamOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/exam_form.fxml"));
        Parent rootNode = loader.load();
        ExamFormController examFormController = loader.getController();
        examFormController.initialize(borderPane);

        borderPane.setCenter(rootNode);

    }

    @FXML
    void btnInstructorOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/instructor_form.fxml"));
        Parent rootNode = loader.load();
        InstructorFormController instructorFormController = loader.getController();
        instructorFormController.initialize(user,borderPane);

        borderPane.setCenter(rootNode);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        BorderPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene =new Scene(rootNode);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Log in");
        stage.show();

    }

    @FXML
    void btnPaymentsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment_form.fxml"));
        Parent rootNode = loader.load();
        PaymentFormController paymentFormController = loader.getController();
        paymentFormController.initialize(user,borderPane);

        borderPane.setCenter(rootNode);

    }

    @FXML
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/attendance_form.fxml"));
        Parent rootNode = loader.load();
        AttendanceFormController attendanceFormController = loader.getController();
        attendanceFormController.initialize(user,borderPane);

        borderPane.setCenter(rootNode);

    }

    @FXML
    void btnScheduleOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/lessonschedule_form.fxml"));
        Parent rootNode = loader.load();
        LessonScheduleFormController lessonScheduleFormController = loader.getController();
        lessonScheduleFormController.initialize(user,borderPane);

        borderPane.setCenter(rootNode);

    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/students_form.fxml"));
        Parent rootNode = loader.load();
        StudentsFormController studentsFormController = loader.getController();
        studentsFormController.initialize(user,borderPane);

        borderPane.setCenter(rootNode);

    }

    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vehicle_form.fxml"));
        Parent rootNode = loader.load();
        VehicleFormController vehicleFormController = loader.getController();
        vehicleFormController.initialize(user,borderPane);

        borderPane.setCenter(rootNode);

    }



}
