package main.model.admin;

import main.SQLConnection;
import main.controller.DataModel;
import main.object.Desk;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ReleaseBookingModel {
    Connection connection;
    private DataModel dataModel = new DataModel();

    public ReleaseBookingModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public ArrayList<Desk> displayPendingDesk() {
        Desk desk;
        ArrayList<Desk> desks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from desk where status = 'pending'";
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                desk = new Desk(resultSet.getInt("seat_id"),
                        resultSet.getString("status"),
                        resultSet.getString("date"),
                        resultSet.getInt("seat_num"),
                        resultSet.getString("emp_username"),
                        resultSet.getString("current_date"));
                if (desk.getStatus().equals("pending")) {
                    long timeDiff = dataModel.TimeValidation(desk.getDate(), "days");
                    if(timeDiff == 0){
                        String queryCancel = "update desk SET status = 'cancel' WHERE seat_id = '" + desk.getDeskId() + "' and status = 'pending'";
                        statement.executeUpdate(queryCancel);
                        desk.setStatus("cancel");
                        desks.add(desk);
                    } else {
                        desks.add(desk);
                    }
                }
            }
            return desks;
        } catch (SQLException e) {
            e.printStackTrace();
            return desks;
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

    public ArrayList<Desk> displayAllDesk() {
        Desk desk;
        ArrayList<Desk> desks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from desk ORDER BY status";
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                desk = new Desk(resultSet.getInt("seat_id"),
                        resultSet.getString("status"),
                        resultSet.getString("date"),
                        resultSet.getInt("seat_num"),
                        resultSet.getString("emp_username"),
                        resultSet.getString("current_date"));
                desks.add(desk);
            }
            return desks;
        } catch (SQLException e) {
            e.printStackTrace();
            return desks;
        }
    }

}
