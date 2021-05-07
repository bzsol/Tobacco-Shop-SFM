package hu.sfm.controller;

import hu.sfm.entity.Permission;
import hu.sfm.entity.User;
import hu.sfm.main.Main;
import hu.sfm.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AccountController {
    @FXML
    private ChoiceBox<String> accountChoiceBox;

    @FXML
    private TextField vezNev;

    @FXML
    private TextField kerNev;

    @FXML
    private TextField userName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField  newPass;

    @FXML
    private PasswordField newPassCheck;

    @FXML
    private ChoiceBox<Permission> permissionChoiceBox;

    @FXML
    private TextField addDate;

    @FXML
    private TextField salary;

    @FXML
    private TextField regDate;

    @FXML
    private TextField birthDate;

    @FXML
    private void initialize() {
        UserDAO userDAO = new JPAUserDAO();
        for (User u : userDAO.getUser()){
            accountChoiceBox.getItems().add(u.getUsername());
        }

        permissionChoiceBox.getItems().addAll(Permission.values());

        loader(Main.actUser.getUsername());



        newPass.setStyle("-fx-background-color: transparent;-fx-background-insets:0;-fx-border-width: 0px 0px 2px 0px;-fx-border-color: #2199dd;-fx-text-fill: white;-fx-padding:0;-fx-alignment: BASELINE_RIGHT;");
        newPass.setStyle("-fx-background-color: transparent;-fx-background-insets:0;-fx-border-width: 0px 0px 2px 0px;-fx-border-color: #2199dd;-fx-text-fill: white;-fx-padding:0;-fx-alignment: BASELINE_RIGHT;");


        if (!Main.actUser.getPerm().equals(Permission.ADMIN))
        {
            accountChoiceBox.setVisible(false);
            permissionChoiceBox.setDisable(true);
            salary.setDisable(true);
        }
        accountChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> loader(newValue));
    }

    @FXML
    private void onActionChangePass(ActionEvent event) {

        if(!newPass.getText().isEmpty() || !newPassCheck.getText().isEmpty()){

            if(newPass.getText().equals(newPassCheck.getText()) && UserPassChecker.passCheck(newPassCheck.getText())){
                UserDAO userDAO = new JPAUserDAO();
                for (User u : userDAO.getUser()){
                    if(u.getUsername().equals(accountChoiceBox.getValue())){
                        u.setPassword(Encryption.titkosit(newPass.getText()));
                        userDAO.saveUser(u);
                        newPass.setStyle("-fx-background-color: transparent;-fx-background-insets:0;-fx-border-width: 0px 0px 2px 0px;-fx-border-color: rgb(40, 220, 40);-fx-text-fill: white;-fx-padding:0;-fx-alignment: BASELINE_RIGHT;");
                        newPassCheck.setStyle("-fx-background-color: transparent;-fx-background-insets:0;-fx-border-width: 0px 0px 2px 0px;-fx-border-color: rgb(40, 220, 40);-fx-text-fill: white;-fx-padding:0;-fx-alignment: BASELINE_RIGHT;");
                    }
                }
            }else{
                newPass.setStyle("-fx-background-color: transparent;-fx-background-insets:0;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:rgb(220, 40, 40);-fx-text-fill: white;-fx-padding:0;-fx-alignment: BASELINE_RIGHT;");
                newPassCheck.setStyle("-fx-background-color: transparent;-fx-background-insets:0;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:rgb(220, 40, 40);-fx-text-fill: white;-fx-padding:0;-fx-alignment: BASELINE_RIGHT;");
            }

        }

    }

    @FXML
    private void onActionDeleteAccount (ActionEvent event) {

        if(!Main.actUser.getPerm().equals(Permission.ADMIN))
        {
            Main.alertMsg = "Nincs jogosultságod a felhasználói fiók törlésére!";
            Main.showAlert("Notification");
        }
        else
        {
            UserDAO userDAO = new JPAUserDAO();

            for (User u : userDAO.getUser())
            {
                if (u.getUsername().equals(userName.getText()) && !u.getPerm().equals(Permission.ADMIN))
                {
                    userDAO.deleteUser(u);
                    accountChoiceBox.getItems().remove(u.getUsername());
                }
            }
            loader(Main.actUser.getUsername());
        }



    }


    @FXML
    private void onActionSaveAccount (ActionEvent event) {
        UserDAO userDAO = new JPAUserDAO();
        boolean hozza_adjam=true;


        for (User u : userDAO.getUser())
        {

            if(u.getUsername().equals(accountChoiceBox.getValue()))
            {
                if(!UserPassChecker.NameCheck(vezNev.getText())){
                    hozza_adjam=false;
                    vezNev.setStyle("-fx-border-color:  rgb(220, 40, 40); -fx-border-width:  0px 0px 2px 0px;-fx-background-color:  transparent;-fx-text-fill: white;-fx-padding: 0");
                    //System.out.println("nem bírod leírni a neved rendesen te gyökér?");
                }
                if(!UserPassChecker.NameCheck((kerNev.getText()))){
                    hozza_adjam=false;
                    kerNev.setStyle("-fx-border-color:  rgb(220, 40, 40); -fx-border-width:  0px 0px 2px 0px;-fx-background-color:  transparent;-fx-text-fill: white;-fx-padding: 0");
                }
                if(!UserPassChecker.EmailChecker(email.getText())){
                    hozza_adjam=false;
                    email.setStyle("-fx-alignment:  BASELINE_RIGHT;-fx-border-color:  rgb(220, 40, 40); -fx-border-width:  0px 0px 2px 0px;-fx-background-color:  transparent;-fx-text-fill: white;-fx-padding: 0");
                    //System.out.println("nem jó az email te gyökér");
                }

                if(!UserPassChecker.dobCheck(birthDate.getText())){
                    hozza_adjam=false;
                    birthDate.setStyle("-fx-opacity:1;-fx-alignment:  BASELINE_RIGHT;-fx-border-color:  rgb(220, 40, 40); -fx-border-width:  0px 0px 2px 0px;-fx-background-color:  transparent;-fx-text-fill: white;-fx-padding: 0");
                    //System.out.println("Ennyre balfasz vagy hogy nem tudod leírni a saját születési dátumod?");
                }

                if(hozza_adjam){
                    u.setVezetekNev(vezNev.getText());
                    vezNev.setStyle("-fx-border-color: #2199dd; -fx-border-width:  0px 0px 2px 0px;-fx-background-color:  transparent;-fx-text-fill: white;-fx-padding: 0");
                    u.setKeresztNev(kerNev.getText());
                    kerNev.setStyle("-fx-border-color: #2199dd; -fx-border-width:  0px 0px 2px 0px;-fx-background-color:  transparent;-fx-text-fill: white;-fx-padding: 0");
                    u.setEmail(email.getText());
                    email.setStyle("-fx-alignment:  BASELINE_RIGHT;-fx-border-color: #2199dd; -fx-border-width:  0px 0px 2px 0px;-fx-background-color:  transparent;-fx-text-fill: white;-fx-padding: 0");
                    u.setBirthDate(LocalDate.parse(birthDate.getText()));
                    birthDate.setStyle("-fx-opacity:1;-fx-alignment:  BASELINE_RIGHT;-fx-border-color: #2199dd; -fx-border-width:  0px 0px 2px 0px;-fx-background-color:  transparent;-fx-text-fill: white;-fx-padding: 0");
                    u.setAddDate(LocalDate.parse(addDate.getText()));
                    u.setSallary(Integer.parseInt(CurrencyManager.removeTextFieldPattern(salary.getText())));
                    u.setPerm(permissionChoiceBox.getValue());
                    userDAO.updateUser(u);
                } else {
                    Main.alertMsg = "A megadott adatok nem felelnek meg a szükséges kritériumoknak!";
                    Main.showAlert("Notification");
                }


            }


        }


    }


    private void loader(String actuallyUser){

        UserDAO userDAO = new JPAUserDAO();


        accountChoiceBox.setValue(actuallyUser);
        for (User u : userDAO.getUser() ){
            if(u.getUsername().equals(actuallyUser)){
                addDate.setText(String.valueOf(LocalDate.now()));
                vezNev.setText(u.getVezetekNev() == null?"<Empty>": u.getVezetekNev());
                kerNev.setText(u.getKeresztNev() == null ?"<Empty>": u.getKeresztNev());
                email.setText(u.getEmail() == null ?"<Empty>": u.getEmail());
                userName.setText(u.getUsername());
                birthDate.setText(u.getBirthDate() == null ?"yyyy-mm-dd": String.valueOf(u.getBirthDate()));
                salary.setText(u.getSallary() == 0 ? "0 Ft":CurrencyManager.createPattern(String.valueOf(u.getSallary())));
                regDate.setText(String.valueOf(u.getregDate()));
                addDate.setText(u.getAddDate() == null ?String.valueOf(LocalDate.now()): String.valueOf(u.getAddDate()));
                switch (u.getPerm()){
                    case ADMIN:
                        permissionChoiceBox.setValue(Permission.ADMIN);
                        break;
                    case ALKALMAZOTT:
                        permissionChoiceBox.setValue(Permission.ALKALMAZOTT);
                        break;
                    case DEFAULT:
                        permissionChoiceBox.setValue(Permission.DEFAULT);
                }
            }
        }

    }
}
