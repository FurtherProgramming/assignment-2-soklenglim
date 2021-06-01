package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import main.model.Employee;
import main.model.ManageAccountEditModel;
import main.model.ManageAccountModel;

import java.net.URL;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ManageAccountEditController implements Initializable {
    private ManageAccountEditModel maem = new ManageAccountEditModel();
    private ManageAccountModel mam = new ManageAccountModel();
    @FXML
    private CheckBox checkbox;

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private ComboBox questionBox;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnConfirm;
    @FXML
    public Label labelError;


    public static boolean isAdmin = false;
    private DataModel dataModel = new DataModel();
    public static Employee emp = new Employee();

    public void Update(ActionEvent event) throws Exception{
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String role = txtRole.getText();
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        String question = (String) questionBox.getValue();
        String answer = txtAnswer.getText();
        List<TextField> textFields = Arrays.asList(txtFirstName, txtLastName, txtRole, txtUsername, txtPassword, txtAnswer);
        Boolean txtField = false;
        for (TextField field : textFields) {
            if (field.getText().isEmpty() || question == null) {
                txtField = true;
            }
        }
        if(!txtField){
            maem.updateCurrentEmp(emp.getId(), firstName, lastName, role, userName, password, question, answer, checkbox.isSelected());

            if(isAdmin == false){
                dataModel.showDialogBox("Account Updated!", "Your account detail has been updated!");
                dataModel.closeScene(btnConfirm);
                dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
            } else {
                dataModel.showDialogBox("Account Updated!", "User detail has been updated!");
                dataModel.closeScene(btnConfirm);
                dataModel.showScene("../ui/AdminManagement.fxml", "Manage All Users Account");
            }
        } else {
            labelError.setText("Please fill all the information below");
            labelError.setTextFill(Color.web("#FF0000"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            EditCurrentEmp(emp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void EditCurrentEmp(Employee currentUser) throws SQLException {
        txtFirstName.setText(currentUser.getFirstName());
        txtLastName.setText(currentUser.getLastName());
        txtRole.setText(currentUser.getRole());
        txtUsername.setText(currentUser.getUserName());
        txtPassword.setText(currentUser.getPassword());
        questionBox.setValue(currentUser.getSecretQ());
        dataModel.setupQuestion(questionBox);
        txtAnswer.setText(currentUser.getSecretA());
        checkbox.setSelected(currentUser.getAdmin());
        if(isAdmin == false){
            txtUsername.setEditable(false);
        } else {
            txtUsername.setEditable(true);
        }
    }

    public void Cancel(ActionEvent event) throws Exception {
        if(isAdmin == false) {
            dataModel.closeScene(btnCancel);
            dataModel.showScene("../ui/ManageAccount.fxml", "Manage Account");
        } else {
            dataModel.closeScene(btnCancel);
            dataModel.showScene("../ui/AdminManagement.fxml", "Manage All Users Account");
            isAdmin = false;
        }
    }
}
