package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import lk.ijse.drivingSchool.Db.DbConnnection;

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.AddPaymentBO;
import lk.ijse.drivingSchool.bo.custom.Impl.AddPaymentBOImpl;
import lk.ijse.drivingSchool.entity.Payment;
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
import java.time.LocalDate;
import java.util.Optional;

public class AddPaymentFormController {
    @FXML
    private Label lblAmount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblStudentName;

    @FXML
    private Label lblVehicleClass;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtStudentNIC;
    private String userId;
    private String studentId;
    private String vehicleClassId;
    private ResultSet studentResultSet;
    AddPaymentBO addPaymentBO = (AddPaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_PAYMENT);


    @FXML
    public void initialize(User user) {
        try {
            userId = addPaymentBO.getId(user.getContactNo());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        restrictNumeric(txtAmount);
    }

    @FXML
    void btnAddPaymentOnAction(ActionEvent event) {
        String amount =txtAmount.getText();
        String NIC = txtStudentNIC.getText();
        LocalDate now = LocalDate.now();
        String date = String.valueOf(now);
        try {
            Student student = addPaymentBO.get(NIC);
            if (student!=null){
                studentId = addPaymentBO.getStudentId(student.getNIC());
                vehicleClassId = student.getVehicleClassId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Payment payment = new Payment(amount,date,studentId,vehicleClassId,userId);

        try {
            boolean isSaved = addPaymentBO.save(payment);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Added!").show();
                alertForJasperReport();
                txtAmount.setText("");
                txtStudentNIC.setText("");
                lblAmount.setText("");
                lblDate.setText("");
                lblVehicleClass.setText("");
                lblStudentName.setText("");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
        }


    }

    private void alertForJasperReport() {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Do you want to print a Student Payment Receipt?", yes, no).showAndWait();

        if(type.orElse(no) == yes) {
            printJasperReport();
        }
    }

    private void printJasperReport() {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Reports/PaymentReport.jrxml");
            JRDesignQuery query = new JRDesignQuery();
            query.setText("SELECT \n" +
                    "    s.first_name AS student_first_name,\n" +
                    "    s.last_name AS student_last_name,\n" +
                    "    p.amount AS last_payment_amount,\n" +
                    "    SUM(p_all.amount) AS total_payments,\n" +
                    "    vc.fee AS vehicle_class_fee,\n" +
                    "    (vc.fee - SUM(p_all.amount)) AS remaining_fee\n" +
                    "FROM \n" +
                    "    payment p\n" +
                    "JOIN \n" +
                    "    student s ON p.student_id = s.student_id\n" +
                    "JOIN \n" +
                    "    vehicleClass vc ON s.vehicle_class_id = vc.vehicle_class_id\n" +
                    "JOIN \n" +
                    "    payment p_all ON s.student_id = p_all.student_id\n" +
                    "WHERE \n" +
                    "    p.payment_id = (SELECT MAX(payment_id) FROM payment)\n" +
                    "GROUP BY \n" +
                    "    s.student_id, s.first_name, s.last_name, p.amount, vc.fee;");
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

    @FXML
    void btnCheckOnAction(ActionEvent event) {
        String amount =txtAmount.getText();
        String NIC = txtStudentNIC.getText();
        LocalDate now = LocalDate.now();

        try {
            Student student = addPaymentBO.get(NIC);

            if(student!=null){
                String vehicleClass = addPaymentBO.getVehicleClass(student.getVehicleClassId());
                lblStudentName.setText(student.getFirstName()+" "+student.getLastName());
                lblVehicleClass.setText(vehicleClass);
                lblDate.setText(String.valueOf(now));
                lblAmount.setText("Rs."+amount+".00");

            } else {
                new Alert(Alert.AlertType.ERROR, "Student NIC Doesn't Exist!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void txtAmountReleasedOnAction(KeyEvent event) {
        restrictNumeric(txtAmount);

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


}
