package hu.sfm.controller;

import hu.sfm.entity.Permission;
import hu.sfm.entity.Product;
import hu.sfm.main.Main;
import hu.sfm.utils.CurrencyManager;
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

import java.io.IOException;

public class StorageProductController {
    @FXML
    private Label label;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField actualPriceTextField;

    @FXML
    private TextField newPriceTextField;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button productModificationType;

    @FXML
    private void initialize() throws Exception {

        if(!Main.actUser.getPerm().equals(Permission.ADMIN))
        {
            newPriceTextField.setDisable(true);
        }
        ProductDAO productDAO = new JPAProductDAO();

        for (Product p : productDAO.getProducts()){
            if(p.getName().equals(Main.productId)){
                label.setText(p.getName());
                actualPriceTextField.setText(CurrencyManager.createPattern(String.valueOf(p.getPrice())));
                newPriceTextField.setText(CurrencyManager.createPattern(String.valueOf(p.getPrice())));
            }
        }
        quantityTextField.setText("0");
    }

    @FXML
    private void onActionStorageSelectionSave(ActionEvent event) throws IOException {
        final int quantity = Integer.parseInt(quantityTextField.getText());

        ProductDAO pDAO = new JPAProductDAO();

        for(Product p : pDAO.getProducts()){

            if(p.getName().equals(label.getText())){

                p.setName(label.getText());
                p.setPrice(Integer.parseInt(CurrencyManager.removeTextFieldPattern(newPriceTextField.getText())));
                if (productModificationType.getText().equals("Elvétel") && p.getQuantity() < quantity) {
                    Main.alertMsg = "A mennyiségi módosítás nem hajtható végre! Az adott termékből kevesebb mennyiség áll rendelkezésre, mint amennyit el szeretnél távolítani.";
                    Main.showAlert("Notification");
                } else {
                    p.setQuantity(productModificationType.getText().equals("Hozzáadás") ? p.getQuantity() + quantity: p.getQuantity() - quantity);
                }
                pDAO.updateProduct(p);
            }

        }
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionStorageSelectionCancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
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
        if (quantityTextField.getText().length() > 3) {
            quantityTextField.setText("1000");
        } else if (quantityTextField.getText().equals("")) {
            quantityTextField.setText("0");
        }
    }

    @FXML
    private void onActionProductModification (ActionEvent event) {
        if (productModificationType.getText().equals("Hozzáadás")) {
            productModificationType.setText("Elvétel");
        } else {
            productModificationType.setText("Hozzáadás");
        }
    }
}
