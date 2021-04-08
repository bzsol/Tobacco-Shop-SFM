package hu.sfm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class IncomeController {
    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;
}
