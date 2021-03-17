package hu.sfm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.io.IOException;


public class LoginController {
    @FXML
    private Button loginBtn;

    @FXML
    private Label loginTextLabel;

    @FXML
    private Label loginPwLabel;

    /** Designt megvalósító eventek **/
    @FXML
    private void onMouseEnteredLogin(MouseEvent event) {
        loginBtn.setStyle("-fx-background-color: #2199dd");
        loginBtn.setTextFill(Paint.valueOf("white"));
    }

    @FXML
    private void onMouseExitedLogin(MouseEvent event) {
        loginBtn.setStyle("-fx-background-color: white");
        loginBtn.setTextFill(Paint.valueOf("#2199dd"));
    }

    @FXML
    private void onMouseEnteredText(MouseEvent event) {
        loginTextLabel.setTextFill(Paint.valueOf("white"));
    }

    @FXML
    private void onMouseExitedText(MouseEvent event) {
        loginTextLabel.setTextFill(Paint.valueOf("#2199dd"));
    }

    @FXML
    private void onMouseEnteredPw(MouseEvent event) {
        loginPwLabel.setTextFill(Paint.valueOf("white"));
    }

    @FXML
    private void onMouseExitedPw(MouseEvent event) {
        loginPwLabel.setTextFill(Paint.valueOf("#2199dd"));
    }
    /** Designt megvalósító eventek **/

    // Hyperlink a regisztrációs GUI-ra
    @FXML
    private void onActionRegistrationLink(ActionEvent event) throws IOException {
        App.setRoot("registrationpanel");
    }
}
