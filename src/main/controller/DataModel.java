package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import main.object.Desk;
import main.object.Employee;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DataModel {
    public static Employee emp = new Employee();
    public static Desk desk = new Desk();
    public static ArrayList<Desk> desks = new ArrayList<>();
    public static ArrayList<Employee> emps = new ArrayList<>();

    public void showScene(String resource, String title) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
    }

    public void closeScene(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void setupQuestion(ComboBox questionBox) {
        questionBox.getItems().add("What is your dog name ?");
        questionBox.getItems().add("What is your teacher name ?");
        questionBox.getItems().add("What is your favourite TV show?");
        questionBox.getItems().add("What is your favourite sport?");
        questionBox.getItems().add("Where are you from?");
    }

    public void displayAlertDialogBox() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    public static void showDialogBox(String title, String content) {
        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle(title);
        dialog.setResizable(false);

        //Setting the content of the dialog
        dialog.setContentText(content);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().setPrefSize(300, 100);
        //Creating a vbox to hold the button and the label
        dialog.showAndWait();
    }

    public long TimeValidation(String date, String status){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date bookingDate = dateFormat.parse(date);
            Date currentTime = new Date(System.currentTimeMillis());
            Date now = dateFormat.parse(dateFormat.format(currentTime));
            long diff = bookingDate.getTime() - now.getTime();
            long timeDiff = 0;
            if(status.equals("hours")){
                timeDiff = diff / (60 * 60 * 1000);
            } else if(status.equals("days")){
                timeDiff = diff / (24 * 60 * 60 * 1000);
            }
            return timeDiff;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String addDaysToDate(String dateString, int days){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dateString);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, days);
            date = c.getTime();
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
