package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {
    private Stage window;

    public void CloseProgram(){
        // the javax.swing package has some very good dialog boxes with options.
        int ans = JOptionPane.showConfirmDialog(null,"Are you sure, you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
        if(ans == JOptionPane.YES_OPTION) {
            System.out.println("The program closed successfully");
            window.close();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Basic Program");
        window.setOnCloseRequest(e -> {
            // consume() means we return to the main window
            e.consume();
            CloseProgram();
        });
        Button button = new Button("Exit");
        button.setOnAction(e -> CloseProgram());
        StackPane layout1 = new StackPane();
        layout1.getChildren().add(button);
        Scene scene = new Scene(layout1, 250, 250);
        window.setScene(scene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
