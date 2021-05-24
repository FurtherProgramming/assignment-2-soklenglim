package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import main.model.RegisterModel;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Label alertTxt;

    @FXML
    private ComboBox questionBox;
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRegister;


    private DataModel dataModel = new DataModel();
    public RegisterModel registerModel = new RegisterModel();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataModel.setupQuestion(questionBox);
    }


    @FXML
    private void Cancel(ActionEvent event) throws Exception {
        dataModel.closeScene(btnCancel);
        dataModel.showScene("../ui/login.fxml", "Login");
    }

    @FXML
    private void Register(ActionEvent event) throws Exception {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String role = txtRole.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String question = (String) questionBox.getValue();
        String answer = txtAnswer.getText();

        List<TextField> textFields = Arrays.asList(txtFirstName, txtLastName, txtRole, txtUserName, txtPassword, txtAnswer);
        if (!registerModel.checkIfUserExist(userName)) {
            Boolean txtFieldEmpty = false;
            for (TextField field : textFields) {
                if (field.getText().isEmpty() || question == null) {
                    alertTxt.setText("Please fill all the information below");
                    alertTxt.setTextFill(Color.web("#FF0000"));
                    txtFieldEmpty = true;
                }
            }
            if (!txtFieldEmpty) {
                if(registerModel.Register(firstName, lastName, role, userName, password, question, answer)) {
                    dataModel.showDialogBox("Register Completed!", "Your account has been created!");
                } else {
                    dataModel.showDialogBox("Register Failed!", "Please try again!");
                }
                dataModel.closeScene(btnRegister);
                dataModel.showScene("../ui/login.fxml", "Login");
            }
        } else {
            alertTxt.setText("User Name is Taken");
            alertTxt.setTextFill(Color.web("#FF0000"));
        }
    }
}
