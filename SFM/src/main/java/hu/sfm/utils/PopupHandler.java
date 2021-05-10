package hu.sfm.utils;

import hu.sfm.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PopupHandler {
    public enum Type {
        ALERT,
        NOTIFICATION
    }
    public enum Result {
        ACCEPTED,
        DECLINED
    }
    public static String alertMsg = null;
    public static Result resultType;
    public static void showAlert(Type t) {
        final double LOADER_PANE_WIDTH_DIFF = 240;
        final double LOADER_PANE_HEIGHT_DIFF = 205;
        final double ALERT_WIDTH_CENTER = 400;
        final double ALERT_HEIGHT_CENTER = 100;


        FXMLLoader loader = t == Type.NOTIFICATION ? new FXMLLoader(Main.class.getResource("/fxml/notification.fxml")) :
                new FXMLLoader(Main.class.getResource("/fxml/alert.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle(t == Type.NOTIFICATION ? "Értesítés" : "Figyelmeztetés");
        assert root != null;
        stage.setScene(new Scene(root));
        Stage primaryStage = (Stage) Main.getScene().getWindow();
        stage.setX(primaryStage.getX() + LOADER_PANE_WIDTH_DIFF + (primaryStage.getWidth() - LOADER_PANE_WIDTH_DIFF) / 2 - ALERT_WIDTH_CENTER);
        stage.setY(primaryStage.getY() + (primaryStage.getHeight() - LOADER_PANE_HEIGHT_DIFF) / 2 - ALERT_HEIGHT_CENTER);
        stage.showAndWait();
    }
}
