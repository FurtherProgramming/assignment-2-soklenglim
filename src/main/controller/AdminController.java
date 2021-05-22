package main.controller;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private Label lbAdmin;
    @FXML
    private Button btnSignOut;
    private DataModel dataModel = new DataModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbAdmin.setText(StringUtils.capitalize(dataModel.emp.getFirstName()) + " " + StringUtils.capitalize(dataModel.emp.getLastName()));

    }


    public void viewBooking(ActionEvent event) {
    }

    public void signOut(ActionEvent event) throws Exception {
        dataModel.closeScene(btnSignOut);
        dataModel.showScene("../ui/login.fxml", "Login");
    }

    public void report(ActionEvent event) {
    }

    public void releaseBooking(ActionEvent event) {
    }

    public void management(ActionEvent event) {
    }
}
