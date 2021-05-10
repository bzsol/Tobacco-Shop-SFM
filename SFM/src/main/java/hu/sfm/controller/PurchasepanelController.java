package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PurchasepanelController {
    @FXML
    private void openProductCategory1() throws IOException {
        Main.actualPane.getChildren().remove(0);
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/buymenu1.fxml"));
        Main.actualPane.getChildren().add(loadNewPane);
    }

    @FXML
    private void openProductCategory2() throws IOException {
        Main.actualPane.getChildren().remove(0);
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/buymenu2.fxml"));
        Main.actualPane.getChildren().add(loadNewPane);
    }

    @FXML
    private void openProductCategory3() throws IOException {
        Main.actualPane.getChildren().remove(0);
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/buymenu3.fxml"));
        Main.actualPane.getChildren().add(loadNewPane);
    }

    @FXML
    private void openProductCategory4() throws IOException {
        Main.actualPane.getChildren().remove(0);
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/buymenu4.fxml"));
        Main.actualPane.getChildren().add(loadNewPane);
    }
    @FXML
    private void openProductCategory5() throws IOException {
        Main.actualPane.getChildren().remove(0);
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/buymenu5.fxml"));
        Main.actualPane.getChildren().add(loadNewPane);
    }

    @FXML
    private void onActionOpenCart() throws IOException {
        final double LOADER_PANE_WIDTH_DIFF = 240;
        final double LOADER_PANE_HEIGHT_DIFF = 205;
        final double CART_WIDTH_CENTER = 400;
        final double CART_HEIGHT_CENTER = 300;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cart.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Kosár");
        Stage primaryStage = (Stage) Main.getScene().getWindow();
        stage.setX(primaryStage.getX() + LOADER_PANE_WIDTH_DIFF + (primaryStage.getWidth() - LOADER_PANE_WIDTH_DIFF) / 2 - CART_WIDTH_CENTER);
        stage.setY(primaryStage.getY() + (primaryStage.getHeight() - LOADER_PANE_HEIGHT_DIFF) / 2 - CART_HEIGHT_CENTER);
        stage.setWidth(800);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
