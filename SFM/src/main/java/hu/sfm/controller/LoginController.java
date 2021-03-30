package hu.sfm.controller;

import hu.sfm.entity.User;
import hu.sfm.main.Main;
import hu.sfm.utils.JPAUserDAO;
import hu.sfm.utils.UserDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import javax.tools.Tool;
import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @FXML
    private VBox loginErrorMsgVbox;

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
            if(userName.equals("")||passWord.equals("")){
                if (loginErrorMsgVbox.getChildren().size() > 0) {
                    loginErrorMsgVbox.getChildren().remove(1);
                }
                Label label = new Label("Sikertelen bejelentkezés");
                label.setStyle("-fx-border-width: 2px; -fx-border-color: red; -fx-border-radius: 50%; -fx-font-family: Segoe UI; -fx-font-size: 14px; -fx-text-fill: white; -fx-alignment: center; -fx-padding: 0; -fx-font-family: Segoe UI;");
                label.setMinWidth(287);
                Tooltip tooltip = new Tooltip("- A felhasználónév, vagy a jelszó mezőt üresen hagyta");
                tooltip.setStyle("-fx-text-fill: red; -fx-background-color: white; -fx-font-size: 12px");
                tooltip.setShowDelay(Duration.seconds(0.1));
                tooltip.setShowDuration(Duration.seconds(30));
                label.setTooltip(tooltip);
                loginErrorMsgVbox.getChildren().add(label);
                logInputPassw.setText("");
                logInputUname.setText("");
            }else {

                for (User u : uDAO.getUser()) {
                    if (u.getUsername().contains(userName) && u.getPassword().contains(passWord)) {

                        validate = true;
                        break;
                    }
                }
                if (validate) {
                    System.out.println("Login Succesfull!");
                    Main.setRoot("/fxml/dashboard");
                } else {
                    if (loginErrorMsgVbox.getChildren().size() > 0) {
                        loginErrorMsgVbox.getChildren().remove(1);
                    }
                    Label label = new Label("Sikertelen bejelentkezés!");
                    label.setStyle("-fx-border-width: 2px; -fx-border-color: red; -fx-border-radius: 50%; -fx-font-family: Segoe UI; -fx-font-size: 14px; -fx-text-fill: white; -fx-alignment: center; -fx-padding: 0; -fx-font-family: Segoe UI;");
                    label.setMinWidth(287);
                    Tooltip tooltip = new Tooltip("- Hibás felhasználónevet, vagy jelszót adott meg");
                    tooltip.setStyle("-fx-text-fill: red; -fx-background-color: white; -fx-font-size: 12px");
                    tooltip.setShowDelay(Duration.seconds(0.1));
                    tooltip.setShowDuration(Duration.seconds(30));
                    label.setTooltip(tooltip);
                    loginErrorMsgVbox.getChildren().add(label);
                    logInputPassw.setText("");
                    logInputUname.setText("");
                }
            }
            uDAO.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
