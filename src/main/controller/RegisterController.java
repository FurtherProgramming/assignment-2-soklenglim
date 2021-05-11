package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
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
    public RegisterModel registerModel = new RegisterModel();
    @FXML
    private ComboBox questionBox;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void setupQuestion() {
        questionBox.getItems().add("What is your dog name ?");
        questionBox.getItems().add("What is your teacher name ?");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupQuestion();
    }


    @FXML
    private void Cancel(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(role);
        System.out.println(userName);
        System.out.println(password);
        System.out.println(question);
        System.out.println(answer);
        List<TextField> textFields = Arrays.asList(txtFirstName, txtLastName, txtRole, txtUserName, txtPassword, txtAnswer);
        if (!registerModel.checkIfUserExist(userName)) {
            Boolean txtFieldEmpty = false;
            for (TextField field : textFields) {
                if (field.getText().isEmpty() || question == null) {
                    alertTxt.setText("Please fill all the information below");
                    alertTxt.setTextFill(Color.web("#FF0000"));
                    txtFieldEmpty = true;
                    break;
                }
            }
            if (!txtFieldEmpty) {
                registerModel.Register(firstName, lastName, role, userName, password, question, answer);
            }
        } else {
            alertTxt.setText("User Name is Taken");
            alertTxt.setTextFill(Color.web("#FF0000"));
        }
    }
}
