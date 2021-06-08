package main.controller.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import main.controller.DataModel;
import main.object.Desk;
import main.model.user.SeatSelectionModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class SeatSelectionController implements Initializable {
    private SeatSelectionModel ssm = new SeatSelectionModel();
    private DataModel dataModel = new DataModel();
    private int previousSelected;
    private String strDateFromDesk = "";
    public static boolean isAdmin = false;

    // FXML variables
    @FXML
    public DatePicker datePicker;
    @FXML
    public Button btnBack;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private final Button[] btns = new Button[15];
    @FXML
    private Button btnBook;
    @FXML
    private Label lbAction;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePicker.setDayCellFactory(ssm.getDayCellFactory());
        datePicker.setConverter(ssm.converter);
        displayAllSeat();
        lbAction.setVisible(false);
        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate date = datePicker.getValue();
                DateTimeFormatter dateFormatter =
                        DateTimeFormatter.ofPattern("dd/MM/yyyy");
                strDateFromDesk = dateFormatter.format(date);
                try {
                    dataModel.desks.clear();
                    ssm.checkDesk(strDateFromDesk);
                    previousSelected = 20;
                    displayAllSeat();
                    lbAction.setText("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }


    private void displayAllSeat() {
        // setup desk
        for (int i = 0; i < btns.length; i++) {
            btns[i] = new Button();
            btns[i].setText("" + (i + 1));
            if (i == 0) {
                btns[i].setLayoutX(50);
                btns[i].setLayoutY(70);
            }
            if (i == 1) {
                btns[i].setLayoutX(170);
                btns[i].setLayoutY(70);
            }
            if (i == 2) {
                btns[i].setLayoutX(190);
                btns[i].setLayoutY(150);
                btns[i].setRotate(90);
            }
            if (i == 3) {
                btns[i].setLayoutX(190);
                btns[i].setLayoutY(250);
                btns[i].setRotate(90);
            }
            if (i == 4) {
                btns[i].setLayoutX(270);
                btns[i].setLayoutY(340);
            }
            if (i == 5) {
                btns[i].setLayoutX(380);
                btns[i].setLayoutY(340);
            }
            if (i == 6) {
                btns[i].setLayoutX(500);
                btns[i].setLayoutY(350);
                btns[i].setRotate(90);
            }
            if (i == 7) {
                btns[i].setLayoutX(500);
                btns[i].setLayoutY(440);
                btns[i].setRotate(90);
            }
            if (i == 8) {
                btns[i].setLayoutX(380);
                btns[i].setLayoutY(460);
            }
            if (i == 9) {
                btns[i].setLayoutX(270);
                btns[i].setLayoutY(460);
            }
            if (i == 10) {
                btns[i].setLayoutX(130);
                btns[i].setLayoutY(460);
            }
            if (i == 11) {
                btns[i].setLayoutX(35);
                btns[i].setLayoutY(440);
                btns[i].setRotate(90);
            }
            if (i == 12) {
                btns[i].setLayoutX(35);
                btns[i].setLayoutY(340);
                btns[i].setRotate(90);
            }
            if (i == 13) {
                btns[i].setLayoutX(35);
                btns[i].setLayoutY(250);
                btns[i].setRotate(90);
            }
            if (i == 14) {
                btns[i].setLayoutX(35);
                btns[i].setLayoutY(150);
                btns[i].setRotate(90);
            }
            btns[i].setPrefWidth(80);
            btns[i].setPrefHeight(40);

            String status = setupDesk(dataModel.desks, i);
            ArrayList<String> lock = new ArrayList<>();
            if (status.equals("unavailable") || status.equals("pending")) {
                btns[i].setStyle("-fx-background-color: red;");
            } else {
                if (status.equals("lock")) {
                    btns[i].setStyle("-fx-background-color: orange;");
                    lock.add(btns[i].getText());
                } else {
                    btns[i].setStyle("-fx-background-color: #90ee90;");
                }
                int j = i;
                btns[i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        int disable = 20;
                        for (int k = 0; k < btns.length; k++) {
                            String status = setupDesk(dataModel.desks, k);
                            if (status.equals("unavailable") || status.equals("pending")) {
                                btns[k].setStyle("-fx-background-color: red;");
                                disable = k;
                            } else if (status.equals("lock")) {
                                if (!isAdmin) {
                                    disable = k;
                                }
                                btns[k].setStyle("-fx-background-color: orange;");
                            } else {
                                btns[k].setStyle("-fx-background-color: #90ee90;");
                            }
                        }
                        if (j == disable) {
                            if (previousSelected <= btns.length) {
                                if (isAdmin) {
                                    if (lock.contains(btns[previousSelected].getText()))
                                        btns[previousSelected].setStyle("-fx-background-color: #B59700;");
                                    else
                                        btns[previousSelected].setStyle("-fx-background-color: #017d0c;");
                                } else {
                                    btns[previousSelected].setStyle("-fx-background-color: #017d0c;");
                                }
                            }
                        } else {
                            previousSelected = j;
                            if (lock.contains(btns[previousSelected].getText())) {
                                btns[previousSelected].setStyle("-fx-background-color: #B59700;");
                                dataModel.desk.setStatus("lock");
                            } else {
                                btns[previousSelected].setStyle("-fx-background-color: #017d0c;");
                                dataModel.desk.setStatus("available");
                            }
                            lbAction.setText(btns[j].getText());
                        }
                        lock.clear();

                    }
                });
            }

            anchorPane.getChildren().add(btns[i]);
        }

    }

    private String setupDesk(ArrayList<Desk> desks, int seatNum) {
        List<Desk> desk = desks.stream().filter(d -> d.getSeatNum() - 1 == seatNum).collect(Collectors.toList());
        if (desk.size() == 0)
            return "available";
        else
            return desk.get(0).getStatus();
    }


    public void book(ActionEvent event) throws Exception {
        if (lbAction.getText().equals("") || strDateFromDesk.equals("")) {
            dataModel.showDialogBox("Error Message", "Please select a seat and date");
        } else {
            dataModel.desk.setDate(strDateFromDesk);
            dataModel.desk.setSeatNum(Integer.parseInt(lbAction.getText()));
            dataModel.closeScene(btnBook);
            dataModel.showScene("../ui/ConfirmSeat.fxml", "Confirmation");
        }
    }

    public void back(ActionEvent event) throws Exception {
        if (isAdmin == false) {
            dataModel.closeScene(btnBack);
            dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
        } else {
            dataModel.closeScene(btnBack);
            dataModel.showScene("../ui/AdminProfile.fxml", "Admin Profile");
            isAdmin = false;
        }
    }
}
