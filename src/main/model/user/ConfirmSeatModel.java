package main.model.user;

import main.SQLConnection;
import main.controller.DataModel;
import main.object.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmSeatModel {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime now = LocalDateTime.now();
    Connection connection;

    public ConfirmSeatModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Boolean addBookingDesk(String empUsername, String status, String date, int seatNum) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            int query = statement.executeUpdate("insert into desk (emp_username, status, date, seat_num, current_date) values ('" + empUsername + "','" + status + "','" + date + "','" + seatNum + "','" + dtf.format(now) + "') ");
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

    public boolean updateBooking(int seatId, int seatNum, String date) {
        try {
            Statement statement = connection.createStatement();
            String query = "update desk SET seat_num = '" + seatNum + "', date = '" + date + "' WHERE seat_id = '" + seatId + "'";
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

    public boolean lockSeat(String status, String date, int seatNum) {
        try {
            Statement statement = connection.createStatement();
            int query = statement.executeUpdate("insert into desk (emp_username, status, date, seat_num, current_date) values ('','" + status + "','" + date + "','" + seatNum + "','" + dtf.format(now) + "') ");
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

    public boolean unlockSeat(String date, int seatNum) {
        try {
            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("DELETE FROM desk WHERE date = '" + date + "' and seat_num = '" + seatNum + "';");
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
