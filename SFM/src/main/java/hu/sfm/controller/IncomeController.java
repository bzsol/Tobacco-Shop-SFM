package hu.sfm.controller;

import hu.sfm.entity.Bevetel;
import hu.sfm.main.Main;
import hu.sfm.utils.BevetelDAO;
import hu.sfm.utils.CurrencyManager;
import hu.sfm.utils.JPABevetelDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.Instant;
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
    private void initialize(){
        BevetelDAO bevetelDAO = new JPABevetelDAO();
        bruttoDaily.setText(CurrencyManager.createPattern(String.valueOf(Main.income)));
        nettoDaily.setText(CurrencyManager.createPattern(String.valueOf(Math.round(Main.income*0.73))));
        bruttoAll.setText(CurrencyManager.createPattern(String.valueOf(bevetelDAO.getBevetelek().stream().mapToInt(Bevetel::getOsszeg).sum())));
        nettoAll.setText(CurrencyManager.createPattern(String.valueOf(Math.round((bevetelDAO.getBevetelek().stream().mapToInt(Bevetel::getOsszeg).sum())*0.73))));
        bruttoRange.setText("0");
        nettoRange.setText("0");
        ;

    }

    @FXML
    void onClose(ActionEvent event) {
        Main.alertMsg = "Biztos vagy benne, hogy le szeretnéd zárni a kasszát?";
        Main.showAlert();

        BevetelDAO bevetelDAO = new JPABevetelDAO();
        for (Bevetel bev : bevetelDAO.getBevetelek()){
            if (bev.getKasszaZaras()==null){
                bev.setKasszaZaras(LocalDate.now());
                bev.setOsszeg(Main.income);
                bev.setElado(Main.actUser.getUsername());
                bevetelDAO.updateBevetel(bev);
            }
        }
        bruttoDaily.setText("0 Ft");
        nettoDaily.setText("0 Ft");
        Main.income=0;

    }

    @FXML
    void onOpen(ActionEvent event) {

        BevetelDAO bevetelDAO = new JPABevetelDAO();
        Bevetel b = new Bevetel();
        boolean nyitva = false;
        for (Bevetel bev : bevetelDAO.getBevetelek()){
            if(bev.getKasszaZaras()==null){
                Main.alertMsg = "A kassza jelenleg is nyitva van! Amíg a kassza nem kerül lezárásra, addig nem nyitható meg újra!";
                Main.showAlert();
                nyitva=true;
            }
        }

        if(!nyitva){
            b.setKasszaNyitas(LocalDate.now());
            bevetelDAO.saveBevetel(b);
        }

    }

    @FXML
    void leker(ActionEvent event){
        BevetelDAO bDAO = new JPABevetelDAO();
        long from = Date.from(fromDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
        long to = Date.from(toDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
        int bevetel =0;

        for (Bevetel b : bDAO.getBevetelek()){
            if(((from<=Date.from(b.getKasszaZaras().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime())) &&
                    ((to>=Date.from(b.getKasszaNyitas().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()))){
                bevetel+=b.getOsszeg();
            }

        }
        bruttoRange.setText(CurrencyManager.createPattern(String.valueOf(bevetel)));
        nettoRange.setText(CurrencyManager.createPattern(String.valueOf(Math.round(bevetel*0.73))));


    }


}
