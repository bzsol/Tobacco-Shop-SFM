package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CreateGroupController {
    @FXML
    private void onActionCreateGroupBtn(ActionEvent event) throws IOException {
        Main.actualPane = null;
        Main.hasGroup = true;
        Main.setRoot("/fxml/dashboard");
    }
}
