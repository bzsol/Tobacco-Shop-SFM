package hu.sfm.controller;

import hu.sfm.entity.Product;
import hu.sfm.main.Main;
import hu.sfm.utils.CurrencyManager;
import hu.sfm.utils.JPAProductDAO;
import hu.sfm.utils.ProductDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StorageController {
    @FXML
    private VBox StorageVbox;

    @FXML
    private void initialize(){
        setupStorage();
    }

    private void setupStorage(){
        ProductDAO productDAO = new JPAProductDAO();
        int sorszam = 0;
        for (Product p : productDAO.getProducts()) {
            HBox productline = new HBox();
            productline.setPrefWidth(1345);
            productline.setMinHeight(50);
            if (sorszam % 2 == 0) {
                productline.setStyle("-fx-background-color: rgba(86, 86, 86, .8)");
            } else {
                productline.setStyle("-fx-background-color: rgba(132, 132, 132, .8)");
            }

            Label l1 = new Label(String.valueOf(p.getQuantity()));
            l1.setStyle("-fx-min-width: 100px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 14px");
            Label l2 = new Label(String.valueOf(p.getName()));
            l2.setStyle("-fx-min-width: 1100px; -fx-font-family: Segoe UI; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: BASELINE_CENTER; -fx-label-padding: 14px");
            Button modifyBtn = new Button("Szerkesztés");
            modifyBtn.setStyle("-fx-background-color: transparent; -fx-border-color: #2199dd; -fx-border-width: 2px; -fx-border-radius: 50%; -fx-text-fill: white; -fx-font-family: Segoe UI; -fx-font-size: 14px; -fx-cursor: HAND");
            modifyBtn.setId(p.getName());
            modifyBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    final double LOADER_PANE_WIDTH_DIFF = 240;
                    final double LOADER_PANE_HEIGHT_DIFF = 205;
                    final double PRODUCTSELECTION_WIDTH_CENTER = 200;
                    final double PRODUCTSELECTION_HEIGHT_CENTER = 150;

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/storageproduct.fxml"));
                    Main.productId = ((Button) event.getSource()).getId();
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(root));
                    Stage primaryStage = (Stage) Main.getScene().getWindow();
                    stage.setX(primaryStage.getX() + LOADER_PANE_WIDTH_DIFF + (primaryStage.getWidth() - LOADER_PANE_WIDTH_DIFF) / 2 - PRODUCTSELECTION_WIDTH_CENTER);
                    stage.setY(primaryStage.getY() + (primaryStage.getHeight() - LOADER_PANE_HEIGHT_DIFF) / 2 - PRODUCTSELECTION_HEIGHT_CENTER);
                    stage.showAndWait();
                }
            });
            productline.setMargin(modifyBtn, new Insets(10, 0, 0, 35));
            productline.getChildren().addAll(l1, l2, modifyBtn);
            StorageVbox.getChildren().add(productline);
            sorszam++;
        }
    }
}
