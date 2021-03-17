package hu.sfm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class UserpanelController {
    @FXML
    private VBox userPanelVbox;

    @FXML
    private TextField addNewUser;

    /*
    Listához való hozzáadás és törlés megvalósítása
    Később rövidíteni fogom, de egyelőre le kellett tesztelnem a lehetőségeket
    Egyelőre az új felhasználó hozzáadása is eléggé primitív, sok dolgot kell majd lekezelni rajta
     */
    @FXML
    private void onActionAddNewUser(ActionEvent event) throws IOException {
        String[] datas = addNewUser.getText().split(" ");
        HBox h = new HBox();
        h.setMinHeight(50);
        h.setPrefWidth(1350);
        h.setStyle("-fx-background-color: rgba(96, 96, 96, .7)");
        Label l1 = new Label(datas[0]);
        Label l2 = new Label(datas[1]);
        Label l3 = new Label(datas[2]);
        Label l4 = new Label(datas[3]);
        Label l5 = new Label(new Date().toString());
        l1.setTextFill(Paint.valueOf("white"));
        l1.setFont(Font.font("Segoe UI", 14));
        l1.setStyle("-fx-min-width: 180px;-fx-label-padding: 14px 0;-fx-alignment: BASELINE_CENTER");
        l2.setTextFill(Paint.valueOf("white"));
        l2.setFont(Font.font("Segoe UI", 14));
        l2.setStyle("-fx-min-width: 230px;-fx-label-padding: 14px 0;-fx-alignment: BASELINE_CENTER");
        l3.setTextFill(Paint.valueOf("white"));
        l3.setFont(Font.font("Segoe UI", 14));
        l3.setStyle("-fx-min-width: 230px;-fx-label-padding: 14px 0;-fx-alignment: BASELINE_CENTER");
        l4.setTextFill(Paint.valueOf("white"));
        l4.setFont(Font.font("Segoe UI", 14));
        l4.setStyle("-fx-min-width: 220px;-fx-label-padding: 14px 0;-fx-alignment: BASELINE_CENTER");
        l5.setTextFill(Paint.valueOf("white"));
        l5.setFont(Font.font("Segoe UI", 14));
        l5.setStyle("-fx-min-width: 220px;-fx-label-padding: 14px 0;-fx-alignment: BASELINE_CENTER");
        Button btn = new Button("Eltávolítás");
        btn.setStyle("-fx-background-color: transparent;-fx-border-color: red;-fx-border-radius: 50%;-fx-border-width: 2px;-fx-border-insets: 10px 0px 0px 74px");
        btn.setTextFill(Paint.valueOf("white"));
        btn.setFont(Font.font("Segoe UI", 14));
        btn.setCursor(Cursor.HAND);
        h.setId("" + App.sorszam);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String id = ((Button)e.getSource()).getParent().getId();
                System.out.println(id);
                int elementId = 0;
                int numOfRuns = 0;
                for (var item : userPanelVbox.getChildren()) {
                    if (item.getId().equals(id)) {
                        elementId = numOfRuns;
                    }
                    numOfRuns++;
                }
                userPanelVbox.getChildren().remove(elementId);
                App.sorszam--;
            }
        });
        h.getChildren().add(l1);
        h.getChildren().add(l2);
        h.getChildren().add(l3);
        h.getChildren().add(l4);
        h.getChildren().add(l5);
        h.getChildren().add(btn);
        userPanelVbox.getChildren().add(h);
        App.sorszam++;
        addNewUser.setText("");
        addNewUser.setPromptText("Adatbevitel...");
    }
}
