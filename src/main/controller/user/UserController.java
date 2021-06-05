package main.controller.user;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.Main;
import main.controller.DataModel;
import main.object.Employee;

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

    private DataModel dataModel = new DataModel();

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
        dataModel.showScene("../ui/ManageAccount.fxml", "Manage Account");
    }

    public void View(ActionEvent event) throws Exception {
        dataModel.closeScene(btnUpdate);
        dataModel.showScene("../ui/ViewBookingStatus.fxml", "View Booking");
    }

    public void Book(ActionEvent event) throws Exception {
        dataModel.closeScene(btnBook);
        dataModel.showScene("../ui/SeatSelection.fxml", "Seat Selection");
    }


}