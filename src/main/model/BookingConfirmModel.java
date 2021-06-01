package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingConfirmModel  {
    Connection connection;

    public BookingConfirmModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Boolean addBookingDesk(String empUsername, int seatNum, String date) throws SQLException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime now = LocalDateTime.now();

            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("insert into booking_desk (emp_username, seat_num, date, current_date) values ('"+empUsername+"','"+seatNum+"','"+date+"','"+dtf.format(now)+"') ");
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
