package main.model.user;

import main.SQLConnection;
import main.controller.DataModel;
import main.object.Desk;
import main.object.Employee;

import javax.xml.crypto.Data;
import java.sql.*;

public class ViewBookingModel {
    Connection connection;

    public ViewBookingModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Desk ViewBooking(String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from desk where emp_username = ? and status = 'pending'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DataModel.desk = new Desk(resultSet.getInt("seat_id"),
                        resultSet.getString("status"),
                        resultSet.getString("date"),
                        resultSet.getInt("seat_num"),
                        resultSet.getString("current_date"));
            }

        } catch (Exception e) {
            return DataModel.desk;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
        return DataModel.desk;
    }

    public Boolean CancelBooking(String username) {
        try {
            Statement statement = connection.createStatement();
            String query = "update desk SET status = 'cancel' WHERE emp_username = '" + username + "'";
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
