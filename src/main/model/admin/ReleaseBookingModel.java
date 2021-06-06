package main.model.admin;

import main.SQLConnection;
import main.controller.DataModel;
import main.object.Desk;
import main.object.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReleaseBookingModel {
    Connection connection;

    public ReleaseBookingModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public ArrayList<Desk> displayAllUsers(){
        try {
            Statement statement = connection.createStatement();
            String query = "select * from desk where status = 'pending'";
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);
            Desk desk;
            DataModel.desks.clear();
            while (resultSet.next()){
                desk = new Desk(resultSet.getInt("seat_id"),
                        resultSet.getString("status"),
                        resultSet.getString("date"),
                        resultSet.getInt("seat_num"),
                        resultSet.getString("emp_username"),
                        resultSet.getString("current_date"));
                DataModel.desks.add(desk);
            }
            return DataModel.desks;
        } catch (SQLException e) {
            e.printStackTrace();
            return DataModel.desks;
        }
    }

    public Boolean RejectBooking(String username) {
        try {
            Statement statement = connection.createStatement();
            String query = "update desk SET status = 'rejected' WHERE emp_username = '" + username + "' and status = 'pending'";
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
    public Boolean ApproveBooking(String username) {
        try {
            Statement statement = connection.createStatement();
            String query = "update desk SET status = 'approve' WHERE emp_username = '" + username + "' and status = 'pending'";
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
