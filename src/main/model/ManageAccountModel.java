package main.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import main.SQLConnection;
import main.controller.Helper;

import java.sql.*;

public class ManageAccountModel {
    Connection connection;

    public ManageAccountModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Employee displayCurrentEmployee(String userName) throws SQLException  {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Helper.emp.getUserName());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Helper.emp = new Employee(resultSet.getString("username"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("role"),
                        resultSet.getString("secretQuestion"),
                        resultSet.getString("answerQuestion"),
                        resultSet.getString("password"));

                return Helper.emp;
            }
            else{
                Helper.emp = new Employee();
                return Helper.emp;
            }
        }
        catch (Exception e)
        {
            Helper.emp = new Employee();
            return Helper.emp;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }






}
