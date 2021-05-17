package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import main.model.Employee;

import java.io.IOException;

public class Helper {
    public static Employee emp = new Employee();

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

    public void closeScene(Button btn){
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

    public void displayAlertDialogBox(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "ERROR", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();

    }
}
