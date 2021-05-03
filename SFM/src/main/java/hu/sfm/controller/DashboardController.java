package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class DashboardController {
    @FXML
    private Pane dashMenuLoaderPane;

    @FXML
    private void initialize() {
        Platform.runLater(dashMenuLoaderPane::requestFocus);
        Label welcomeLabel = new Label("Üdvözöllek a Programban, " + Main.actUser.getUsername());
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-family: Segoe UI; -fx-font-size: 32px; -fx-min-width: 800px; -fx-min-height: 40px");
        welcomeLabel.setLayoutX(420);
        welcomeLabel.setLayoutY(327.5);
        dashMenuLoaderPane.getChildren().add(welcomeLabel);
    }

    @FXML
    private void dashLoadMenu1(ActionEvent event) throws IOException {
        if (Main.clickedMenuBtn != null) {
            Main.clickedMenuBtn.setStyle("-fx-background-color: #2199dd; -fx-alignment: baseline_left; -fx-border-color: white; -fx-border-width: 0px 0px 2px 0px; -fx-background-radius: 0;");
        }
        Main.clickedMenuBtn = ((Button)event.getSource());
        Main.clickedMenuBtn.setStyle("-fx-background-color: rgb(15, 10, 85);");
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
        if (Main.clickedMenuBtn != null) {
            Main.clickedMenuBtn.setStyle("-fx-background-color: #2199dd; -fx-alignment: baseline_left; -fx-border-color: white; -fx-border-width: 0px 0px 2px 0px; -fx-background-radius: 0;");
        }
        Main.clickedMenuBtn = ((Button)event.getSource());
        Main.clickedMenuBtn.setStyle("-fx-background-color: rgb(15, 10, 85);");
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
        if (Main.clickedMenuBtn != null) {
            Main.clickedMenuBtn.setStyle("-fx-background-color: #2199dd; -fx-alignment: baseline_left; -fx-border-color: white; -fx-border-width: 0px 0px 2px 0px; -fx-background-radius: 0;");
        }
        Main.clickedMenuBtn = ((Button)event.getSource());
        Main.clickedMenuBtn.setStyle("-fx-background-color: rgb(15, 10, 85);");
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
        if (Main.clickedMenuBtn != null) {
            Main.clickedMenuBtn.setStyle("-fx-background-color: #2199dd; -fx-alignment: baseline_left; -fx-border-color: white; -fx-border-width: 0px 0px 2px 0px; -fx-background-radius: 0;");
        }
        Main.clickedMenuBtn = ((Button)event.getSource());
        Main.clickedMenuBtn.setStyle("-fx-background-color: rgb(15, 10, 85);");
        if (Main.actualPane == null) {
            Main.actualPane = dashMenuLoaderPane;
        }
        else{
            Main.actualPane.getChildren().remove(0);
        }
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/incomepanel.fxml"));
        dashMenuLoaderPane.getChildren().add(loadNewPane);
    }

    @FXML
    private void onMouseEnteredDashMenuBtn(MouseEvent event) {
        if (event.getSource() != Main.clickedMenuBtn) {
            ((Button)event.getSource()).setStyle("-fx-background-color: rgba(12, 12, 12, 0.3);");
        }
    }

    @FXML
    private void onMouseExitedDashMenuBtn(MouseEvent event) {
        if (event.getSource() != Main.clickedMenuBtn) {
            ((Button) event.getSource()).setStyle("-fx-background-color: #2199dd;");
        }
    }
}
