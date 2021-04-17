package hu.sfm.controller;

import hu.sfm.entity.Product;
import hu.sfm.main.Main;
import hu.sfm.utils.CurrencyManager;
import hu.sfm.utils.JPAProductDAO;
import hu.sfm.utils.ProductDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StorageController {
    @FXML
    private VBox StorageVbox;

    @FXML
    private void initialize(){
        setupStorage();
    }

    private void setupStorage(){
        ProductDAO productDAO = new JPAProductDAO();
        for (Product p : productDAO.getProducts()) {
            HBox productline = new HBox();
            productline.setPrefWidth(1345);
            productline.setMinHeight(50);
            productline.setStyle("-fx-background-color: rgba(132, 132, 132, .8)");


            Label l1 = new Label(String.valueOf(p.getQuantity()));
            l1.setStyle("-fx-min-width: 100px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 14px");
            Label l2 = new Label(String.valueOf(p.getName()));
            l2.setStyle("-fx-min-width: 1100px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 14px");
            Button modifyBtn = new Button("Szerkesztés");
            modifyBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #2199dd; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-text-fill: white; -fx-font-family: Segoe UI; -fx-font-size: 14px; -fx-cursor: HAND");
            //modifyBtn.setId(product.getKey());
            modifyBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked");
                }
            });
            productline.setMargin(modifyBtn, new Insets(10, 0, 0, 35));
            productline.getChildren().addAll(l1, l2, modifyBtn);
            StorageVbox.getChildren().add(productline);
        }

    }
}
