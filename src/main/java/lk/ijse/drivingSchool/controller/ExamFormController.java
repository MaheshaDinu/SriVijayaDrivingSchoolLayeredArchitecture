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

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.ExamBO;
import lk.ijse.drivingSchool.bo.custom.Impl.ExamBOImpl;
import lk.ijse.drivingSchool.entity.Exam;

import lk.ijse.drivingSchool.view.tdm.ExamTm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamFormController {
    @FXML
    private TableColumn<?, ?> colPracticalExamDate;

    @FXML
    private TableColumn<?, ?> colPracticalExamTime;

    @FXML
    private TableColumn<?, ?> colWrittenExamDate;

    @FXML
    private TableColumn<?, ?> colWrittenExamTime;
    @FXML
    private TableView<ExamTm> tblPracticalExam;

    @FXML
    private TableView<ExamTm> tblWrittenExam;

    public BorderPane borderPane;
    ExamBO examBO = (ExamBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EXAM);
    @FXML
    public void initialize(BorderPane borderPane) {
        setValueFactoryWrittenExam();
        setValueFactoryPracticalExam();
        loadWrittenExamTable();
        loadPracticalExamTable();
        this.borderPane = borderPane;
    }

    private void loadPracticalExamTable() {
        try {
        ArrayList<Exam> exams = examBO.getFuturePracticalExams();
        ObservableList<ExamTm> examTmObservableList = FXCollections.observableArrayList();

            for (Exam e:exams){
                String date = e.getDate();
                String time = e.getTime();

                ExamTm examTm = new ExamTm(date,time);
                examTmObservableList.add(examTm);

            }
            tblPracticalExam.setItems(examTmObservableList);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void loadWrittenExamTable() {
        try {
        ArrayList<Exam>exams = examBO.getFutureWrittenExams();
        ObservableList<ExamTm> examTmObservableList = FXCollections.observableArrayList();

            for (Exam e:exams){
                String date = e.getDate();
                String time = e.getTime();

                ExamTm examTm = new ExamTm(date,time);
                examTmObservableList.add(examTm);

            }
            tblWrittenExam.setItems(examTmObservableList);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void setValueFactoryPracticalExam() {
        colPracticalExamDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPracticalExamTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void setValueFactoryWrittenExam() {
        colWrittenExamDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colWrittenExamTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    @FXML
    void btnAddPracticalExamOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/addpracticalexam_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddWrittenExamOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/addwrittenexam_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
