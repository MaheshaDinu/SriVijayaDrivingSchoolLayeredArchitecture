package lk.ijse.drivingSchool.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import lk.ijse.drivingSchool.bo.custom.AttendanceBO;
import lk.ijse.drivingSchool.bo.custom.Impl.AttendanceBOImpl;
import lk.ijse.drivingSchool.entity.Attendance;
import lk.ijse.drivingSchool.entity.Lesson;
import lk.ijse.drivingSchool.entity.Student;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AttendanceFormController {
    @FXML
    private JFXComboBox<String> cmbEndTimeAMPM;

    @FXML
    private JFXComboBox<String> cmbStartTimeAMPM;

    @FXML
    private JFXComboBox<String> cmbTime;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDaysAttended;

    @FXML
    private TextField txtEndTime;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtNICSearch;

    @FXML
    private TextField txtStartTime;
    private LocalDate now = LocalDate.now();
    private String startAMPM;
    private String endAMPM;
    private String time;
    private String lessonId;
    private String studentId;
    AttendanceBO attendanceBO = new AttendanceBOImpl();

    public void initialize(ResultSet resultSet, BorderPane borderPane) {
        lblDate.setText(String.valueOf(now));
        getTimes();
        getAMPM();
    }

    private void getAMPM() {
        cmbStartTimeAMPM.setItems(FXCollections.observableArrayList("AM","PM"));
        cmbEndTimeAMPM.setItems(FXCollections.observableArrayList("AM","PM"));
    }

    private void getTimes() {
        cmbTime.setItems(FXCollections.observableArrayList("10.00 AM","03.00 PM"));
    }

    @FXML
    void btnMarkAttendanceOnAction(ActionEvent event) {
        try {
        Lesson lesson = attendanceBO.getLesson(String.valueOf(now),time);
        String NIC = txtNIC.getText();
        String startTime = txtStartTime.getText()+" "+startAMPM;
        String endTime = txtEndTime.getText()+" "+endAMPM;




                if (lesson!=null) {

                    lessonId =attendanceBO.getLessonId(lesson.getDate(),lesson.getTime());
                    Student student = attendanceBO.getStudent(NIC);
                    if (student!=null){
                        studentId = attendanceBO.getStudentId(NIC);
                        Attendance attendance = new Attendance(lessonId,studentId,String.valueOf(now),startTime,endTime,"Present");
                        boolean isSaved = attendanceBO.save(attendance);
                        if (isSaved){
                            new Alert(Alert.AlertType.CONFIRMATION, "Attendance Recorded!").show();
                            txtNIC.setText("");

                        }else {
                            new Alert(Alert.AlertType.ERROR, "Attendance Not Recorded!").show();
                        }
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR, "No Lesson Today!").show();
                }

        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
            throw new RuntimeException(e);

        }


    }

    @FXML
    void btnFindAttendanceOnAction(ActionEvent event) {
        String NIC = txtNICSearch.getText();
        try {
            String studentId = attendanceBO.getStudentId(NIC);
            lblDaysAttended.setText(attendanceBO.getDaysAttended(studentId));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbEndTimeAMPMOnAction(ActionEvent event) {
        endAMPM = cmbEndTimeAMPM.getValue();

    }

    @FXML
    void cmbStartTimeAMPMOnAction(ActionEvent event) {
        startAMPM = cmbStartTimeAMPM.getValue();

    }

    @FXML
    void cmbtimeOnAction(ActionEvent event) {
        time = cmbTime.getValue();

    }

}
