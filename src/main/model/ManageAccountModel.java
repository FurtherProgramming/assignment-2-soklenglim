package main.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import main.SQLConnection;

import java.sql.*;

public class ManageAccountModel {
    Connection connection;

    public ManageAccountModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Employee displayCurrentEmployee(String userName) throws SQLException  {
        Employee emp;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                emp = new Employee(resultSet.getString("username"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("role"),
                        resultSet.getString("secretQuestion"),
                        resultSet.getString("answerQuestion"),
                        resultSet.getString("password"));

                return emp;
            }
            else{
                emp = new Employee();
                return emp;
            }
        }
        catch (Exception e)
        {
            emp = new Employee();
            return emp;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public Boolean updateCurrentEmp(String firstName, String lastName, String role, String userName, String password, String secretQuestion, String answer) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "update Employee SET firstName = '"+firstName+"', lastName = '"+lastName+"', role = '"+role+"', password = '"+password+"', secretQuestion = '"+secretQuestion+"', answerQuestion = '"+answer+"' WHERE username = '" +userName+"'";
            int status = statement.executeUpdate(query);
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
