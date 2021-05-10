package hu.sfm.main;

import hu.sfm.entity.User;
import hu.sfm.utils.PopupHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.h2.tools.Server;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    private static Scene scene;

    public static Scene getScene() {
        return scene;
    }

    public static Pane actualPane;
    public static Pane mainBuyMenuPane;
    public static String productId = null;
    public static User actUser = null;
    public static Map<String, Integer> actualCart = new HashMap<>();
    public static Button clickedMenuBtn = null;

        @Override
        public void start(Stage stage) throws Exception {
            new Server().runTool("-tcp", "-web", "-ifNotExists");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginpanel.fxml"));
            stage.setOnCloseRequest(e->{
                e.consume();
                System.out.println("The application closed, the database is closed too!");
                System.exit(0);

            });


            stage.setResizable(false);
            scene = new Scene(root);
            stage.setTitle("Vezérlőpult");
            stage.setScene(scene);
            stage.show();
        }

       public static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
            Stage stage = (Stage) scene.getWindow();
            if (fxml.equals("loginpanel")) {
                stage.setTitle("Bejelentkezés");
            } else if (fxml.equals("registrationpanel")) {
                stage.setTitle("Regisztráció");
            }
        }

        private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        }

        public static void setUpApplication(){
            actualPane = null;
            mainBuyMenuPane = null;
            productId = null;
            actUser = null;
            actualCart = new HashMap<>();
            PopupHandler.alertMsg = null;
            clickedMenuBtn = null;
        }

        public static Label createErrorLabel(String title, String tooltipMessage) {
            Label label = new Label(title);
            label.setStyle("-fx-border-width: 2px; -fx-border-color: red; -fx-border-radius: 50%; -fx-font-family: Segoe UI;" +
                    " -fx-font-size: 14px; -fx-text-fill: white; -fx-alignment: center; -fx-padding: 0; ");
            label.setMinWidth(287);
            Tooltip tooltip = new Tooltip(tooltipMessage);
            tooltip.setStyle("-fx-text-fill: red; -fx-background-color: white; -fx-font-size: 12px");
            tooltip.setShowDelay(Duration.seconds(0.1));
            tooltip.setShowDuration(Duration.seconds(30));
            label.setTooltip(tooltip);
            return label;
        }



        public static void main(String[] args) {
            launch();
        }

    }
