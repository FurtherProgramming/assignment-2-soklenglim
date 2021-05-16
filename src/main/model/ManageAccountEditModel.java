package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageAccountEditModel {
    Connection connection;

    public ManageAccountEditModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }


    public Boolean updateCurrentEmp(String firstName, String lastName, String role, String userName, String password, String secretQuestion, String answer) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "update Employee SET firstName = '" + firstName + "', lastName = '" + lastName + "', role = '" + role + "', password = '" + password + "', secretQuestion = '" + secretQuestion + "', answerQuestion = '" + answer + "' WHERE username = '" + userName + "'";
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
