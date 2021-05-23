package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BookingConfirmModel  {
    Connection connection;

    public BookingConfirmModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Boolean addBookingDesk(String empUsername, int seatNum, String date) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("insert into booking_desk (emp_username, seat_num, date) values ('"+empUsername+"','"+seatNum+"','"+date+"') ");
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
