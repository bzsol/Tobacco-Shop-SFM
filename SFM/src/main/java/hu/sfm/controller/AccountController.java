package hu.sfm.controller;

import hu.sfm.entity.Permission;
import hu.sfm.entity.User;
import hu.sfm.main.Main;
import hu.sfm.utils.CurrencyManager;
import hu.sfm.utils.JPAUserDAO;
import hu.sfm.utils.UserDAO;
import hu.sfm.utils.UserPassChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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
    private TextField newPass;

    @FXML
    private TextField newPassCheck;

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

    }

    @FXML
    private void onActionDeleteAccount (ActionEvent event) {

        if(!Main.actUser.getPerm().equals(Permission.ADMIN))
        {
            System.out.println("nem vagy admin");
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
                if(!UserPassChecker.NameCheck(vezNev.getText()) || !UserPassChecker.NameCheck(kerNev.getText())) {
                    hozza_adjam=false;
                    System.out.println("nem bírod leírni a neved rendesen te gyökér?");
                }
                if(!UserPassChecker.EmailChecker(email.getText())){
                    hozza_adjam=false;
                    System.out.println("nem jó az email te gyökér");
                }
                if (!UserPassChecker.UsernameCheck(userName.getText())){
                    hozza_adjam=false;
                    System.out.println("nem jó a username te büdös gyökér");
                }
                if(!UserPassChecker.dobCheck(birthDate.getText())){
                    hozza_adjam=false;
                    System.out.println("Ennyre balfasz vagy hogy nem tudod leírni a saját születési dátumod?");
                }

                if(hozza_adjam){
                    u.setVezetekNev(vezNev.getText());
                    u.setKeresztNev(kerNev.getText());
                    u.setEmail(email.getText());
                    u.setUsername(userName.getText());
                    u.setBirthDate(LocalDate.parse(birthDate.getText()));
                    u.setAddDate(LocalDate.parse(addDate.getText()));
                    u.setSallary(Integer.parseInt(CurrencyManager.removeTextFieldPattern(salary.getText())));
                    u.setPerm(permissionChoiceBox.getValue());
                    userDAO.updateUser(u);
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
