package main.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import main.model.LoginModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();
    public Button btnReset;

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

    private DataModel dataModel = new DataModel();

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected");
            isConnected.setTextFill(Color.BLUE);
        } else {
            isConnected.setText("Not Connected");
        }
    }

    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event) throws Exception {
        try {
            dataModel.emp = loginModel.isLogin(txtUsername.getText(), txtPassword.getText());
            if (dataModel.emp.getUserName() != null && dataModel.emp.getRole() != null) {
                // Login Successful
                isConnected.setText("Logged in successfully");
                if (dataModel.emp.getAdmin() == true) {
                    dataModel.closeScene(btnLogin);
                    dataModel.showScene("../ui/AdminProfile.fxml", "Admin Profile");

                } else {
                    dataModel.closeScene(btnLogin);
                    dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
                }
            } else if (txtUsername.getText().equals("") || txtPassword.getText().equals("")) {
                dataModel.showDialogBox("Login Error", "Please input username and password");
            } else {
                dataModel.showDialogBox("Login Error", "Username and password is incorrect");
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void Register(ActionEvent event) throws Exception {
        dataModel.closeScene(btnRegister);
        dataModel.showScene("../ui/Register.fxml", "Register");
    }


    public void resetPassword(ActionEvent event) throws Exception {
        dataModel.closeScene(btnReset);
        dataModel.showScene("../ui/ForgetPassword.fxml", "Reset Password");
    }
}