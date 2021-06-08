package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.model.RegisterModel;
import main.object.Employee;
import main.model.EditUserModel;
import main.model.user.ViewAccountModel;

import java.net.URL;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {
    private EditUserModel eum = new EditUserModel();

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

    private int admin = 0;
    public static boolean isAdmin = false;
    private DataModel dataModel = new DataModel();
    public static Employee emp = new Employee();
    private RegisterModel rm = new RegisterModel();

    public void Update(ActionEvent event) throws Exception {
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

        if (checkbox.isSelected()) {
            admin = 1;
        } else {
            admin = 0;
        }

        if (!txtField) {
            if (emp.getUserName().equals(userName)) {
                if (eum.updateCurrentEmp(emp.getId(), firstName, lastName, role, userName, password, question, answer, admin)) {
                    dataModel.showDialogBox("Account Updated!", "User detail has been updated!");
                    dataModel.closeScene(btnConfirm);
                    if (isAdmin == false) {
                        dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
                    } else {
                        dataModel.showScene("../ui/AdminManagement.fxml", "Manage All Users Account");
                    }
                } else {
                    dataModel.showDialogBox("Account Updated Fail!", "Fail to update!");
                }
            } else {
                if (!rm.checkIfUserExist(userName)) {
                    if (eum.updateCurrentEmp(emp.getId(), firstName, lastName, role, userName, password, question, answer, admin)) {
                        dataModel.showDialogBox("Account Updated!", "User detail has been updated!");
                        dataModel.closeScene(btnConfirm);
                        if (isAdmin == false) {
                            dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
                        } else {
                            dataModel.showScene("../ui/AdminManagement.fxml", "Manage All Users Account");
                        }
                    } else {
                        dataModel.showDialogBox("Account Updated Fail!", "Fail to update!");
                    }

                } else {
                    dataModel.showDialogBox("Account Updated Fail!", "Username is already existed!");
                }
            }

        } else {
            dataModel.showDialogBox("Error Message!", "Please fill all the information below!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            EditCurrentEmp(emp);
            if (isAdmin == false) {
                checkbox.setVisible(false);
                txtUsername.setEditable(false);
            } else {
                checkbox.setVisible(true);
                txtUsername.setEditable(true);
            }
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

    }

    public void Cancel(ActionEvent event) throws Exception {
        if (isAdmin == false) {
            dataModel.closeScene(btnCancel);
            dataModel.showScene("../ui/ViewAccount.fxml", "Manage Account");
        } else {
            dataModel.closeScene(btnCancel);
            dataModel.showScene("../ui/AdminManagement.fxml", "Manage All Users Account");
            isAdmin = false;
        }
    }
}
