package main.model;

import main.SQLConnection;
import main.controller.Helper;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    Connection connection;

    public Boolean login;

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
                Helper.emp = new Employee(resultSet.getString("username"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("role"),
                        resultSet.getString("secretQuestion"),
                        resultSet.getString("answerQuestion"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("admin"));
                login = true;
                return Helper.emp;
            }
            else{
                Helper.emp = new Employee();
                login = false;
                return Helper.emp;
            }
        }
        catch (Exception e)
        {
            Helper.emp = new Employee();
            login = false;
            return Helper.emp;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }

    }


}