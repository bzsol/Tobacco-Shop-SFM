package hu.sfm.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.h2.tools.Server;

import java.io.IOException;




public class Main extends Application {
        private static Scene scene;
        public static int sorszam;
        public static Pane actualPane;
        public static Pane mainBuyMenuPane;

        @Override
        public void start(Stage stage) throws Exception {
            new Server().runTool("-tcp", "-web", "-ifNotExists");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginpanel.fxml"));
            scene = new Scene(root);
            stage.setTitle("Vezérlőpult");
            stage.setScene(scene);
            stage.show();
        }

       public static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
            Stage stage = (Stage) scene.getWindow();
            if (fxml.equals("loginpanel")) {
                stage.setTitle("Login");
            } else if (fxml.equals("registrationpanel")) {
                stage.setTitle("Registration");
            }
        }

        private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        }

        public static void main(String[] args) {
            launch();
        }

    }
