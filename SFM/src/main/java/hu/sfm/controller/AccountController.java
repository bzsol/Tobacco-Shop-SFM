package hu.sfm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class AccountController {
    @FXML
    private ChoiceBox<String> accountChoiceBox;

    @FXML
    private void initialize() {
        accountChoiceBox.getItems().add("Teszt");
        accountChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));
    }

    @FXML
    private void onActionDeleteAccount (ActionEvent event) {

    }

    @FXML
    private void onActionSaveAccount (ActionEvent event) {

    }
}
