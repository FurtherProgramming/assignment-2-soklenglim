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
    private Label lbFirstName;
    @FXML
    private Label lbLastName;
    @FXML
    private Label lbRole;
    @FXML
    private Label lbUsername;
    @FXML
    private Label lbPassword;
    @FXML
    private Label lbQuestion;
    @FXML
    private Label lbAnswer;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnCancel;

    private Helper h = new Helper();


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            displayCurrentEmp(h.emp.getUserName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void displayCurrentEmp(String username) throws SQLException {
        h.emp = mam.displayCurrentEmployee(username);
        lbFirstName.setText(h.emp.getFirstName());
        lbLastName.setText(h.emp.getLastName());
        lbRole.setText(h.emp.getRole());
        lbUsername.setText(h.emp.getUserName());
        lbPassword.setText("********");
        lbQuestion.setText(h.emp.getSecretQ());
        lbAnswer.setText(h.emp.getSecretA());

    }




    public void Cancel(ActionEvent event) throws Exception {
        h.closeScene(btnCancel);
        h.showScene("../ui/UserProfile.fxml", "User Profile");
    }

    public void Edit(ActionEvent event) throws Exception {
        h.closeScene(btnEdit);
        h.showScene("../ui/ManageAccountEdit.fxml", "Editing Information");
    }


}
