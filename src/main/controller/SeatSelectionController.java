package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import jdk.nashorn.internal.codegen.CompilerConstants;
import main.model.SeatSelectionModel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class SeatSelectionController implements Initializable {
    @FXML
    public DatePicker datePicker;
    private SeatSelectionModel ssm = new SeatSelectionModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePicker.setDayCellFactory(ssm.getDayCellFactory());
        datePicker.setConverter(ssm.converter);
    }


}
