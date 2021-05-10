package hu.sfm.controller;

import hu.sfm.entity.Bevetel;
import hu.sfm.main.Main;
import hu.sfm.utils.BevetelDAO;
import hu.sfm.utils.CurrencyManager;
import hu.sfm.utils.JPABevetelDAO;
import hu.sfm.utils.PopupHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class IncomeController {



    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;
    @FXML
    private TextField bruttoDaily;

    @FXML
    private TextField nettoDaily;

    @FXML
    private TextField bruttoRange;

    @FXML
    private TextField nettoRange;

    @FXML
    private TextField nettoAll;

    @FXML
    private TextField bruttoAll;

    @FXML
    private TextField cassaState;

    private boolean cassaOpened = false;

    @FXML
    private void initialize(){

        toDatePicker.getEditor().setDisable(true);
        fromDatePicker.getEditor().setDisable(true);
        BevetelDAO bevetelDAO = new JPABevetelDAO();
        int income =bevetelDAO.getBevetelek().stream().filter(e -> e.getKasszaZaras() == null).mapToInt(Bevetel::getOsszeg).sum();
        bruttoDaily.setText(CurrencyManager.createPattern(String.valueOf(income)));
        nettoDaily.setText(CurrencyManager.createPattern(String.valueOf(Math.round(income*0.73))));
        bruttoAll.setText(CurrencyManager.createPattern(String.valueOf(bevetelDAO.getBevetelek().stream().mapToInt(Bevetel::getOsszeg).sum())));
        nettoAll.setText(CurrencyManager.createPattern(String.valueOf(Math.round((bevetelDAO.getBevetelek().stream().mapToInt(Bevetel::getOsszeg).sum())*0.73))));
        bruttoRange.setText("0");
        nettoRange.setText("0");
        for (Bevetel bev : bevetelDAO.getBevetelek()){
            if (bev.getKasszaZaras() == null) {
                cassaOpened = true;
                break;
            }
        }
        if(cassaOpened){
            cassaState.setText("Nyitva");
        }else {
            cassaState.setText("Zárva");
        }
    }

    @FXML
    void onClose(ActionEvent event) {
        PopupHandler.alertMsg = "Biztos vagy benne, hogy le szeretnéd zárni a kasszát?";
        PopupHandler.showAlert(PopupHandler.Type.ALERT);
        if(PopupHandler.resultType == PopupHandler.Result.ACCEPTED) {
            BevetelDAO bevetelDAO = new JPABevetelDAO();
            for (Bevetel bev : bevetelDAO.getBevetelek()) {
                if (bev.getKasszaZaras() == null) {
                    bev.setKasszaZaras(LocalDate.now());
                    bevetelDAO.updateBevetel(bev);
                }
            }
            bruttoDaily.setText("0 Ft");
            nettoDaily.setText("0 Ft");
            cassaOpened = false;
            cassaState.setText("Zárva");
        }

    }

    @FXML
    void onOpen(ActionEvent event) {

        BevetelDAO bevetelDAO = new JPABevetelDAO();
        Bevetel b = new Bevetel();

        for (Bevetel bev : bevetelDAO.getBevetelek()){
            if(bev.getKasszaZaras()==null){
                PopupHandler.alertMsg = "A kassza jelenleg is nyitva van! Amíg a kassza nem kerül lezárásra, addig nem nyitható meg újra!";
                PopupHandler.showAlert(PopupHandler.Type.NOTIFICATION);
                cassaOpened=true;
            }
        }

        if(!cassaOpened){
            b.setElado(Main.actUser.getUsername());
            b.setKasszaNyitas(LocalDate.now());
            bevetelDAO.saveBevetel(b);
            cassaState.setText("Nyitva");
        }

    }

    @FXML
    void leker(ActionEvent event){
        BevetelDAO bDAO = new JPABevetelDAO();

        try {
           long from = Date.from(fromDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
           long to = Date.from(toDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
           if (from > to) {
               PopupHandler.alertMsg = "Lekérdezés sikertelen! Hibás intervallumot választott ki.";
               PopupHandler.showAlert(PopupHandler.Type.NOTIFICATION);
           }
            int bevetel =0;

            for (Bevetel b : bDAO.getBevetelek()){
                if(((from<=Date.from(b.getKasszaZaras().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime())) &&
                        ((to>=Date.from(b.getKasszaNyitas().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()))){
                    bevetel+=b.getOsszeg();
                }

            }
            bruttoRange.setText(CurrencyManager.createPattern(String.valueOf(bevetel)));
            nettoRange.setText(CurrencyManager.createPattern(String.valueOf(Math.round(bevetel*0.73))));
       }catch (Exception e){
           PopupHandler.alertMsg = "Lekérdezés sikertelen! Nem választott ki időintervallumot.";
           PopupHandler.showAlert(PopupHandler.Type.NOTIFICATION);
       }




    }


}
