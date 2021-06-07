package main.model.user;

import main.SQLConnection;
import main.object.Desk;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewBookingModel {
    Connection connection;

    public ViewBookingModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public ArrayList<Desk> ViewBooking(String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from desk where emp_username = ? ";
        Desk desk = new Desk();
        ArrayList<Desk> desks = new ArrayList<Desk>();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                desk = new Desk(resultSet.getInt("seat_id"),
                        resultSet.getString("status"),
                        resultSet.getString("date"),
                        resultSet.getInt("seat_num"),
                        resultSet.getString("emp_username"),
                        resultSet.getString("current_date"));
                desks.add(desk);
            }


        } catch (Exception e) {
            return desks;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
        return desks;
    }

    public Boolean CancelBooking(int deskId) {
        try {
            Statement statement = connection.createStatement();
            String query = "update desk SET status = 'cancel' WHERE seat_id = '" + deskId + "'";
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

    public Boolean CheckIn(int deskId) {
        try {
            Statement statement = connection.createStatement();
            String query = "update desk SET status = 'check in' WHERE emp_username = '" + deskId + "'";
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
