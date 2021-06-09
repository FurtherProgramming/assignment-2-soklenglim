package main.model.user;

import main.SQLConnection;
import main.controller.DataModel;
import main.object.Desk;

import java.sql.*;
import java.util.ArrayList;

public class ViewBookingModel {
    Connection connection;
    private DataModel dataModel = new DataModel();

    public ViewBookingModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public ArrayList<Desk> ViewBooking(String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from desk where emp_username = ? ";
        Desk desk;
        ArrayList<Desk> desks = new ArrayList<>();
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
                if (desk.getStatus().equals("pending")) {
                    long timeDiff = dataModel.TimeValidation(desk.getDate(), "days");
                    if (timeDiff == 0) {
                        Statement statement = connection.createStatement();
                        String queryCancel = "update desk SET status = 'cancel' WHERE seat_id = '" + desk.getDeskId() + "' and status = 'pending'";
                        statement.executeUpdate(queryCancel);
                        desk.setStatus("cancel");
                    }

                }
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
            String query = "update desk SET status = 'check in' WHERE seat_id = '" + deskId + "'";
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
