package main.controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.controller.DataModel;
import main.model.user.ConfirmSeatModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmSeatController implements Initializable {
    private DataModel dataModel = new DataModel();
    private ConfirmSeatModel bcm = new ConfirmSeatModel();
    public static boolean editBooking = false;
    public static int deskId;

    // FXML variables
    @FXML
    public Label lbSeat;
    @FXML
    public Label lbDate;
    @FXML
    public Button btnBook;
    @FXML
    private Button btnBack;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbDate.setText(dataModel.desk.getDate());
        lbSeat.setText(dataModel.desk.getSeatNum()+"");
    }
    public void back(ActionEvent event) throws Exception {
        dataModel.desk.setDate("");
        dataModel.desk.setSeatNum(0);
        if(editBooking == false) {
            dataModel.closeScene(btnBack);
            dataModel.showScene("../ui/SeatSelection.fxml", "Seat Selection");
        } else {
            dataModel.closeScene(btnBook);
            dataModel.showScene("../ui/ViewBookingStatus.fxml", "View Booking");
            editBooking = false;
        }
    }

    public void book(ActionEvent event) throws Exception {
        dataModel.desk.setStatus("pending");
        if(editBooking == false) {
            if (bcm.addBookingDesk(dataModel.emp.getUserName(), dataModel.desk.getStatus(), dataModel.desk.getDate(), dataModel.desk.getSeatNum())) {
                dataModel.showDialogBox("Booking Completed!", "Your Booking on desk has been completed!");
                dataModel.closeScene(btnBook);
                dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
            } else {
                dataModel.showDialogBox("Booking Failed!", "Please try again!");
            }
        } else {
            if(bcm.updateBooking(deskId, dataModel.desk.getSeatNum(), dataModel.desk.getDate())){
                dataModel.showDialogBox("Seat Modified!", "Your booking has been updated!");
                dataModel.closeScene(btnBook);
                dataModel.showScene("../ui/ViewBookingStatus.fxml", "View Booking");
                editBooking = false;
            }
        }

    }
}
