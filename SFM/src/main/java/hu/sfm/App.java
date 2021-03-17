package hu.sfm;

import javafx.application.Application;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    // Dashboard listaelem ElementID hozzárendelő
    public static int sorszam = 0;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("dashboard"), 1600, 900);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        Stage stage = (Stage) scene.getWindow();

        // Ablak nevének megváltoztatása, mikor váltunk köztük
        if (fxml.equals("registrationpanel")) {
            stage.setTitle("Registration");
        } else if (fxml.equals("loginpanel")) {
            stage.setTitle("Login");
        }
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}