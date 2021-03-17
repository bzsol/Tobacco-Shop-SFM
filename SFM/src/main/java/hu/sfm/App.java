package hu.sfm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    public static int sorszam;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginpanel"), 640, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Irányítópult");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        Stage stage = (Stage) scene.getWindow();
        if (fxml.equals("loginpanel")) {
            stage.setTitle("Login");
        } else if (fxml.equals("registrationpanel")) {
            stage.setTitle("Registration");
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}