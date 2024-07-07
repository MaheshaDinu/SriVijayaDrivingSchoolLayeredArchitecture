package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.Impl.RemovePaymentBOImpl;
import lk.ijse.drivingSchool.bo.custom.RemovePaymentBO;

import java.sql.SQLException;
import java.util.Optional;

public class RemovePaymentFormController {
    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtNIC;
    RemovePaymentBO removePaymentBO = (RemovePaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REMOVE_PAYMENT);

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        try {
            String date = txtDate.getText();
            String studentId = removePaymentBO.getId(txtNIC.getText());
            String amount = txtAmount.getText();
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Do you really want to remove this payment?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {

                boolean isRemoved = removePaymentBO.delete(date, studentId, amount);
                if (isRemoved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment Removed!").show();
                    txtDate.setText("");
                    txtNIC.setText("");
                    txtAmount.setText("");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Payment Not Removed!").show();
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }


    }
