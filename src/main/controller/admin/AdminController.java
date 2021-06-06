package main.controller.admin;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Main;
import main.controller.DataModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private Label lbAdmin;
    @FXML
    private Button btnSignOut;
    @FXML
    private Button btnManagement;
    @FXML
    private Button btnViewBooking;
    @FXML
    private Button btnReport;
    @FXML
    private Button btnReleaseBooking;
    private DataModel dataModel = new DataModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbAdmin.setText(StringUtils.capitalize(dataModel.emp.getFirstName()) + " " + StringUtils.capitalize(dataModel.emp.getLastName()));
    }


    public void viewBooking(ActionEvent event) throws Exception {
        dataModel.closeScene(btnViewBooking);
        dataModel.showScene("../ui/Login.fxml", "Login");
    }

    public void signOut(ActionEvent event) throws Exception {
        Main.isLogin = false;
        dataModel.closeScene(btnSignOut);
        dataModel.showScene("../ui/Login.fxml", "Login");
    }

    public void report(ActionEvent event) throws Exception {
        dataModel.closeScene(btnReport);
        dataModel.showScene("../ui/Login.fxml", "Login");
    }

    public void releaseBooking(ActionEvent event) throws Exception {
        dataModel.closeScene(btnReleaseBooking);
        dataModel.showScene("../ui/AdminReleaseBooking.fxml", "Release Booking");
    }

    public void management(ActionEvent event) throws Exception {
        dataModel.closeScene(btnManagement);
        dataModel.showScene("../ui/AdminManagement.fxml", "Manage All Users Account");
    }
}
