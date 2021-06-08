package main.model.admin;

import main.SQLConnection;
import main.controller.DataModel;
import main.object.Desk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReleaseBookingModel {
    Connection connection;

    public ReleaseBookingModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public ArrayList<Desk> displayAllDesk(){
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
                if(desk.getStatus().equals("pending")){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date bookingDate = dateFormat.parse(desk.getDate());
                        Date currentTime = new Date(System.currentTimeMillis());
                        Date now = dateFormat.parse(dateFormat.format(currentTime));
                        long diff = bookingDate.getTime() - now.getTime();
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        if(diffDays <= 0){
                            String queryCancel = "update desk SET status = 'cancel' WHERE seat_id = '" + desk.getDeskId() + "' and status = 'pending'";
                            statement.executeUpdate(queryCancel);
                            desk.setStatus("cancel");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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
