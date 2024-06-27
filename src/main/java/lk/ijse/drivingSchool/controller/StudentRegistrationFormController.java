package lk.ijse.drivingSchool.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import lk.ijse.drivingSchool.API.API;
import lk.ijse.drivingSchool.Db.DbConnnection;


import lk.ijse.drivingSchool.dao.custom.UserDAO;
import lk.ijse.drivingSchool.dao.custom.VehicleClassDAO;
import lk.ijse.drivingSchool.dao.custom.impl.UserDAOImpl;
import lk.ijse.drivingSchool.dao.custom.impl.VehicleClassDAOImpl;
import lk.ijse.drivingSchool.dto.StudentDTO;
import lk.ijse.drivingSchool.entity.Student;
import lk.ijse.drivingSchool.entity.User;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class StudentRegistrationFormController {


    @FXML
    private Label lblContactNoErrorMsg;

    @FXML
    private Label lblDateErrorMsg;



    @FXML
    private Label lblNICErrorMsg;

    ;

    @FXML
    private JFXComboBox<String> cmbVehicleClass;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtBloodGroup;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtWeight;

    @FXML
    private TextField txtInitialPayment;

    private String vehicleClassId;
    private String userId;
    public BorderPane borderPane;
    public ResultSet resultSet;
    public User user;
    UserDAO userDAO = new UserDAOImpl();
    VehicleClassDAO vehicleClassDAO = new VehicleClassDAOImpl();

    @FXML
    public void initialize(User user, BorderPane borderPane){
        getVehicleClass();
        this.user = user;
        setUserId();
        this.borderPane = borderPane;

        restrictNumeric(txtWeight);
        restrictNumeric(txtHeight);
        restrictNumeric(txtInitialPayment);


    }

    private void setUserId() {
        try {
            userId = userDAO.getUserId(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String NIC = txtNIC.getText();
        String firstname = txtFirstName.getText();
        String lastname = txtLastName.getText();
        String height = txtHeight.getText();
        String weight = txtWeight.getText();
        String dateOfBirth = txtDOB.getText();
        String bloodGroup = txtBloodGroup.getText();
        String contactNo = txtContactNo.getText();
        String address = txtAddress.getText();
        String teleNo = txtContactNo.getText();

        String initialPayment = txtInitialPayment.getText();

        Pattern phonePattern = Pattern.compile("^\\+94\\d{9}$");
        Pattern datePattern = Pattern.compile("^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[01])$");
        Pattern NICPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");

        if (isValidInput(NICPattern,phonePattern,datePattern)) {

            StudentDTO studentDTO = new StudentDTO(NIC, firstname, lastname, height, weight, dateOfBirth, bloodGroup, contactNo, address, userId, vehicleClassId);

            boolean isSaved = false;
            try {
                isSaved = StudentRegistrationRepo.studentRegistration(studentDTO, initialPayment, userId, vehicleClassId);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Account Created!").show();
                    sendSMS(teleNo,smsMessage(firstname,lastname,initialPayment,vehicleClassId,userId));
                    alertForJasperReport();
                    txtNIC.setText("");
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtHeight.setText("");
                    txtWeight.setText("");
                    txtDOB.setText("");
                    txtBloodGroup.setText("");
                    txtContactNo.setText("+94");
                    txtAddress.setText("");


                } else {
                    new Alert(Alert.AlertType.ERROR, "Account not Created!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
            }
        }


    }

    private void alertForJasperReport() {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Do you want to print a Student Registration Receipt?", yes, no).showAndWait();

        if(type.orElse(no) == yes) {
            printJasperReport();
                 }
    }

    private void printJasperReport() {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Reports/StudentRegistrationReport.jrxml");
            JRDesignQuery query = new JRDesignQuery();
            query.setText("SELECT \n" +
                    "    s.first_name AS student_first_name,\n" +
                    "    s.last_name AS student_last_name,\n" +
                    "    vc.vehicle_class,\n" +
                    "    vc.fee,\n" +
                    "    p.amount AS payment_amount,\n" +
                    "    p.date AS payment_date,\n" +
                    "    (vc.fee - p.amount) AS remaining_fee\n" +
                    "FROM \n" +
                    "    student s\n" +
                    "JOIN \n" +
                    "    vehicleClass vc ON s.vehicle_class_id = vc.vehicle_class_id\n" +
                    "JOIN \n" +
                    "    payment p ON s.student_id = p.student_id\n" +
                    "WHERE \n" +
                    "    s.student_id = (SELECT MAX(student_id) FROM student);");
            jasperDesign.setQuery(query);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnnection.getInstance().getConnection());

            JFrame frame = new JFrame("Jasper Report Viewer");
            JRViewer viewer = new JRViewer(jasperPrint);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(viewer);
            frame.setSize(new Dimension(1200, 800));
            frame.setVisible(true);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean isValidInput(Pattern nicPattern, Pattern phonePattern, Pattern datePattern) {
        boolean isValid = true;
        if (!nicPattern.matcher(txtNIC.getText()).matches()){
            lblNICErrorMsg.setText("Invalid NIC format");
            addError(txtNIC);
            isValid =false;
        }else {
            removeError(txtNIC);
        }
        if (!phonePattern.matcher(txtContactNo.getText()).matches()){
            lblContactNoErrorMsg.setText("Invalid Contact Number");
            addError(txtContactNo);
            isValid =false;
        }else {
            removeError(txtContactNo);
        }
        if (!datePattern.matcher(txtDOB.getText()).matches()){
            lblDateErrorMsg.setText("Invalid Date Format");
            addError(txtDOB);
            isValid = false;
        }else {
            removeError(txtDOB);
        }
        return isValid;
    }


    private void sendSMS(String teleNo, String message) {
        API api = new API();
        api.sms("mahesha_dinushan","Mahesha@123",teleNo,message);
    }

    private String smsMessage(String firstname, String lastname, String initialPayment, String vehicleClassId, String userId) throws SQLException {
        Double remaining = Double.parseDouble(vehicleClassDAO.getFee(vehicleClassId))-Double.parseDouble(initialPayment);
        String msg ="Sri Vijaya Driving School! "+firstname+" "+lastname+" Vehicle Class: "+vehicleClassDAO.getVehicleClass(vehicleClassId)+" total fee Rs."+vehicleClassDAO.getFee(vehicleClassId)+" -"+resultSet.getString("first_name")+"-";
        return msg;
    }


    public void getVehicleClass() {
            ObservableList<String> obList = FXCollections.observableArrayList();

            try {
                List<String> vehicleClassList = vehicleClassDAO.getAllVehicleClass();
                for (String vehicleClass : vehicleClassList) {
                    obList.add(vehicleClass);
                }
                cmbVehicleClass.setItems(obList);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

    @FXML
    void cmbVehicleClassOnAction(ActionEvent event) {
        String vehicleClass = cmbVehicleClass.getValue();
        vehicleClassId =vehicleClassDAO.getId(vehicleClass);

    }

    @FXML
    void txtContactNoKeyReleasedOnAction(KeyEvent event) {
        Pattern phonePattern = Pattern.compile("^\\+94\\d{9}$");
        if (!phonePattern.matcher(txtContactNo.getText()).matches()){
            addError(txtContactNo);
        }else {
            removeError(txtContactNo);
        }

    }

    @FXML
    void txtDOBReleasedOnAction(KeyEvent event) {
        Pattern datePattern = Pattern.compile("^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[01])$");
        if (!datePattern.matcher(txtDOB.getText()).matches()){
            addError(txtDOB);
        }else {
            removeError(txtDOB);
        }

    }

    @FXML
    void txtHeightKeyOnReleased(KeyEvent event) {
        restrictNumeric(txtHeight);

    }

    @FXML
    void txtInitialPaymentReleaseOnAction(KeyEvent event) {
        restrictNumeric(txtInitialPayment);

    }

    @FXML
    void txtNICReleasedOnAction(KeyEvent event) {
        Pattern NICPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        if (!NICPattern.matcher(txtNIC.getText()).matches()){
            addError(txtNIC);
        }else {
            removeError(txtNIC);
        }

    }

    @FXML
    void txtWeightReleasedOnAction(KeyEvent event) {
        restrictNumeric(txtWeight);

    }

    private void restrictNumeric(TextField textField){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                textField.setText("");
            } else {
                if (!newValue.matches("\\d*")) {
                    textField.setText(oldValue);
                }
            }
        });
    }

    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green");

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
    }

}
