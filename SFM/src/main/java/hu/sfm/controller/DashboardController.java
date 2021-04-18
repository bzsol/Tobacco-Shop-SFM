package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardController {
    @FXML
    private Pane dashMenuLoaderPane;

    @FXML
    private void initialize() {
        Label welcomeLabel = new Label("Üdvözöllek a Programban, " + Main.actUser.getUsername());
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-family: Segoe UI; -fx-font-size: 32px; -fx-min-width: 800px; -fx-min-height: 40px");
        welcomeLabel.setLayoutX(420);
        welcomeLabel.setLayoutY(327.5);
        dashMenuLoaderPane.getChildren().add(welcomeLabel);
    }

    @FXML
    private void onMouseEnteredDashMenu(MouseEvent event) {
        ((Button) event.getSource()).setStyle(
                "-fx-background-color: rgba(0, 0, 255, .3);" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseExitedDashMenu(MouseEvent event) {
        ((Button) event.getSource()).setStyle(
                "-fx-background-color: #2199dd;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void dashLoadMenu1(ActionEvent event) throws IOException {
        if (Main.actualPane == null) {
            Main.actualPane = dashMenuLoaderPane;
        }
        else
        {
            Main.actualPane.getChildren().remove(0);
        }

        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/account.fxml"));
        Main.actualPane.getChildren().add(loadNewPane);
    }

    @FXML
    private void dashLoadMenu2(ActionEvent event) throws IOException {
        if (Main.actualPane == null) {
            Main.actualPane = dashMenuLoaderPane;
        }
        else{
            Main.actualPane.getChildren().remove(0);
        }
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/purchasepanel.fxml"));
        Main.mainBuyMenuPane = loadNewPane;
        dashMenuLoaderPane.getChildren().add(loadNewPane);
    }

    @FXML
    private void dashLoadMenu3(ActionEvent event) throws IOException {
        if (Main.actualPane == null) {
            Main.actualPane = dashMenuLoaderPane;
        }
        else{
            Main.actualPane.getChildren().remove(0);
        }
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/storage.fxml"));
        dashMenuLoaderPane.getChildren().add(loadNewPane);
    }

    @FXML
    private void dashLoadMenu4(ActionEvent event) throws IOException {
        if (Main.actualPane == null) {
            Main.actualPane = dashMenuLoaderPane;
        }
        else{
            Main.actualPane.getChildren().remove(0);
        }
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/incomepanel.fxml"));
        dashMenuLoaderPane.getChildren().add(loadNewPane);
    }
}
