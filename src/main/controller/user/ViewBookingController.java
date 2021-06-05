package main.controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.controller.DataModel;
import main.model.user.ViewBookingModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewBookingController implements Initializable {
    private ViewBookingModel vbm = new ViewBookingModel();
    private DataModel dataModel = new DataModel();

    @FXML
    private Label lbDate;
    @FXML
    private Label lbSeat;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnCancelBooking;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(vbm.ViewBooking(dataModel.emp.getUserName()).equals("")){
                lbDate.setText(dataModel.desk.getDate());
                lbSeat.setText(dataModel.desk.getSeatNum()+"");
            } else {
                dataModel.showDialogBox("View Booking", "You don't have any booking yet!");
                dataModel.closeScene(btnBack);
                dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void back(ActionEvent event) throws Exception {
        dataModel.closeScene(btnBack);
        dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
    }

    public void CancelBooking(ActionEvent event) {
        vbm.CancelBooking(dataModel.emp.getUserName());
        dataModel.showDialogBox("View Booking", "Your booking has been canceled!");
    }
}
