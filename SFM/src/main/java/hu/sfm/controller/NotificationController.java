package hu.sfm.controller;

import hu.sfm.main.Main;
import hu.sfm.utils.PopupHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NotificationController {
    @FXML
    private Text alertMsg;

    @FXML
    private void initialize() {
        alertMsg.setText(PopupHandler.alertMsg);
    }

    @FXML
    private void onActionNotificationOk(ActionEvent event) {
        Stage stage = (Stage) alertMsg.getScene().getWindow();
        stage.close();
    }
}
