package main.model;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import javafx.util.StringConverter;
import main.SQLConnection;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeatSelectionModel {
    Connection connection;

    public SeatSelectionModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
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
