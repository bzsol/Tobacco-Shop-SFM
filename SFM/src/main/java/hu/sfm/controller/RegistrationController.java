package hu.sfm.controller;

import hu.sfm.entity.Permission;
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
import java.sql.SQLException;

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

    /**
     * Designt megvalósító eventek
     **/

    // Hyperlink a bejelentkező GUI-ra
    @FXML
    private void onActionLoginLink(ActionEvent event) throws IOException {
        Main.setRoot("/fxml/loginpanel");
    }

    @FXML
    void regBtnClicked(ActionEvent event) throws SQLException {


        User u = new User();
        String userName = regUserInput.getText();
        String passwd = regPassInput.getText();
        String check = regPwAgainField.getText();
        boolean volte = false;
        if (passwd.equals(check)) {
            try {
                UserDAO uDAO = new JPAUserDAO();
                for (User user : uDAO.getUser()) {
                    if (user.getUsername().contains(userName)) {
                        volte = true;
                        break;
                    }
                }
                if (!volte) {
                    u.setUsername(userName);
                    u.setPassword(check);
                    u.setPerm(Permission.ADMIN);
                    uDAO.saveUser(u);
                    Main.setRoot("/fxml/loginpanel");

                }
                regUserInput.setText("");
                regPassInput.setText("");
                regPwAgainField.setText("");

                uDAO.close();


            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("nem jó a jelszó!");
        }
        //System.exit(0);


    }

}


