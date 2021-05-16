package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    private Helper h = new Helper();

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
        if(!txtField){
            maem.updateCurrentEmp(firstName, lastName, role, userName, password, question, answer);
            h.closeScene(btnConfirm);
            h.showScene("../ui/ManageAccount.fxml", "Manage Account");

        } else {
            labelError.setText("Something went wrong. Please try again!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            EditCurrentEmp(h.emp.getUserName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void EditCurrentEmp(String username) throws SQLException {
        h.emp = mam.displayCurrentEmployee(username);
        txtFirstName.setText(h.emp.getFirstName());
        txtLastName.setText(h.emp.getLastName());
        txtRole.setText(h.emp.getRole());
        txtUsername.setText(h.emp.getUserName());
        txtPassword.setText(h.emp.getPassword());
        questionBox.setValue(h.emp.getSecretQ());
        h.setupQuestion(questionBox);
        txtAnswer.setText(h.emp.getSecretA());
    }

    public void Cancel(ActionEvent event) throws Exception {
        h.closeScene(btnCancel);
        h.showScene("../ui/ManageAccount.fxml", "Manage Account");
    }
}
