package hu.sfm.controller;

import hu.sfm.entity.Product;
import hu.sfm.main.Main;
import hu.sfm.utils.JPAProductDAO;
import hu.sfm.utils.ProductDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import hu.sfm.utils.CurrencyManager;

import java.util.Map;

public class CartController {
    @FXML
    private Button completeBtn;

    @FXML
    private Button continueBtn;

    @FXML
    private TextField totalPrice;

    @FXML
    private VBox cartVbox;

    @FXML
    private void initialize() {
        totalPrice.setText("0 Ft");

        if (Main.actualCart.entrySet().size() == 0) {
            emptyCartSetup();
        } else {
            setupCart();
        }
    }

    @FXML
    private void onActionPurchaseComplete(ActionEvent event) {
        Stage stage = (Stage) completeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionPurchaseContinue(ActionEvent event) {
        Stage stage = (Stage) continueBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionPurchaseDecline(ActionEvent event) {
        Main.actualCart.clear();
        cartVbox.getChildren().clear();
        emptyCartSetup();
    }

    private void setupCart() {
        int sorszam = 0;
        String itemprice = "0";
        ProductDAO productDAO = new JPAProductDAO();

        for (Map.Entry<String, Integer> product : Main.actualCart.entrySet()) {

            for(Product p : productDAO.getProducts()){
                if(p.getName().equals(product.getKey()))
                {
                    itemprice = String.valueOf(p.getPrice());
                }
            }

            HBox productLine = new HBox();
            productLine.setId(product.getKey());
            productLine.setMinHeight(50);
            productLine.setPrefWidth(800);
            if (sorszam % 2 == 0) {
                productLine.setStyle("-fx-background-color: rgba(86, 86, 86, .8)");
            } else {
                productLine.setStyle("-fx-background-color: rgba(132, 132, 132, .8)");
            }
            Label l1 = new Label(product.getValue() + "x");
            l1.setStyle("-fx-min-width: 100px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 14px");
            Label l2 = new Label(product.getKey());
            l2.setStyle("-fx-min-width: 155px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 14px");
            Label l3 = new Label(CurrencyManager.createPattern(String.valueOf(Integer.parseInt(itemprice) * product.getValue())));
            l3.setStyle("-fx-min-width: 310px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 14px");
            Button removeBtn = new Button("Eltávolítás");
            removeBtn.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-text-fill: white; -fx-font-family: Segoe UI; -fx-font-size: 14px; -fx-cursor: HAND");
            removeBtn.setId(product.getKey());
            removeBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Main.actualCart.remove(((Button)event.getSource()).getId());
                    cartVbox.getChildren().clear();
                    setupCart();
                }
            });
            productLine.getChildren().addAll(l1, l2, l3, removeBtn);
            productLine.setMargin(removeBtn, new Insets(10, 0, 0, 95));
            cartVbox.getChildren().add(productLine);
            sorszam++;
        }
    }

    private void emptyCartSetup() {
        Label zeroItemLabel = new Label("Jelenleg egyetlen termék sincs a kosárban");
        zeroItemLabel.setStyle("-fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-label-padding: 125px 0px 0px 202.5px");
        cartVbox.getChildren().add(zeroItemLabel);
    }
}
