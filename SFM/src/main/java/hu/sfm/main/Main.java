package hu.sfm.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

            stage.setOnCloseRequest(e->{
                e.consume();
                System.out.println("The application closed, the database is closed too!");
                System.exit(0);

            });



            scene = new Scene(root);
            stage.setTitle("Bejelentkezés");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

       public static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
            Stage stage = (Stage) scene.getWindow();
           switch (fxml) {
               case "/fxml/loginpanel":
                   stage.setTitle("Bejelentkezés");
                   break;
               case "/fxml/registrationpanel":
                   stage.setTitle("Regisztráció");
                   break;
               case "/fxml/dashboard":
                   stage.setTitle("Vezérlőpult");
                   break;
           }
        }
        public static void setWindowSize(int width,int height){
            Stage stage = (Stage) scene.getWindow();
            stage.setWidth(width);
            stage.setHeight(height);
            stage.centerOnScreen();
        }

        private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        }

        public static void main(String[] args) {
            launch();
        }

    }
