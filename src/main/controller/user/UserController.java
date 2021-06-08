package main.controller.user;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Main;
import main.controller.DataModel;
import main.model.user.ViewBookingModel;
import main.object.Desk;
import main.object.Employee;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


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

    private DataModel dataModel = new DataModel();
    private ViewBookingModel vbm = new ViewBookingModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelUsername.setText(StringUtils.capitalize(dataModel.emp.getFirstName()) + " " + StringUtils.capitalize(dataModel.emp.getLastName()));
    }

    @FXML
    private void SignOut(ActionEvent event) throws Exception {
        dataModel.emp = new Employee();
        Main.isLogin = false;
        dataModel.closeScene(btnSignOut);
        dataModel.showScene("../ui/Login.fxml", "Login");
    }

    public void Update(ActionEvent event) throws Exception {
        dataModel.closeScene(btnUpdate);
        dataModel.showScene("../ui/ViewAccount.fxml", "Manage Account");
    }

    public void View(ActionEvent event) throws Exception {
        if (!checkStatus(vbm.ViewBooking(dataModel.emp.getUserName()))) {
            dataModel.closeScene(btnView);
            dataModel.showScene("../ui/ViewBookingStatus.fxml", "View Booking");
        } else {
            dataModel.showDialogBox("View Booking", "You don't have any booking yet!");
        }

    }

    public void Book(ActionEvent event) throws Exception {
        if (checkStatus(vbm.ViewBooking(dataModel.emp.getUserName()))) {
            dataModel.closeScene(btnBook);
            dataModel.showScene("../ui/SeatSelection.fxml", "Seat Selection");
        } else {
            dataModel.showDialogBox("Seat Selection", "You already book a seat!");
        }
    }

    private Boolean checkStatus(ArrayList<Desk> desks) {
        List<Desk> desk = desks.stream().filter(d -> d.getStatus().equals("pending")).collect(Collectors.toList());
        if (desk.size() == 0)
            return true;
        else
            return false;
    }


}