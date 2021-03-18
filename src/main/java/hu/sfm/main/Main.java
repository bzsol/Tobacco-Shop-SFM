package hu.sfm.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

    public class Main extends Application {
        public static int sorszam;

        @Override
        public void start(Stage stage) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Vezérlőpult");
            stage.setScene(scene);
            stage.show();
        }

       /* static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
            Stage stage = (Stage) scene.getWindow();
            if (fxml.equals("loginpanel")) {
                stage.setTitle("Login");
            } else if (fxml.equals("registrationpanel")) {
                stage.setTitle("Registration");
            }
        }
        */
        /* private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        }
        */
        public static void main(String[] args) {
            launch();
        }

    }
