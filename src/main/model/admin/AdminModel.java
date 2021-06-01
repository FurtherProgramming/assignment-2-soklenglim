package main.model.admin;


import main.SQLConnection;


import java.sql.Connection;


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
