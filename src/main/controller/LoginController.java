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

    private Stage stage;
    private Scene scene;

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
            Employee emp = loginModel.isLogin(txtUsername.getText(),txtPassword.getText());
            if (emp.getUserName() != null && emp.getRole() != null){
                // Login Successful
                isConnected.setText("Logged in successfully");
                if (emp.getRole().equals("Admin")) {
                    closeScene(btnLogin);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/AdminProfile.fxml"));
                    Parent root = loader.load();
                    AdminController adminController = loader.getController();
  //                  adminController.setUserName(emp.getUserName());
                    stage.setScene(new Scene(root));
                    stage.setTitle("User Profile");
                    stage.show();
                } else {
                    closeScene(btnLogin);
                    UserController.username = emp.getUserName();
                    showScene("../ui/UserProfile.fxml", "User Profile");

                }
            }else{
                isConnected.setText("username and password is incorrect");
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void Register(ActionEvent event) throws Exception {
        closeScene(btnRegister);
        showScene("../ui/Register.fxml", "Register");
    }

    public void showScene(String resource, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }

    public void closeScene(Button btn){
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}