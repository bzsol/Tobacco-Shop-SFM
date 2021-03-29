package hu.sfm.controller;

import hu.sfm.entity.User;
import hu.sfm.main.Main;
import hu.sfm.utils.JPAUserDAO;
import hu.sfm.utils.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.List;

public class LoginController {
    @FXML
    private Button loginBtn;

    @FXML
    private Label loginTextLabel;

    @FXML
    private Label loginPwLabel;

    @FXML
    private PasswordField logInputPassw;

    @FXML
    private TextField logInputUname;

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
        Main.setRoot("/fxml/registrationpanel");
    }

    @FXML
    void onLogIn(ActionEvent event) {

        String userName = logInputUname.getText();
        String passWord = logInputPassw.getText();
        boolean validate = false;
        try {
            UserDAO uDAO = new JPAUserDAO();
            List<User> users = uDAO.getUser();
            for (User u : users) {
                if (u.getUsername().contains(userName) && u.getPassword().contains(passWord)) {

                    validate = true;
                }
            }
            if (validate) {
                System.out.println("Login Succesfull!");
                Main.setRoot("/fxml/dashboard");
            } else {
                System.out.println("Wrong!");
                logInputPassw.setText("");
                logInputUname.setText("");

            }
            uDAO.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
