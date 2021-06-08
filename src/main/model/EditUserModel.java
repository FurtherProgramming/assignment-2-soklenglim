package main.model;

import main.SQLConnection;
import main.controller.DataModel;
import main.controller.EditUserController;
import main.object.Employee;

import java.sql.*;

public class EditUserModel {
    Connection connection;

    public EditUserModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }


    public Boolean updateCurrentEmp(int empId, String firstName, String lastName, String role, String userName, String password, String secretQuestion, String answer, int admin) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "update Employee SET firstName = '" + firstName + "', lastName = '" + lastName + "', role = '" + role + "', username = '" + userName + "', password = '" + password + "', secretQuestion = '" + secretQuestion + "', answerQuestion = '" + answer +"', admin = '" + admin + "' WHERE emp_id = '" + empId + "'";
            int status = statement.executeUpdate(query);
            if (status > 0) {
                DataModel.emp = new Employee(DataModel.emp.getId(),userName, firstName, lastName, role, secretQuestion,answer, password, false);
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
