package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardController {
    @FXML
    private Button dashMenuBtn1;

    @FXML
    private Button dashMenuBtn2;

    @FXML
    private Button dashMenuBtn3;

    @FXML
    private Button dashMenuBtn4;

    @FXML
    private Button dashMenuBtn5;

    @FXML
    private Button dashMenuBtn6;

    @FXML
    private Button dashMenuBtn7;

    @FXML
    private Button dashMenuBtn8;

    @FXML
    private Pane dashMenuLoaderPane;

    @FXML
    private void onMouseEnteredDashMenu1(MouseEvent event) {
        dashMenuBtn1.setStyle(
                "-fx-background-color: rgba(0, 0, 255, .3);" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseExitedDashMenu1(MouseEvent event) {
        dashMenuBtn1.setStyle(
                "-fx-background-color: #2199dd;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    /*
    Betöltünk egy új FXML-t, amit elhelyezünk a Dashboard egyik felületén
    Később kezelni kell azt is, hogy ha már van valami betöltve, akkor azt távolítsa el
    helyette pedig töltse be az újat, ha annak adatai is vannak, akkor azokkal együtt
     */

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
    private void onMouseEnteredDashMenu2(MouseEvent event) {
        dashMenuBtn2.setStyle(
                "-fx-background-color: rgba(0, 0, 255, .3);" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseExitedDashMenu2(MouseEvent event) {
        dashMenuBtn2.setStyle(
                "-fx-background-color: #2199dd;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    /*
    Az új fülre kattintáskor az eltávolítást megvalósító eljárás
    Később valamit hozzá kell majd adnunk.
     */
    @FXML
    private void dashLoadMenu2(ActionEvent event) {
        if (Main.actualPane == null) {
            Main.actualPane = dashMenuLoaderPane;
        }
        else
        {
            Main.actualPane.getChildren().remove(0);
        }

    }

    @FXML
    private void onMouseEnteredDashMenu3(MouseEvent event) {
        dashMenuBtn3.setStyle(
                "-fx-background-color: rgba(0, 0, 255, .3);" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseExitedDashMenu3(MouseEvent event) {
        dashMenuBtn3.setStyle(
                "-fx-background-color: #2199dd;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void dashLoadMenu3(ActionEvent event) throws IOException {

        if (Main.actualPane == null) {
            Main.actualPane = dashMenuLoaderPane;
        }
        else{
            Main.actualPane.getChildren().remove(0);
        }

        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/userpanel.fxml"));
        dashMenuLoaderPane.getChildren().add(loadNewPane);
    }

    @FXML
    private void onMouseEnteredDashMenu4(MouseEvent event) {
        dashMenuBtn4.setStyle(
                "-fx-background-color: rgba(0, 0, 255, .3);" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseExitedDashMenu4(MouseEvent event) {
        dashMenuBtn4.setStyle(
                "-fx-background-color: #2199dd;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void dashLoadMenu4(ActionEvent event) throws IOException {

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
    private void onMouseEnteredDashMenu5(MouseEvent event) {
        dashMenuBtn5.setStyle(
                "-fx-background-color: rgba(0, 0, 255, .3);" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseExitedDashMenu5(MouseEvent event) {
        dashMenuBtn5.setStyle(
                "-fx-background-color: #2199dd;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void dashLoadMenu5(ActionEvent event) {

    }

    @FXML
    private void onMouseEnteredDashMenu6(MouseEvent event) {
        dashMenuBtn6.setStyle(
                "-fx-background-color: rgba(0, 0, 255, .3);" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseExitedDashMenu6(MouseEvent event) {
        dashMenuBtn6.setStyle(
                "-fx-background-color: #2199dd;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void dashLoadMenu6(ActionEvent event) {

    }

    @FXML
    private void onMouseEnteredDashMenu7(MouseEvent event) {
        dashMenuBtn7.setStyle(
                "-fx-background-color: rgba(0, 0, 255, .3);" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseExitedDashMenu7(MouseEvent event) {
        dashMenuBtn7.setStyle(
                "-fx-background-color: #2199dd;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseEnteredDashMenu8(MouseEvent event) {
        dashMenuBtn8.setStyle(
                "-fx-background-color: rgba(0, 0, 255, .3);" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }

    @FXML
    private void onMouseExitedDashMenu8(MouseEvent event) {
        dashMenuBtn8.setStyle(
                "-fx-background-color: #2199dd;" +
                        "-fx-border-width: 0px 0px 2px 0px;" +
                        "-fx-border-color: white;" +
                        "-fx-alignment: BASELINE_LEFT"
        );
    }
}
