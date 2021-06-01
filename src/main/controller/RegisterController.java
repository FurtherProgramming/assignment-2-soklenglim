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
    private CheckBox checkbox;
    @FXML
    private ComboBox questionBox;
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRegister;

    public static boolean isAdmin = false;
    private DataModel dataModel = new DataModel();
    public RegisterModel registerModel = new RegisterModel();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataModel.setupQuestion(questionBox);
        if(isAdmin==false){
            checkbox.setVisible(false);
        } else {
            checkbox.setVisible(true);
        }
    }


    @FXML
    private void Cancel(ActionEvent event) throws Exception {
        if(isAdmin==false) {
            dataModel.closeScene(btnCancel);
            dataModel.showScene("../ui/Login.fxml", "Login");
        } else {
            dataModel.closeScene(btnCancel);
            dataModel.showScene("../ui/AdminManagement.fxml", "Manage All Users Account");
            isAdmin=false;
        }
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
        int admin = 0;


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
            if(checkbox.isSelected()){
                admin = 1;
            } else {
                admin = 0;
            }

            if (!txtFieldEmpty) {
                if(registerModel.Register(firstName, lastName, role, userName, password, question, answer, admin)) {
                    dataModel.showDialogBox("Register Completed!", "Your account has been created!");
                } else {
                    dataModel.showDialogBox("Register Failed!", "Please try again!");
                }
                if(isAdmin==false) {
                    dataModel.closeScene(btnRegister);
                    dataModel.showScene("../ui/Login.fxml", "Login");
                } else {
                    dataModel.closeScene(btnRegister);
                    dataModel.showScene("../ui/AdminManagement.fxml", "Manage All Users Account");
                    isAdmin=false;
                }

            }
        } else {
            alertTxt.setText("User Name is Taken");
            alertTxt.setTextFill(Color.web("#FF0000"));
        }
    }
}
