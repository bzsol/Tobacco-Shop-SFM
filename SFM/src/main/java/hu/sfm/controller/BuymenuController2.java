package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class BuymenuController2 {
    @FXML
    private void onActionBack() {
        Main.actualPane.getChildren().remove(0);
        Main.actualPane.getChildren().add(Main.mainBuyMenuPane);
    }

    @FXML
    private void onActionOpenProductSelection(ActionEvent event) throws IOException {
        final double LOADER_PANE_WIDTH_DIFF = 240;
        final double LOADER_PANE_HEIGHT_DIFF = 205;
        final double PRODUCTSELECTION_WIDTH_CENTER = 200;
        final double PRODUCTSELECTION_HEIGHT_CENTER = 150;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/productselection.fxml"));
        Main.productId = ((Button) event.getSource()).getId();
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        Stage primaryStage = (Stage) Main.getScene().getWindow();
        stage.setX(primaryStage.getX() + LOADER_PANE_WIDTH_DIFF + (primaryStage.getWidth() - LOADER_PANE_WIDTH_DIFF) / 2 - PRODUCTSELECTION_WIDTH_CENTER);
        stage.setY(primaryStage.getY() + (primaryStage.getHeight() - LOADER_PANE_HEIGHT_DIFF) / 2 - PRODUCTSELECTION_HEIGHT_CENTER);
        stage.showAndWait();
    }
}
