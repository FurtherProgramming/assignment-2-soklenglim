package main.controller.admin;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Main;
import main.controller.DataModel;
import main.controller.user.ConfirmSeatController;
import main.controller.user.SeatSelectionController;
import main.model.admin.AdminModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private DataModel dataModel = new DataModel();
    private AdminModel am = new AdminModel();

    // FXML variables
    @FXML
    private Label lbAdmin;
    @FXML
    private Button btnSignOut;
    @FXML
    private Button btnManagement;
    @FXML
    private Button btnViewDesk;
    @FXML
    private Button btnReleaseBooking;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbAdmin.setText(StringUtils.capitalize(dataModel.emp.getFirstName()) + " " + StringUtils.capitalize(dataModel.emp.getLastName()));
    }


    public void viewDesk(ActionEvent event) throws Exception {
        dataModel.closeScene(btnViewDesk);
        SeatSelectionController.isAdmin = true;
        ConfirmSeatController.isAdmin = true;
        dataModel.showScene("../ui/SeatSelection.fxml", "Seat Selection");
    }

    public void signOut(ActionEvent event) throws Exception {
        Main.isLogin = false;
        dataModel.closeScene(btnSignOut);
        dataModel.showScene("../ui/Login.fxml", "Login");
    }

    public void report(ActionEvent event) throws Exception {
        am.generateEmpReport();
        am.generateBookingReport();
        dataModel.showDialogBox("Report Generated", "All Employee and Desk report has been generated!");
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
