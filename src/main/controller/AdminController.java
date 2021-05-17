package main.controller;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private Label lbAdmin;
    @FXML
    private Button btnSignOut;
    private Helper h = new Helper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbAdmin.setText(StringUtils.capitalize(h.emp.getFirstName()) + " " + StringUtils.capitalize(h.emp.getLastName()));

    }

    public void addAdmin(ActionEvent event) {
    }

    public void manageUser(ActionEvent event) {
    }

    public void viewBooking(ActionEvent event) {
    }

    public void signOut(ActionEvent event) throws Exception {
        h.closeScene(btnSignOut);
        h.showScene("../ui/login.fxml", "Login");
    }
}
