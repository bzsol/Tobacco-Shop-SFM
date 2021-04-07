package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {
    @FXML
    private Pane dashMenuLoaderPane;

    @FXML
    private VBox dashVbox1;

    @FXML
    private VBox dashVbox2;

    @FXML
    private void initialize() {
        dashMenuLoaderPane.setLayoutX(224);
        if (Main.hasGroup /* Ha nincs még csoportja, akkor ezt jelenítsd meg, különben a másikat*/) {
            dashVbox1.setVisible(true);
        } else {
            dashVbox2.setVisible(true);
        }
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
    private void dashLoadMenu5(ActionEvent event) {

    }

    @FXML
    private void dashLoadMenu6(ActionEvent event) {

    }

    @FXML
    private void dashMenuLoad10(ActionEvent event) throws IOException {
        if (Main.actualPane == null) {
            Main.actualPane = dashMenuLoaderPane;
        }
        else{
            Main.actualPane.getChildren().remove(0);
        }
        Pane loadNewPane = FXMLLoader.load(getClass().getResource("/fxml/creategroup.fxml"));
        Main.mainBuyMenuPane = loadNewPane;
        dashMenuLoaderPane.getChildren().add(loadNewPane);
    }
}
