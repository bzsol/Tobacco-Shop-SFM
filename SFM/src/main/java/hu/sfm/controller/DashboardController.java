package hu.sfm.controller;

import hu.sfm.main.Main;
import hu.sfm.utils.BevetelDAO;
import hu.sfm.utils.JPABevetelDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
    private void onActionAccount(ActionEvent event) throws IOException {
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
    private void onActionSelling(ActionEvent event) throws IOException {
        BevetelDAO bevetelDAO = new JPABevetelDAO();
        if(bevetelDAO.getBevetelek().stream().anyMatch(e -> e.getKasszaZaras() == null)){
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
        }else {
            Main.alertMsg="Kassza zárva! Az eladáshoz nyisd meg!";
            Main.showAlert("Notification");
        }

    }

    @FXML
    private void onActionStorage(ActionEvent event) throws IOException {
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
    private void onActionIncome(ActionEvent event) throws IOException {
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
    private void onActionHelp (ActionEvent event)
    {

    }

    @FXML
    private void onActionLogout (ActionEvent event) throws IOException {
        Stage stage = (Stage)dashMenuLoaderPane.getScene().getWindow();
        stage.setHeight(400);
        stage.setWidth(640);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        Main.setUpApplication();
        Main.setRoot("/fxml/loginpanel");
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
