package lk.ijse.drivingSchool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lk.ijse.drivingSchool.bo.BOFactory;
import lk.ijse.drivingSchool.bo.custom.Impl.LoginBOImpl;
import lk.ijse.drivingSchool.bo.custom.LoginBO;
import lk.ijse.drivingSchool.entity.User;

import java.io.IOException;

import java.sql.SQLException;

public class LoginFormController {



    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);







    @FXML
    void btnLoginOnAction(ActionEvent event) {
            String userName = txtUsername.getText();
            String password = txtPassword.getText();

            try {

                User user = loginBO.userNameCheck(userName);
                if (user.getUserName().equals(userName)) {
                    String dbpw = user.getPassword();
                    if (dbpw.equals(password)) {



                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/navigation_form.fxml"));
                            Parent root = loader.load();
                            NavigationFormController navigationFormController = loader.getController();
                            navigationFormController.initialize(user);



                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setTitle("Sri Vijaya Driving School");
                            stage.centerOnScreen();
                            stage.show();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Password is Incorrect!").show();
                        txtPassword.setText("");

                    }
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "User Name not Found!").show();
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Sorry Something Went Wrong!");
                e.printStackTrace();
            }


    }



    @FXML
    void linkRegisterOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/registration_form.fxml"));

        Scene scene =new Scene(rootNode);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Create Account");
        stage.show();

    }
}
