package main.model;

import com.sun.corba.se.spi.activation.EndPointInfo;
import main.Main;
import main.SQLConnection;
import main.controller.DataModel;

import java.sql.*;

public class ManageAccountEditModel {
    Connection connection;

    public ManageAccountEditModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }


    public Employee updateCurrentEmp(int empId, String firstName, String lastName, String role, String userName, String password, String secretQuestion, String answer, boolean admin) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "update Employee SET firstName = '" + firstName + "', lastName = '" + lastName + "', role = '" + role + "', username = '" + userName + "', password = '" + password + "', secretQuestion = '" + secretQuestion + "', answerQuestion = '" + answer +"', admin = '" + admin + "' WHERE emp_id = '" + empId + "'";
            int status = statement.executeUpdate(query);
            if (status > 0) {
                DataModel.emp = new Employee(DataModel.emp.getId(),userName, firstName, lastName, role, secretQuestion,answer, password, false);
                return DataModel.emp;
            } else {
                return DataModel.emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return DataModel.emp;
        }
    }




}
