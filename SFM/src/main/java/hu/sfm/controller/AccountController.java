package hu.sfm.controller;

import hu.sfm.entity.Permission;
import hu.sfm.entity.User;
import hu.sfm.main.Main;
import hu.sfm.utils.JPAUserDAO;
import hu.sfm.utils.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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
    private TextField addDate;

    @FXML
    private TextField beosztas;

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
        loader(Main.actUser.getUsername());


        accountChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> loader(newValue));
    }

    @FXML
    private void onActionDeleteAccount (ActionEvent event) {

        UserDAO userDAO = new JPAUserDAO();

        for (User u : userDAO.getUser()){
            if (u.getUsername().equals(userName.getText()) && !u.getPerm().equals(Permission.ADMIN)){
                userDAO.deleteUser(u);
                accountChoiceBox.getItems().remove(u.getUsername());
            }
        }
        loader(Main.actUser.getUsername());




    }

    @FXML
    private void onActionSaveAccount (ActionEvent event) {

    }


    private void loader(String actuallyUser){

        UserDAO userDAO = new JPAUserDAO();


        accountChoiceBox.setValue(actuallyUser);
        for (User u : userDAO.getUser() ){
            if(u.getUsername().equals(actuallyUser)){

                vezNev.setText(u.getVezetekNev() == null?"<Empty>": u.getVezetekNev());
                kerNev.setText(u.getKeresztNev() == null ?"<Empty>": u.getKeresztNev());
                email.setText(u.getEmail() == null ?"<Empty>": u.getEmail());
                userName.setText(u.getUsername());
                birthDate.setText(u.getBirthDate() == null ?"<Empty>": String.valueOf(u.getBirthDate()));
                salary.setText(u.getSallary() == 0 ? "<Empty>":String.valueOf(u.getSallary()));
                regDate.setText(String.valueOf(u.getregDate()));
                addDate.setText(u.getAddDate() == null ?"<Empty>": String.valueOf(u.getAddDate()));
                switch (u.getPerm()){
                    case ADMIN:
                        beosztas.setText("Tulajdonos");
                        break;
                    case ALKALMAZOTT:
                        beosztas.setText("Alkalmazott");
                        break;
                    case DEFAULT:
                        beosztas.setText("<Empty>");
                }
            }
        }

    }
}
