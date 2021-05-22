package main.model;

import javafx.fxml.Initializable;
import main.SQLConnection;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class AdminModel  {
    Connection connection;


    public AdminModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public void updateEmp(){}
    public void deleteEmp(){}
    public void editEmp(){}
    public void addAdmin(){}
    public void updateAdmin(){}
    public void deleteAdmin(){}
}
