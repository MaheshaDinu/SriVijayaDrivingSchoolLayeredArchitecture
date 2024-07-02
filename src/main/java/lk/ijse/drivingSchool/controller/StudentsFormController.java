package lk.ijse.drivingSchool.controller;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import lk.ijse.drivingSchool.bo.custom.Impl.StudentBOImpl;
import lk.ijse.drivingSchool.bo.custom.StudentBO;
import lk.ijse.drivingSchool.dao.custom.StudentDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.StudentDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;
import lk.ijse.drivingSchool.entity.Student;
import lk.ijse.drivingSchool.entity.User;

import lk.ijse.drivingSchool.view.tdm.StudentTm;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsFormController {
    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProfile;

    @FXML
    private TableColumn<?, ?> colVehicleClass;
    @FXML
    private TableView<StudentTm> tblStudentTable;

    private ObservableList<StudentTm> studentTmObservableList = FXCollections.observableArrayList();
    private List<Student> studentList = new ArrayList<>();

    public BorderPane borderPane;
    StudentBO studentBO = new StudentBOImpl();



    public User user;

    public void initialize(User user, BorderPane borderPane){

        setValueFactory();
        loadStudentTable();
        this.user =user;


        this.borderPane = borderPane;



    }

    private void setValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colVehicleClass.setCellValueFactory(new PropertyValueFactory<>("vehicleClass"));
        colProfile.setCellValueFactory(new PropertyValueFactory<>("btnProfile"));

    }
    private void loadStudentTable(){

        try {
             studentList = studentBO.getAll();
            for (Student s : studentList){
                String id = studentBO.getId(s.getNIC());
                String NIC = s.getNIC();

                String vehicleClassId = s.getVehicleClassId();



                JFXButton btnProfile = new JFXButton("Profile");
                btnProfile.setCursor(Cursor.HAND);
                btnProfile.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE,new CornerRadii(40), Insets.EMPTY)));
                btnProfile.setTextFill(Color.WHITE);
                btnProfile.setOnAction(event -> handleProfileButtonClick(s));

                StudentTm studentTm = new StudentTm(id, NIC, s.getFullName(), studentBO.getVehicleClass(vehicleClassId), btnProfile);
                studentTmObservableList.add(studentTm);
            }
            tblStudentTable.setItems(studentTmObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void handleProfileButtonClick(Student student)  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/drivingSchool/view/studentprofile_form.fxml"));

            Parent rootNode = loader.load();

            StudentProfileFormController studentProfileFormController = loader.getController();
            studentProfileFormController.initialize(student);

            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Student Profile");
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnStudentEditOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/lk/ijse/drivingSchool/view/studentedit_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnStudentRegisterOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/lk/ijse/drivingSchool/view/studentregistration_form.fxml"));
            Parent rootNode = loader.load();
            StudentRegistrationFormController studentRegistrationFormController =loader.getController();

                studentRegistrationFormController.initialize(user, borderPane);
                borderPane.setCenter(rootNode);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnStudentRemoveOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/lk/ijse/drivingSchool/view/studentremove_form.fxml"));
        try {
            Parent rootNode = loader.load();
            StudentRemoveFormController studentRemoveFormController = loader.getController();
            studentRemoveFormController.initializer(borderPane);
            borderPane.setCenter(rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
