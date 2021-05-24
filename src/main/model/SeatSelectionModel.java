package main.model;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import javafx.util.StringConverter;
import main.SQLConnection;
import main.controller.DataModel;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SeatSelectionModel {
    Connection connection;

    public SeatSelectionModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }


    public Boolean bookDesk(int seat_id, int emp_id, String date, String availability){
        try {
            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("insert into booking_desk (seat_id, emp_id, date, availability) values ('"+seat_id+"','"+emp_id+"','"+date+"','"+availability+"') ");
            if (status > 0) {
                System.out.println("Book completed");
                return true;
            } else {
                System.out.println("Fail to book");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<Desk> initDesk(String date) throws SQLException  {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from desk where date = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DataModel.desk = new Desk(resultSet.getString("status"),
                        resultSet.getString("date"),
                        resultSet.getInt("seat_num"));
                DataModel.desks.add(DataModel.desk);

            }
        }
        catch (Exception e)
        {
            return DataModel.desks;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
        return DataModel.desks;
    }


    //Disable past dates
    public Callback<DatePicker, DateCell> getDayCellFactory(){
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>(){
            public DateCell call(final DatePicker datePicker){
                return new DateCell(){
                    public void updateItem(LocalDate item, boolean empty){
                        super.updateItem(item, empty);
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) < 0);
                    }
                };
            }
        };
        return dayCellFactory;
    }


    // Date convert to dd-mm-yyyy in case on some machine date system is different
    public StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");
        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }
        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };
}
