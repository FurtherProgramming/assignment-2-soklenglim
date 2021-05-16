package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Helper {
    public void showScene(String resource, String title) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
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

    public void displaySuccessDialogBox(){
        Alert alertSuccess = new Alert(Alert.AlertType.NONE);
        alertSuccess.setTitle("Completed!");
        alertSuccess.setHeaderText("Update info!");
        alertSuccess.setContentText("Employee details have been updated!");
        alertSuccess.show();

    }
}
