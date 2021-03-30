package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.fxml.FXML;

public class BuymenuController2 {
    public BuymenuController2() {
    }

    @FXML
    private void onActionBack() {
        Main.actualPane.getChildren().remove(0);
        Main.actualPane.getChildren().add(Main.mainBuyMenuPane);
    }
}
