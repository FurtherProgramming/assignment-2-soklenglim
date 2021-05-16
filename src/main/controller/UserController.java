package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserController implements Initializable {

    @FXML
    private Label labelUsername;
    @FXML
    private Button btnSignOut;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnView;
    @FXML
    private Button btnBook;

    public static String username = "";
    private Helper h = new Helper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(username.equals(""))
            labelUsername.setText("Username");
        else
            labelUsername.setText(username);
    }

    @FXML
    private void SignOut(ActionEvent event) throws Exception {
        username = "";
        h.closeScene(btnSignOut);
        h.showScene("../ui/login.fxml", "Login");
    }

    public void Update(ActionEvent event) throws Exception {
        h.closeScene(btnUpdate);
        h.showScene("../ui/ManageAccount.fxml", "Manage Account");
    }

    public void View(ActionEvent event) {
    }

    public void Book(ActionEvent event) {
    }


}