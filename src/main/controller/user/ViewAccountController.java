package main.controller.user;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import main.controller.DataModel;
import main.controller.EditUserController;
import main.model.user.ViewAccountModel;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewAccountController implements Initializable {

    private ViewAccountModel mam = new ViewAccountModel();

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

    private DataModel dataModel = new DataModel();
    private EditUserController maem = new EditUserController();


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            displayCurrentEmp();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void displayCurrentEmp() throws SQLException {
        dataModel.emp = mam.displayCurrentEmployee();
        lbFirstName.setText(dataModel.emp.getFirstName());
        lbLastName.setText(dataModel.emp.getLastName());
        lbRole.setText(dataModel.emp.getRole());
        lbUsername.setText(dataModel.emp.getUserName());
        lbPassword.setText("********");
        lbQuestion.setText(dataModel.emp.getSecretQ());
        lbAnswer.setText(dataModel.emp.getSecretA());

    }


    public void Cancel(ActionEvent event) throws Exception {
        dataModel.closeScene(btnCancel);
        dataModel.showScene("../ui/UserProfile.fxml", "User Profile");
    }

    public void Edit(ActionEvent event) throws Exception {
        dataModel.closeScene(btnEdit);
        maem.emp = dataModel.emp;
        dataModel.showScene("../ui/EditUser.fxml", "Editing Information");

    }


}
