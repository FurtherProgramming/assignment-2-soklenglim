package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class UserController implements Initializable {

    @FXML
    private Label txtUsername;
    public static String username = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(username.equals(""))
            txtUsername.setText("Username");
        else
            txtUsername.setText(username);
    }

}