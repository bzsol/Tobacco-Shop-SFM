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

import java.time.LocalDate;

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
    private TextField bruttoAvg;

    @FXML
    private TextField nettoAvg;


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
        BevetelDAO bevetelDAO = new JPABevetelDAO();
        for (Bevetel bev : bevetelDAO.getBevetelek()){
            if (bev.getKasszaZaras()==null){
                bev.setKasszaZaras(LocalDate.now());
                bev.setOsszeg(Main.income);
                bev.setElado(Main.actUser);
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
                System.out.println("Már van nyitva kassza te gyökér!");
                nyitva=true;
            }
        }

        if(!nyitva){
            b.setKasszaNyitas(LocalDate.now());
            bevetelDAO.saveBevetel(b);
        }

    }

    @FXML
    void leker(ActionEvent event) {


    }

}
