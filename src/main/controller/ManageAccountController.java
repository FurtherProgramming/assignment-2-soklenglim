package main.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.model.Employee;
import main.model.LoginModel;
import main.model.ManageAccountModel;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
public class ManageAccountController implements Initializable {

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
    public Label labelError;

    private Helper h = new Helper();


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            displayCurrentEmp(UserController.username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void displayCurrentEmp(String username) throws SQLException {
        Employee emp = mam.displayCurrentEmployee(username);
        txtFirstName.setText(emp.getFirstName());
        txtLastName.setText(emp.getLastName());
        txtRole.setText(emp.getRole());
        txtUsername.setText(emp.getUserName());
        txtPassword.setText(emp.getPassword());
        questionBox.setValue(emp.getSecretQ());
        h.setupQuestion(questionBox);
        txtAnswer.setText(emp.getSecretA());

    }




    public void Cancel(ActionEvent event) throws Exception {
        h.closeScene(btnCancel);
        h.showScene("../ui/UserProfile.fxml", "User Profile");
    }

    public void Update(ActionEvent event) throws SQLException {
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
            mam.updateCurrentEmp(firstName, lastName, role, userName, password, question, answer);
            h.displaySuccessDialogBox();
        } else {
            labelError.setText("Something went wrong. Please try again!");
        }
    }
}
