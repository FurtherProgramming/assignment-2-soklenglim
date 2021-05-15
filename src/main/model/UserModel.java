package main.model;
import main.SQLConnection;

import java.sql.*;

public class UserModel {
    Connection connection;

    public UserModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }


    public void displayUserDetail() {
        String sql = "SELECT firstName, lastName FROM Employee WHERE username = ";
        try (Connection conn = SQLConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("firstName") + "\t" +
                        rs.getString("lastName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}