package main.model;

import main.Main;
import main.SQLConnection;
import main.controller.DataModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    Connection connection;


    public LoginModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }

    public Employee isLogin(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ? and password= ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

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
                Main.isLogin = true;
                return DataModel.emp;
            }
            else{
                DataModel.emp = new Employee();
                Main.isLogin = false;
                return DataModel.emp;
            }
        }
        catch (Exception e)
        {
            DataModel.emp = new Employee();
            Main.isLogin = false;
            return DataModel.emp;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }

    }


}