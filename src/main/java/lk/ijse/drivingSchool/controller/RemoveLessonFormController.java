package lk.ijse.drivingSchool.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import lk.ijse.drivingSchool.bo.custom.Impl.RemoveLessonBOImpl;
import lk.ijse.drivingSchool.bo.custom.RemoveLessonBO;

import java.sql.SQLException;
import java.util.Optional;

public class RemoveLessonFormController {
    @FXML
    private JFXComboBox<String> cmbTime;

    @FXML
    private TextField txtDate;
    private String time;
    RemoveLessonBO removeLessonBO = new RemoveLessonBOImpl();


    public void initialize(){
        setCmbTime();
    }

    private void setCmbTime() {
        cmbTime.setItems(FXCollections.observableArrayList("10.00 AM","03.00 PM"));
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        String date = txtDate.getText();
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Do you really want to remove this lesson?", yes, no).showAndWait();

        if(type.orElse(no) == yes) {
            try {
                boolean isRemoved = removeLessonBO.delete(date, time);
                if (isRemoved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Lesson Removed!").show();
                    txtDate.setText("");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Lesson Not Removed!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something Happened!").show();
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void cmbTimeOnAction(ActionEvent event) {
        time = cmbTime.getValue();

    }
}
