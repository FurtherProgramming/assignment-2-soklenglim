package main.model.admin;


import javafx.fxml.Initializable;
import main.SQLConnection;
import main.controller.DataModel;
import main.object.Employee;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageAccountModel{
    Connection connection;


    public ManageAccountModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public ArrayList<Employee> displayAllUsers(){
        try {
            Statement statement = connection.createStatement();
            String query = "select * from Employee";
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);
            Employee emp;
            DataModel.emps.clear();
            while (resultSet.next()){
                emp = new Employee(resultSet.getInt("emp_id"),
                        resultSet.getString("username"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("role"),
                        resultSet.getString("secretQuestion"),
                        resultSet.getString("answerQuestion"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("admin"));
                DataModel.emps.add(emp);
            }
            return DataModel.emps;
        } catch (SQLException e) {
            e.printStackTrace();
            return DataModel.emps;
        }
    }

    public Boolean deleteEmp(String username){
        try {
            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("DELETE FROM Employee WHERE username = '" + username + "';");
            if (status > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
