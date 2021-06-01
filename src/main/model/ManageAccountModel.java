package main.model;

import main.SQLConnection;
import main.controller.DataModel;

import java.sql.*;

public class ManageAccountModel {
    Connection connection;

    public ManageAccountModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Employee displayCurrentEmployee() throws SQLException  {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, DataModel.emp.getUserName());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DataModel.emp = new Employee(resultSet.getInt("emp_id"),
                        resultSet.getString("username"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("role"),
                        resultSet.getString("secretQuestion"),
                        resultSet.getString("answerQuestion"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("admin"));

                return DataModel.emp;
            }
            else{
                DataModel.emp = new Employee();
                return DataModel.emp;
            }
        }
        catch (Exception e)
        {
            DataModel.emp = new Employee();
            return DataModel.emp;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }






}
