package main.model.admin;


import main.SQLConnection;
import main.controller.DataModel;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AdminModel {
    Connection connection;


    public AdminModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public void generateReport() throws SQLException, IOException {
        try {
            Statement statement = connection.createStatement();
            String queryEmp = "select * from Employee";
            ResultSet resultSet = statement.executeQuery(queryEmp);
            BufferedWriter bw = new BufferedWriter(new FileWriter(("Report/Employee.csv")));
            bw.write("emp_id, firstName, lastName, role, username, password, secretQuestion, answerQuestion, admin");
            while (resultSet.next()) {
                int empId = resultSet.getInt("emp_id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String role = resultSet.getString("role");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String secretQuestion = resultSet.getString("secretQuestion");
                String answerQuestion = resultSet.getString("answerQuestion");
                Boolean admin = resultSet.getBoolean("admin");

                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", empId, firstName, lastName, role, username, password, secretQuestion, answerQuestion, admin);

                bw.newLine();
                bw.write(line);
            }

            String queryBooking = "select * from desk";
            ResultSet result = statement.executeQuery(queryBooking);
            bw = new BufferedWriter(new FileWriter("Report/Booking.csv"));
            bw.write("seat_id, status, date, seat_num, emp_username, current_date");
            while (result.next()) {
                int seatId = result.getInt("seat_id");
                String status = result.getString("status");
                String date = result.getString("date");
                int seatNum = result.getInt("seat_num");
                String empUsername = result.getString("emp_username");
                String currentDate = result.getString("current_date");
                String line = String.format("%s,%s,%s,%s,%s,%s", seatId, status, date, seatNum, empUsername, currentDate);
                bw.newLine();
                bw.write(line);
            }

            statement.close();
            bw.close();
            connection.close();
        } catch (SQLException | IOException e) {
            DataModel.showDialogBox("Error", e.getMessage());
        }
    }


}
