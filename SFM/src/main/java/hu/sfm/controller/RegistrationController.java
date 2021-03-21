package hu.sfm.controller;

import hu.sfm.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private Button registrationBtn;

    @FXML
    private Label registrationTextLabel;

    @FXML
    private Label registrationPwLabel;

    @FXML
    private Label registrationPwAgainLabel;

    /** Designt megvalósító eventek **/
    @FXML
    private void onMouseEnteredRegistration(MouseEvent event) {
        registrationBtn.setStyle("-fx-background-color: #2199dd");
        registrationBtn.setTextFill(Paint.valueOf("white"));
    }

    @FXML
    private void onMouseExitedRegistration(MouseEvent event) {
        registrationBtn.setStyle("-fx-background-color: white");
        registrationBtn.setTextFill(Paint.valueOf("#2199dd"));
    }

    @FXML
    private void onMouseEnteredText(MouseEvent event) {
        registrationTextLabel.setTextFill(Paint.valueOf("white"));
    }

    @FXML
    private void onMouseExitedText(MouseEvent event) {
        registrationTextLabel.setTextFill(Paint.valueOf("#2199dd"));
    }

    @FXML
    private void onMouseEnteredPw(MouseEvent event) {
        registrationPwLabel.setTextFill(Paint.valueOf("white"));
    }

    @FXML
    private void onMouseExitedPw(MouseEvent event) {
        registrationPwLabel.setTextFill(Paint.valueOf("#2199dd"));
    }

    @FXML
    private void onMouseEnteredPwAgain(MouseEvent event) {
        registrationPwAgainLabel.setTextFill(Paint.valueOf("white"));
    }

    @FXML
    private void onMouseExitedPwAgain(MouseEvent event) {
        registrationPwAgainLabel.setTextFill(Paint.valueOf("#2199dd"));
    }
    /** Designt megvalósító eventek **/

    // Hyperlink a bejelentkező GUI-ra
    @FXML
    private void onActionLoginLink(ActionEvent event) throws IOException {
        Main.setRoot("/fxml/loginpanel");
    }
}
