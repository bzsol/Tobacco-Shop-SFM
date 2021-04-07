package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
}
