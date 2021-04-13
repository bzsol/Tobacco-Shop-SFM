package hu.sfm.controller;

import hu.sfm.entity.Product;
import hu.sfm.main.Main;
import hu.sfm.utils.JPAProductDAO;
import hu.sfm.utils.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import hu.sfm.utils.CurrencyManager;

public class ProductSelectionController {
    @FXML
    private TextField quantityTextField;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField unitPrice;

    @FXML
    private TextField fullPrice;

    @FXML
    private Label label;

    @FXML
    private void onActionIncrementQuantity (ActionEvent event) {
        if (Integer.parseInt(quantityTextField.getText()) < 100) {
            quantityTextField.setText(String.valueOf(Integer.parseInt(quantityTextField.getText()) + 1));
            CurrencyManager.setTextFieldPattern(fullPrice, unitPrice, quantityTextField);
        }

    }

    @FXML
    private void onActionDecrementQuantity (ActionEvent event) {
        if (Integer.parseInt(quantityTextField.getText()) > 0) {
            quantityTextField.setText(String.valueOf(Integer.parseInt(quantityTextField.getText()) - 1));
            CurrencyManager.setTextFieldPattern(fullPrice, unitPrice, quantityTextField);
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
        CurrencyManager.setTextFieldPattern(fullPrice, unitPrice, quantityTextField);
    }

    @FXML
    private void onActionProductSelectionSave(ActionEvent event) {
        Main.income += Integer.parseInt(CurrencyManager.removeTextFieldPattern(fullPrice.getText()));
        if (Main.actualCart.containsKey(label.getText())) {
            Main.actualCart.replace(label.getText(), Main.actualCart.get(label.getText()) + Integer.parseInt(quantityTextField.getText()));
        } else {
            Main.actualCart.put(label.getText(), Integer.parseInt(quantityTextField.getText()));
        }
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionProductSelectionCancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize(){
        ProductDAO productDAO = new JPAProductDAO();

        for(Product p : productDAO.getProducts()){
            if(p.getName().equals(Main.productId)){
                unitPrice.setText(CurrencyManager.createPattern(String.valueOf(p.getPrice())));
                label.setText(p.getName());
            }
        }
        CurrencyManager.setTextFieldPattern(fullPrice, unitPrice, quantityTextField);

    }
}
