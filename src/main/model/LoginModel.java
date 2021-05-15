package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

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
        Employee emp = new Employee();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ? and password= ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                emp = new Employee(resultSet.getString("username"), resultSet.getString("role"));
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


}