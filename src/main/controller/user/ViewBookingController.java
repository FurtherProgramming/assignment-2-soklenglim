package main.controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import main.controller.DataModel;
import main.model.user.ViewBookingModel;
import main.object.Desk;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ViewBookingController implements Initializable {
    private ViewBookingModel vbm = new ViewBookingModel();
    private DataModel dataModel = new DataModel();


    @FXML
    private Button btnBack;
    @FXML
    private Button btnCancelBooking;
    @FXML
    private AnchorPane anchorPane;
    private TableView table = new TableView();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            vbm.ViewBooking(dataModel.emp.getUserName());
            createTable();
            addButtonEdit();
            addButtonCheckIn();
            addButtonCancel();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void createTable() throws SQLException {
        TableColumn<String, Desk> columnUsername = new TableColumn<>("Username");
        TableColumn<Integer, Desk> columnSeatNum = new TableColumn<>("Seat Number");
        TableColumn<String, Desk> columnDate = new TableColumn<>("Date");
        TableColumn<String, Desk> columnStatus = new TableColumn<>("Status");
        TableColumn<String, Desk> columnDateOfBooking = new TableColumn<>("Date of Booking");
        columnUsername.setCellValueFactory(new PropertyValueFactory<>("empUsername"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnSeatNum.setCellValueFactory(new PropertyValueFactory<>("seatNum"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnDateOfBooking.setCellValueFactory(new PropertyValueFactory<>("currentDate"));


        table.getColumns().addAll(columnUsername,columnSeatNum,columnDate,columnStatus,columnDateOfBooking);

        for(Desk desk : vbm.ViewBooking(dataModel.emp.getUserName())){
            table.getItems().add(desk);
        }

        table.setLayoutX(30);
        table.setLayoutY(90);
        table.setPrefHeight(300);
        table.setPrefWidth(610);

        anchorPane.getChildren().add(table);
    }

    private void addButtonEdit() {
        TableColumn<Desk, Void> colBtnEdit = new TableColumn("");
        Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>> cellFactory = new Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>>() {
            @Override
            public TableCell<Desk, Void> call(final TableColumn<Desk, Void> param) {
                final TableCell<Desk, Void> cell = new TableCell<Desk, Void>() {
                    private final Button btn = new Button("Edit");
                    {
                        btn.setId("btnLogin");
                        btn.setTextFill(Color.WHITE);
                        btn.setOnAction((ActionEvent event) -> {
                            Desk desk = getTableView().getItems().get(getIndex());
                            long timeDiff = dataModel.TimeValidation(desk.getCurrentDate(), "hours");
                            if(timeDiff < -48){
                                dataModel.showDialogBox("View Booking", "You cannot change booking after 48h!");
                            } else {
                                if(desk.getStatus().equals("pending")) {
                                    ConfirmSeatController.editBooking = true;
                                    ConfirmSeatController.deskId = desk.getDeskId();
                                    dataModel.closeScene(btn);
                                    try {
                                        dataModel.showScene("../ui/SeatSelection.fxml", "Seat Selection");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    dataModel.showDialogBox("View Booking", "Your status is not pending!");
                                }
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtnEdit.setCellFactory(cellFactory);
        table.getColumns().add(colBtnEdit);
    }
    private void addButtonCheckIn() {
        TableColumn<Desk, Void> colBtnCheckIn = new TableColumn("");
        Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>> cellFactory = new Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>>() {
            @Override
            public TableCell<Desk, Void> call(final TableColumn<Desk, Void> param) {
                final TableCell<Desk, Void> cell = new TableCell<Desk, Void>() {
                    private final Button btn = new Button("Check In");
                    {
                        btn.setId("btnLogin");
                        btn.setTextFill(Color.WHITE);
                        btn.setOnAction((ActionEvent event) -> {
                            Desk desk = getTableView().getItems().get(getIndex());
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date bookingDate = dateFormat.parse(desk.getDate());
                                Date currentTime = new Date(System.currentTimeMillis());
                                Date now = dateFormat.parse(dateFormat.format(currentTime));
                                long diff = bookingDate.getTime() - now.getTime();
                                long diffDays = diff / (24 * 60 * 60 * 1000);
                                if (diffDays == 0) {
                                    System.out.println(desk.getDeskId());
                                    if(desk.getStatus().equals("approve")){
                                        if(vbm.CheckIn(desk.getDeskId())) {
                                            dataModel.showDialogBox("Checking in!", "Your check-in has been successful.");
                                        } else {
                                            dataModel.showDialogBox("Checking in!", "Fail to check-in, Please try again!");
                                        }
                                    } else if(desk.getStatus().equals("check in")) {
                                        dataModel.showDialogBox("Checking in!", "You already check in!");
                                    } else {
                                        dataModel.showDialogBox("Checking in!", "Your seat has not been approved yet.");
                                    }
                                } else {
                                    dataModel.showDialogBox("Checking in!", "You cannot check-in before the booking date.");
                                }
                            }  catch (Exception e) {
                                e.printStackTrace();
                            }



                            table.getItems().clear();

                            try {
                                for(Desk desks : vbm.ViewBooking(dataModel.emp.getUserName())){
                                    table.getItems().add(desks);
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtnCheckIn.setCellFactory(cellFactory);
        table.getColumns().add(colBtnCheckIn);
    }
    private void addButtonCancel() {
        TableColumn<Desk, Void> colBtnCancel = new TableColumn("");
        Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>> cellFactory = new Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>>() {
            @Override
            public TableCell<Desk, Void> call(final TableColumn<Desk, Void> param) {
                final TableCell<Desk, Void> cell = new TableCell<Desk, Void>() {
                    private final Button btn = new Button("Cancel");
                    {
                        btn.setId("btnSignOut");
                        btn.setTextFill(Color.WHITE);
                        btn.setOnAction((ActionEvent event) -> {
                            Desk desk = getTableView().getItems().get(getIndex());
                            if(!desk.getStatus().equals("check in")){
                                if(vbm.CancelBooking(desk.getDeskId())) {
                                    dataModel.showDialogBox("Cancel Booking!", "Your booking has been cancel.");
                                } else {
                                    dataModel.showDialogBox("Cancel Booking!", "Fail to cancel, Please try again!");
                                }
                            } else {
                                dataModel.showDialogBox("Cancel Booking!", "Cannot cancel the booking that is already check in.");
                            }


                            table.getItems().clear();

                            try {
                                for(Desk desks : vbm.ViewBooking(dataModel.emp.getUserName())){
                                    table.getItems().add(desks);
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtnCancel.setCellFactory(cellFactory);
        table.getColumns().add(colBtnCancel);
    }
    public void back(ActionEvent event) throws Exception {
        dataModel.closeScene(btnBack);
        dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
    }


}
