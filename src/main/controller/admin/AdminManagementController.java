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
import main.controller.DataModel;
import main.controller.EditUserController;
import main.controller.RegisterController;
import main.model.admin.AdminManagementModel;
import main.object.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminManagementController implements Initializable {
    private AdminManagementModel amm = new AdminManagementModel();
    private DataModel dataModel = new DataModel();
    private EditUserController maec = new EditUserController();
    private RegisterController rc = new RegisterController();
    @FXML
    private TableView table = new TableView();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnAdminBack;
    @FXML
    private Button btnAddUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        amm.displayAllUsers();
        createTable();
        addButtonDelete();
        addButtonEdit();
    }


    private void createTable(){
        TableColumn<String, Employee> columnFirstName = new TableColumn<>("First Name");
        TableColumn<String, Employee> columnLastName = new TableColumn<>("Last Name");
        TableColumn<Integer, Employee> columnId = new TableColumn<>("Emp ID");
        TableColumn<String, Employee> columnRole = new TableColumn<>("Role");
        TableColumn<String, Employee> columnUsername = new TableColumn<>("Username");
        TableColumn<String, Employee> columnPassword = new TableColumn<>("Password");
        TableColumn<String, Employee> columnQuestion = new TableColumn<>("Question");
        TableColumn<String, Employee> columnAnswer = new TableColumn<>("Answer");
        TableColumn<Boolean, Employee> columnAdmin = new TableColumn<>("Admin");


        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        columnUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnQuestion.setCellValueFactory(new PropertyValueFactory<>("secretQ"));
        columnAnswer.setCellValueFactory(new PropertyValueFactory<>("secretA"));



        table.getColumns().addAll(columnId,columnFirstName,columnLastName,columnRole,columnUsername,columnPassword,columnQuestion,columnAnswer,columnAdmin);

        for(Employee emp : dataModel.emps){
            table.getItems().add(emp);
        }

        table.setLayoutX(40);
        table.setLayoutY(90);
        table.setPrefHeight(600);
        table.setPrefWidth(910);

        anchorPane.getChildren().add(table);
    }
    private void addButtonDelete() {
        TableColumn<Employee, Void> colBtnDelete = new TableColumn("");

        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setId("btnSignOut");
                        btn.setTextFill(Color.WHITE);
                        btn.setOnAction((ActionEvent event) -> {
                            Employee emp = getTableView().getItems().get(getIndex());
                            if(amm.deleteEmp(emp.getUserName())) {
                                dataModel.showDialogBox("User Deleted!", emp.getUserName() + " has been deleted!");
                                dataModel.emps.removeIf(t -> t.getUserName() == emp.getUserName());
                            } else {
                                dataModel.showDialogBox("Delete Fail!", "Fail to delete, Please try again!");
                            }

                            table.getItems().clear();

                            for(Employee emps : dataModel.emps){
                                table.getItems().add(emps);
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

        colBtnDelete.setCellFactory(cellFactory);
        table.getColumns().add(colBtnDelete);
    }
    private void addButtonEdit() {
        TableColumn<Employee, Void> colBtnEdit = new TableColumn("");

        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {

                    private final Button btnEdit = new Button("Edit");
                    {
                        btnEdit.setId("btnEdit");
                        btnEdit.setTextFill(Color.WHITE);
                        btnEdit.setOnAction((ActionEvent event) -> {
                            Employee emp = getTableView().getItems().get(getIndex());
                            maec.emp = emp;
                            maec.isAdmin = true;
                            dataModel.closeScene(btnEdit);
                            try {
                                dataModel.showScene("../ui/ManageAccountEdit.fxml", "Editing Information");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnEdit );
                        }
                    }
                };
                return cell;
            }
        };

        colBtnEdit.setCellFactory(cellFactory);
        table.getColumns().add(colBtnEdit);
    }


    public void addUser(ActionEvent event) throws Exception {
        rc.isAdmin = true;
        dataModel.closeScene(btnAddUser);
        dataModel.showScene("../ui/Register.fxml", "Add New User");
    }

    public void back(ActionEvent event) throws Exception {
        dataModel.closeScene(btnAdminBack);
        dataModel.showScene("../ui/AdminProfile.fxml", "Admin Profile");
    }
}
