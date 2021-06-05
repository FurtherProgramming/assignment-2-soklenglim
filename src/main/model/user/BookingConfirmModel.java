package main.model.user;

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

    public Boolean addBookingDesk(String empUsername, String status, String date, int seatNum) throws SQLException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime now = LocalDateTime.now();

            Statement statement = connection.createStatement();
            int query = statement.executeUpdate("insert into desk (emp_username, status, date, seat_num, current_date) values ('"+empUsername+"','"+ status +"','"+date+"','"+seatNum+"','"+dtf.format(now)+"') ");
            if (query > 0) {
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
