package lk.ijse.drivingSchool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lk.ijse.drivingSchool.Repository.InstructorRepo;
import lk.ijse.drivingSchool.Repository.LessonRepo;
import lk.ijse.drivingSchool.Repository.VehicleRepo;
import lk.ijse.drivingSchool.bo.custom.Impl.LessonScheduleBOImpl;
import lk.ijse.drivingSchool.bo.custom.LessonScheduleBO;
import lk.ijse.drivingSchool.entity.Lesson;
import lk.ijse.drivingSchool.model.tableModel.LessonTm;
import lk.ijse.drivingSchool.view.tdm.LessonTm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonScheduleFormController {
    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colInstructor;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colVehicle;

    @FXML
    private TableView<LessonTm> tblLessonSchedule;
    private ObservableList<LessonTm> lessonTmObservableList = FXCollections.observableArrayList();

    public BorderPane borderPane;
    LessonScheduleBO lessonScheduleBO = new LessonScheduleBOImpl();

    public void initialize(ResultSet resultSet, BorderPane borderPane) {

        setValueFactory();
        loadLessonTable();
        this.borderPane = borderPane;
    }

    private void loadLessonTable() {
        try {
        Lesson lesson = lessonScheduleBO.getFutureLessons();

            while (lesson!=null){
                String date = lesson.getDate();
                String time = lesson.getTime();
                String instructorId = lesson.getInstructorId();
                String vehicleId = lesson.getVehicleId();

                LessonTm lessonTm = new LessonTm(date,time,lessonScheduleBO.getInstructor(instructorId),lessonScheduleBO.getVehicle(vehicleId));
                lessonTmObservableList.add(lessonTm);
            }
            tblLessonSchedule.setItems(lessonTmObservableList);
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void setValueFactory() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colInstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        colVehicle.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
    }


    @FXML
    void btnAddLessonOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/addlesson_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnLessonRemoveOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/removelesson_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
