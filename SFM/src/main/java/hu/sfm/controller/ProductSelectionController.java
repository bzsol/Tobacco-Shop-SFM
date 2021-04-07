package hu.sfm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ProductSelectionController {
    @FXML
    private TextField quantityTextField;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private void onActionIncrementQuantity (ActionEvent event) {
        if (Integer.parseInt(quantityTextField.getText()) < 100)
        quantityTextField.setText(String.valueOf(Integer.parseInt(quantityTextField.getText()) + 1));
    }

    @FXML
    private void onActionDecrementQuantity (ActionEvent event) {
        if (Integer.parseInt(quantityTextField.getText()) > 0) {
            quantityTextField.setText(String.valueOf(Integer.parseInt(quantityTextField.getText()) - 1));
        }
    }

    @FXML
    private void onKeyTypedValidate (KeyEvent event) {
        if (!Character.isDigit(event.getCharacter().charAt(0)) && event.getCharacter().charAt(0) != KeyCode.BACK_SPACE.getCode()) {
            StringBuilder removeNonDigits = new StringBuilder();
            for (char element : quantityTextField.getText().toCharArray()) {
                if (Character.isDigit(element)) {
                    removeNonDigits.append(element);
                }
            }
            quantityTextField.setText(removeNonDigits.toString());
        }
        if (quantityTextField.getText().length() > 2) {
            quantityTextField.setText("100");
        } else if (quantityTextField.getText().equals("")) {
            quantityTextField.setText("0");
        }
    }

    @FXML
    private void onActionProductSelectionSave(ActionEvent event) {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionProductSelectionCancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
