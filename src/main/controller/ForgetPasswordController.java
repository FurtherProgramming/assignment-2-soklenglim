package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.model.ForgetPasswordModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgetPasswordController implements Initializable {
    private DataModel dataModel = new DataModel();
    private ForgetPasswordModel fpm = new ForgetPasswordModel();
    @FXML
    private ComboBox questionBox;
    @FXML
    private Button btnResetPassword;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtAnswer;
    @FXML
    private Label alertTxt;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataModel.setupQuestion(questionBox);
    }

    public void resetPassword(ActionEvent event) throws Exception {

        String userName = txtUsername.getText();
        String question = (String) questionBox.getValue();
        String answer = txtAnswer.getText();
        List<TextField> textFields = Arrays.asList(txtUsername, txtAnswer);

        // Check empty field
        Boolean txtFieldEmpty = false;

        for (TextField field : textFields) {
            if (field.getText().isEmpty() || question == null) {
                alertTxt.setText("Please fill all the information below");
                alertTxt.setTextFill(Color.web("#FF0000"));
                txtFieldEmpty = true;
            }
        }
        if(!txtFieldEmpty){
            if(fpm.ResetPassword(userName, question, answer)){
                Random rnd = new Random();
                String password = rnd.nextInt(999999) + "";
                fpm.updateCurrentEmp(dataModel.emp.getId(), password);
                dataModel.showDialogBox("Reset Password!" , "Your new password is " + password);
                dataModel.closeScene(btnResetPassword);
                dataModel.showScene("../ui/Login.fxml", "Login");
            } else {
                dataModel.showDialogBox("Reset Password!" , "Please input the right information.");
            }

        }
    }


    public void back(ActionEvent event) throws Exception {
        dataModel.closeScene(btnBack);
        dataModel.showScene("../ui/Login.fxml", "Login");
    }
}
