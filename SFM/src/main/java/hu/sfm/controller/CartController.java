package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class CartController {
    @FXML
    private Button completeBtn;

    @FXML
    private Button declineBtn;

    @FXML
    private TextField totalPrice;

    @FXML
    private VBox cartVbox;

    @FXML
    private void initialize() {
        totalPrice.setText("0 Ft");

        if (Main.actualCart.entrySet().size() == 0) {
            Label zeroItemLabel = new Label("Jelenleg egyetlen termék sincs a kosárban");
            zeroItemLabel.setStyle("-fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-label-padding: 125px 0px 0px 202.5px");
            cartVbox.getChildren().add(zeroItemLabel);
        } else {
            int sorszam = 0;
            for (Map.Entry<String, Integer> product : Main.actualCart.entrySet()) {
                HBox productLine = new HBox();
                productLine.setMinHeight(50);
                productLine.setPrefWidth(800);
                if (sorszam % 2 == 0) {
                    productLine.setStyle("-fx-background-color: rgba(86, 86, 86, .8)");
                } else {
                    productLine.setStyle("-fx-background-color: rgba(132, 132, 132, .8)");
                }
                Label l1 = new Label(product.getValue() + "x");
                l1.setStyle("-fx-min-width: 90px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 15px 0px");
                Label l2 = new Label(product.getKey());
                l2.setStyle("-fx-min-width: 160px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 15px 0px");
                Label l3 = new Label("Ft");
                l3.setStyle("-fx-min-width: 220px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 15px 0px 0px 140px");
                Button removeBtn = new Button("Eltávolítás");
                removeBtn.setStyle("-fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-text-fill: white; -fx-font-family: Segoe UI; -fx-font-size: 14px; -fx-cursor: HAND");
                productLine.getChildren().addAll(l1, l2, l3, removeBtn);
                productLine.setMargin(removeBtn, new Insets(10, 0, 0, 170));
                cartVbox.getChildren().add(productLine);
                sorszam++;
            }
        }
    }

    @FXML
    private void onActionPurchaseComplete(ActionEvent event) {
        Stage stage = (Stage) completeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionPurchaseDecline(ActionEvent event) {
        Stage stage = (Stage) declineBtn.getScene().getWindow();
        stage.close();
    }
}
