package main.model.admin;

import main.SQLConnection;
import main.model.DataModel;
import main.object.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ManageAccountModel{
    Connection connection;

    public ManageAccountModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public ArrayList<Employee> displayAllUsers(){
        ArrayList<Employee> emps = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from Employee";
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);
            DataModel.emps.clear();
            while (resultSet.next()){
                Employee emp = new Employee(resultSet.getInt("emp_id"),
                        resultSet.getString("username"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("role"),
                        resultSet.getString("secretQuestion"),
                        resultSet.getString("answerQuestion"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("admin"));
                emps.add(emp);
            }
            return emps;
        } catch (SQLException e) {
            e.printStackTrace();
            return emps;
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
