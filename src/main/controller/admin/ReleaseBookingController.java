package main.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import main.model.DataModel;
import main.model.admin.ReleaseBookingModel;
import main.object.Desk;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReleaseBookingController implements Initializable {
    private ReleaseBookingModel rbm = new ReleaseBookingModel();
    private DataModel dataModel = new DataModel();
    private ArrayList<Desk> desks = new ArrayList<>();

    // FXML variables
    @FXML
    private TableView table = new TableView();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnAdminBack;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // query all booking and add buttons to table
        desks = rbm.displayPendingDesk();
        createTable();
        addButtonApprove();
        addButtonReject();
    }

    private void createTable() {
        TableColumn<String, Desk> columnUsername = new TableColumn<>("Username");
        TableColumn<Integer, Desk> columnSeatNum = new TableColumn<>("Seat");
        TableColumn<String, Desk> columnStatus = new TableColumn<>("Status");
        TableColumn<String, Desk> columnDate = new TableColumn<>("Date");
        TableColumn<String, Desk> columnDateOfBooking = new TableColumn<>("Date of Booking");
        columnUsername.setCellValueFactory(new PropertyValueFactory<>("empUsername"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnSeatNum.setCellValueFactory(new PropertyValueFactory<>("seatNum"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnDateOfBooking.setCellValueFactory(new PropertyValueFactory<>("currentDate"));
        table.getColumns().addAll(columnUsername, columnSeatNum, columnStatus, columnDate, columnDateOfBooking);

        for (Desk desk : desks) {
            table.getItems().add(desk);
        }

        table.setLayoutX(75);
        table.setLayoutY(90);
        table.setPrefHeight(300);
        table.setPrefWidth(555);
        anchorPane.getChildren().add(table);
    }

    private void addButtonApprove() {
        TableColumn<Desk, Void> colBtnApprove = new TableColumn("");
        Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>> cellFactory = new Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>>() {
            @Override
            public TableCell<Desk, Void> call(final TableColumn<Desk, Void> param) {
                final TableCell<Desk, Void> cell = new TableCell<Desk, Void>() {
                    private final Button btn = new Button("Approve");
                    {
                        btn.setId("btnLogin");
                        btn.setTextFill(Color.WHITE);
                        btn.setOnAction((ActionEvent event) -> {
                            Desk desk = getTableView().getItems().get(getIndex());
                            if (rbm.ApproveBooking(desk.getEmpUsername())) {
                                dataModel.showDialogBox("User Approved!", desk.getEmpUsername() + " has been approved on seat " + desk.getSeatNum());
                                desks.removeIf(t -> t.getEmpUsername() == desk.getEmpUsername());
                            } else if(!desk.getStatus().equals("pending")) {
                                dataModel.showDialogBox("Approve Failed!", "Only pending status can be approved!");
                            }else {
                                dataModel.showDialogBox("Approve Failed!", "Fail to approve, Please try again!");
                            }

                            // Reset Table
                            table.getItems().clear();
                            for (Desk desks : desks) {
                                table.getItems().add(desks);
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

        colBtnApprove.setCellFactory(cellFactory);
        table.getColumns().add(colBtnApprove);
    }

    private void addButtonReject() {
        TableColumn<Desk, Void> colBtnReject = new TableColumn("");
        Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>> cellFactory = new Callback<TableColumn<Desk, Void>, TableCell<Desk, Void>>() {
            @Override
            public TableCell<Desk, Void> call(final TableColumn<Desk, Void> param) {
                final TableCell<Desk, Void> cell = new TableCell<Desk, Void>() {
                    private final Button btn = new Button("Reject");
                    {
                        btn.setId("btnSignOut");
                        btn.setTextFill(Color.WHITE);
                        btn.setOnAction((ActionEvent event) -> {
                            Desk desk = getTableView().getItems().get(getIndex());
                            if (rbm.RejectBooking(desk.getEmpUsername())) {
                                dataModel.showDialogBox("User Rejected!", desk.getEmpUsername() + " has been rejected on seat " + desk.getSeatNum());
                                desks.removeIf(t -> t.getEmpUsername() == desk.getEmpUsername());
                            } else if(!desk.getStatus().equals("pending")){
                                dataModel.showDialogBox("Reject Failed!", "Only pending status can be rejected!");
                            } else {
                                dataModel.showDialogBox("Reject Failed!", "Fail to delete, Please try again!");
                            }

                            table.getItems().clear();
                            for (Desk desks : dataModel.desks) {
                                table.getItems().add(desks);
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
        colBtnReject.setCellFactory(cellFactory);
        table.getColumns().add(colBtnReject);
    }

    public void back(ActionEvent event) throws Exception {
        desks.clear();
        dataModel.closeScene(btnAdminBack);
        dataModel.showScene("../ui/AdminProfile.fxml", "Admin Profile");
    }

    public void displayAllBooking(ActionEvent event) {
        desks.clear();
        desks = rbm.displayAllDesk();
        // Reset Table
        table.getItems().clear();
        for (Desk desks : desks) {
            table.getItems().add(desks);
        }
    }
}
