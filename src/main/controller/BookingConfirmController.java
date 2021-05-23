package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.model.BookingConfirmModel;

import java.net.URL;
import java.util.ResourceBundle;

public class BookingConfirmController implements Initializable {
    @FXML
    public Label lbSeat;
    @FXML
    public Label lbDate;
    @FXML
    public Button btnBook;
    @FXML
    private Button btnBack;
    private DataModel dataModel = new DataModel();
    private BookingConfirmModel bcm = new BookingConfirmModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbDate.setText(dataModel.desk.getDate());
        lbSeat.setText(dataModel.desk.getSeatNum()+"");
    }
    public void back(ActionEvent event) throws Exception {
        dataModel.desk.setDate("");
        dataModel.desk.setSeatNum(0);
        dataModel.closeScene(btnBack);
        dataModel.showScene("../ui/SeatSelection.fxml", "Seat Selection");
    }

    public void book(ActionEvent event) throws Exception {
        if(bcm.addBookingDesk(dataModel.emp.getUserName(), dataModel.desk.getSeatNum(), dataModel.desk.getDate())){
            dataModel.showDialogBox("Booking Completed!", "Your Booking on desk has been completed!");
            dataModel.closeScene(btnBook);
            dataModel.showScene("../ui/UserProfile.fxml", "User Profile");

        } else {
            dataModel.showDialogBox("Booking Failed!", "Please try again!");
            dataModel.closeScene(btnBook);
            dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
        }
    }
}
