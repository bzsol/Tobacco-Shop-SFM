package hu.sfm.controller;

import hu.sfm.entity.Permission;
import hu.sfm.entity.User;
import hu.sfm.main.Main;
import hu.sfm.utils.Encryption;
import hu.sfm.utils.JPAUserDAO;
import hu.sfm.utils.UserDAO;
import hu.sfm.utils.UserPassChecker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RegistrationController {
    @FXML
    private Button registrationBtn;

    @FXML
    private Label registrationTextLabel;

    @FXML
    private Label registrationPwLabel;

    @FXML
    private Label registrationPwAgainLabel;

    @FXML
    private PasswordField regPassInput;

    @FXML
    private TextField regUserInput;

    @FXML
    private PasswordField regPwAgainField;

    @FXML
    private Label unameInfoLabel;

    @FXML
    private Label pwInfoLabel;

    @FXML
    private VBox registrationErrorMsgVbox;

    /**
     * Designt megvalósító eventek
     **/
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

    @FXML
    private void onMouseEnteredUnameInfo(MouseEvent event) {
        Tooltip tooltip = new Tooltip("- A felhasználónév maximum 15 karakterből állhat\n" +
                "- A felhasználónév minimum 5 karakterből állhat\n" +
                "- A felhasználónév csak az angol ABC kis- és nagybetűit tartalmazhatja," +
                "illetve számokat!\n" +
                "- A felhasználónév nem tartalamazhat szőközt!");
        tooltip.setShowDelay(Duration.seconds(0.1));
        tooltip.setShowDuration(Duration.seconds(30));
        tooltip.setStyle("-fx-background-color: white; -fx-font-size: 12px; -fx-text-fill: #2199dd");
        unameInfoLabel.setTooltip(tooltip);
    }

    @FXML
    private void onMouseEnteredPwInfo(MouseEvent event) {
        Tooltip tooltip = new Tooltip("- A jelszónak legalább 8 karakterből kell állnia\n" +
                "- A jelszónak tartalmaznia kell legalább egy nagybetűs karaktert\n" +
                "- A jelszónak tartalmaznia kell legalább egy kisbetűs karaktert\n" +
                "- A jelszónak tartalmaznia kell legalább egy számot\n" +
                "- A jelszó NEM tartalmazhat szóközt!\n" +
                "- A jelszó legfeljebb 15 karakterből állhat\n");
        tooltip.setShowDelay(Duration.seconds(0.1));
        tooltip.setShowDuration(Duration.seconds(30));
        tooltip.setStyle("-fx-background-color: white; -fx-font-size: 12px; -fx-text-fill: #2199dd");
        pwInfoLabel.setTooltip(tooltip);
    }

    /**
     * Designt megvalósító eventek
     **/

    // Hyperlink a bejelentkező GUI-ra
    @FXML
    private void onActionLoginLink(ActionEvent event) throws IOException {
        Main.setRoot("/fxml/loginpanel");
    }

    @FXML
    void onRegistration(ActionEvent event) {
        for (var timer : Timers) {
            timer.stop();
        }

        User u = new User();

        String userName = regUserInput.getText();
        String passwd = regPassInput.getText();
        String check = regPwAgainField.getText();
        boolean volte = false;

        if (userName.isEmpty() || passwd.isEmpty() || check.isEmpty()) {
            if (registrationErrorMsgVbox.getChildren().size() > 0) {
                registrationErrorMsgVbox.getChildren().remove(0);
            }
            Label label = Main.createErrorLabel("Sikertelen regisztráció!", "- Nem maradhat üresen mező!");
            registrationErrorMsgVbox.getChildren().add(label);
            TimeUntilDisappear();
        } else if (passwd.equals(check) && UserPassChecker.UsernameCheck(userName) && UserPassChecker.passCheck(passwd)) {

            try {
                UserDAO uDAO = new JPAUserDAO();
                for (User user : uDAO.getUser()) {
                    if (user.getUsername().contains(userName)) {
                        volte = true;
                        break;
                    }
                }
                if (!volte && uDAO.getUser().size()!=0) {
                    u.setUsername(userName);
                    u.setPassword(Encryption.titkosit(passwd));
                    u.setPerm(Permission.DEFAULT);
                    u.setregDate(LocalDate.now());
                    uDAO.saveUser(u);
                    Main.setRoot("/fxml/loginpanel");
                }else if(!volte){
                    u.setUsername(userName);
                    u.setPassword(Encryption.titkosit(passwd));
                    u.setPerm(Permission.ADMIN);
                    u.setregDate(LocalDate.now());
                    uDAO.saveUser(u);
                    Main.setRoot("/fxml/loginpanel");
                }
                else {
                    regUserInput.setText("");
                    regPassInput.setText("");
                    regPwAgainField.setText("");
                    if (registrationErrorMsgVbox.getChildren().size() > 0) {
                        registrationErrorMsgVbox.getChildren().remove(0);
                    }
                    Label label = Main.createErrorLabel("Sikertelen regisztráció!", "- A megadott felhasználónév már létezik!");
                    registrationErrorMsgVbox.getChildren().add(label);
                    TimeUntilDisappear();
                }

                uDAO.close();


            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            if (!passwd.equals(check)) {
                regUserInput.setText("");
                regPassInput.setText("");
                regPwAgainField.setText("");
                if (registrationErrorMsgVbox.getChildren().size() > 0) {
                    registrationErrorMsgVbox.getChildren().remove(0);
                }
                Label label = Main.createErrorLabel("Sikertelen regisztráció!", "- A megadott jelszavak nem egyeznek!");
                registrationErrorMsgVbox.getChildren().add(label);
                TimeUntilDisappear();
            } else if (!(UserPassChecker.UsernameCheck(userName)) || !(UserPassChecker.passCheck(passwd))) {
                regUserInput.setText("");
                regPassInput.setText("");
                regPwAgainField.setText("");
                if (registrationErrorMsgVbox.getChildren().size() > 0) {
                    registrationErrorMsgVbox.getChildren().remove(0);
                }
                Label label = Main.createErrorLabel("Sikertelen regisztráció!", "- A megadott felhasználónév vagy jelszó nem megfelelő!");
                registrationErrorMsgVbox.getChildren().add(label);
                TimeUntilDisappear();
            }

        }

    }

    private List<Timeline> Timers = new ArrayList<>();

    private void TimeUntilDisappear() {
        Timeline disappear = new Timeline(
                new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (registrationErrorMsgVbox.getChildren().size() > 0) {
                            registrationErrorMsgVbox.getChildren().remove(0);
                        }
                    }
                })
        );
        disappear.setCycleCount(Timeline.INDEFINITE);
        disappear.play();
        Timers.add(disappear);
    }

}
