package main.model;
import main.SQLConnection;

import java.sql.*;

public class RegisterModel {
    Connection connection;

    public RegisterModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }


    public Boolean Register(String firstName, String lastName, String role, String userName, String password, String secretQuestion, String answer, boolean admin) {
        connection = SQLConnection.connect();
        try {
            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("insert into Employee (firstName, lastName, role, username, password, secretQuestion, answerQuestion, admin) values ('"+firstName+"','"+lastName+"','"+role+"','"+userName+"','"+password+"','"+secretQuestion+"','"+answer+"','"+admin+"') ");
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


    public Boolean checkIfUserExist(String userName)throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}
