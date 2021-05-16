package main.controller;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.model.Employee;
import main.model.LoginModel;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();

    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;

    private Helper h = new Helper();

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }
    }
    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event) throws Exception {
        try {
            h.emp = loginModel.isLogin(txtUsername.getText(),txtPassword.getText());
            if (h.emp.getUserName() != null && h.emp.getRole() != null){
                // Login Successful
                isConnected.setText("Logged in successfully");
                if (h.emp.getRole().equals("Admin")) {
                    h.closeScene(btnLogin);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/AdminProfile.fxml"));
                    Parent root = loader.load();
                    AdminController adminController = loader.getController();
  //                  adminController.setUserName(emp.getUserName());

                } else {
                    Stage stage = (Stage) btnRegister.getScene().getWindow();
                    stage.close();
                    h.showScene("../ui/UserProfile.fxml", "User Profile");
                }
            }else{
                isConnected.setText("username and password is incorrect");
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void Register(ActionEvent event) throws Exception {
        h.closeScene(btnRegister);
        h.showScene("../ui/Register.fxml", "Register");
    }


}