package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
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

    private Stage stage;
    private Scene scene;
    private Parent root;




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
            if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())){
                // Login Successful
                isConnected.setText("Logged in successfully");
                root = FXMLLoader.load(getClass().getResource("../ui/UserProfile.fxml"));
                scene = new Scene(root);
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }else{
                isConnected.setText("username and password is incorrect");
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void Register(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("../ui/Register.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }




}
