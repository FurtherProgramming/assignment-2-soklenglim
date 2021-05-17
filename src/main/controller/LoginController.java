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
            isConnected.setTextFill(Color.BLUE);
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
                if (h.emp.getAdmin()==true) {
                    h.closeScene(btnLogin);
                    h.showScene("../ui/AdminProfile.fxml", "Admin Profile");

                } else {
                    h.closeScene(btnLogin);
                    h.showScene("../ui/UserProfile.fxml", "User Profile");
                }
            }else if(txtUsername.getText().equals("") || txtPassword.getText().equals("")) {
                isConnected.setText("Please input username and password");
                isConnected.setTextFill(Color.RED);
            }else{
                isConnected.setText("Username and password is incorrect");
                isConnected.setTextFill(Color.RED);
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