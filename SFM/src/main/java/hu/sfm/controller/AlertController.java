package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AlertController {
    @FXML
    private Text alertMsg;

    @FXML
    private void initialize() {
        alertMsg.setText(Main.alertMsg);
    }
}
