package main.model;

import main.SQLConnection;
import main.object.Employee;

import java.sql.*;

public class ForgetPasswordModel {
    Connection connection;

    public ForgetPasswordModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }


    public Boolean ResetPassword(String userName, String question, String answer) throws SQLException {
        connection = SQLConnection.connect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Employee WHERE username = '" + userName + "' AND secretQuestion = '" + question + "' AND answerQuestion = '" + answer + "'";
        try {
            preparedStatement = connection.prepareStatement(query);

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

                return true;
            } else {
                DataModel.emp = new Employee();
                return false;
            }
        } catch (Exception e) {
            DataModel.emp = new Employee();
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

    }

    public Boolean updateCurrentEmp(int empId, String password) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "update Employee SET password = '" + password + "' WHERE emp_id = '" + empId + "'";
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
