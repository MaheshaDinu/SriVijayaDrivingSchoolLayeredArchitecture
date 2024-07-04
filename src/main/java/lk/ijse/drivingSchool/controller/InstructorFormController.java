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
import lk.ijse.drivingSchool.bo.custom.Impl.InstructorBOImpl;
import lk.ijse.drivingSchool.bo.custom.InstructorBO;
import lk.ijse.drivingSchool.entity.Instructor;
import lk.ijse.drivingSchool.view.tdm.InstructorTm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorFormController {
    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLicence;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<InstructorTm> tblInstructor;
    private ObservableList<InstructorTm> instructorTmObservableList = FXCollections.observableArrayList();
    public BorderPane borderPane;
    InstructorBO instructorBO = new InstructorBOImpl();
    @FXML
    public void initialize(ResultSet resultSet, BorderPane borderPane) {
        setValueFactory();
        loadInstructorTable();
        this.borderPane =borderPane;
    }

    private void loadInstructorTable() {
        try{
        ArrayList<Instructor> instructors = instructorBO.getAll();

            for (Instructor i:instructors){
                String id = instructorBO.getId(i.getFirstName());
                String name = i.getFirstName()+" "+i.getLastname();
                String license = i.getLicenseNumber();
                String contactNo = i.getContactNo();

                InstructorTm instructorTm = new InstructorTm(id,name,license,contactNo);
                instructorTmObservableList.add(instructorTm);
            }
            tblInstructor.setItems(instructorTmObservableList);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLicence.setCellValueFactory(new PropertyValueFactory<>("license"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
    }

    @FXML
    void btnAddInstructorOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/addInstructor_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnInstructorRemoveOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/removeinstructor_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
