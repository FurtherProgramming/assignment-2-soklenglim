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


    public void Register(String firstName, String lastName, String role, String userName, String password, String secretQuestion, String answer) {
        connection = SQLConnection.connect();
        try {
            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("insert into Employee (firstName, lastName, role, username, password, secretQuestion, answerQuestion) values ('"+firstName+"','"+lastName+"','"+role+"','"+userName+"','"+password+"','"+secretQuestion+"','"+answer+"') ");
            if (status > 0) {
                System.out.println("user register completed");
            } else {
                System.out.println("Fail to register");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectAll(){
        String sql = "SELECT firstName, lastName FROM Employee";
        try (Connection conn = SQLConnection.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("firstName") +  "\t" +
                        rs.getString("lastName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
