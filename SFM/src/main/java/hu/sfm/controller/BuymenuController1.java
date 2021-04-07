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

public class BuymenuController1 {
    @FXML
    private void onActionBack() throws IOException {
        Main.actualPane.getChildren().remove(0);
        Main.actualPane.getChildren().add(Main.mainBuyMenuPane);
    }

    @FXML
    private void onActionOpenProductSelection(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/productselection.fxml"));
        Main.productId = ((Button) event.getSource()).getId();
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.setX(880);
        stage.setY(252.5);
        stage.showAndWait();

    }
}
