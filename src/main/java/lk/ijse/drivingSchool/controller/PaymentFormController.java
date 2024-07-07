package lk.ijse.drivingSchool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.Impl.PaymentBOImpl;
import lk.ijse.drivingSchool.bo.custom.PaymentBO;
import lk.ijse.drivingSchool.entity.Payment;
import lk.ijse.drivingSchool.entity.User;

import lk.ijse.drivingSchool.view.tdm.PaymentTm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentFormController {
    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableView<PaymentTm> tblPayment;
    private ObservableList<PaymentTm> paymentTmObservableList = FXCollections.observableArrayList();

    public BorderPane borderPane;
    User user;
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    @FXML
    public void initialize(User user, BorderPane borderPane) {
        setValueFactory();
        loadPaymentTable();
        this.borderPane= borderPane;
        this.user = user;
    }

    private void loadPaymentTable() {
        try{
        ArrayList<Payment> payments = paymentBO.getAll();

            for (Payment p: payments){
                String id = paymentBO.getPaymentId(p.getAmount(),p.getDate(),p.getStudentId());
                String date = p.getDate();
                String studentName = paymentBO.getName(p.getStudentId());
                String amount = p.getAmount();

                PaymentTm paymentTm = new PaymentTm(id,date,studentName,amount);
                paymentTmObservableList.add(paymentTm);
            }
            tblPayment.setItems(paymentTmObservableList);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void setValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    @FXML
    void btnAddPaymentOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addpayment_form.fxml"));
        Parent rootNode = loader.load();
        AddPaymentFormController addPaymentFormController = loader.getController();
        addPaymentFormController.initialize(user);

        borderPane.setCenter(rootNode);

    }

    @FXML
    void btnPaymentRemoveOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/removepayment_form.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
