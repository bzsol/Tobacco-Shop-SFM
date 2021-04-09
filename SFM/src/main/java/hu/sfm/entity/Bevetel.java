package hu.sfm.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bevetel {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    private int osszeg;

    @Basic
    public int getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(int osszeg) {
        this.osszeg = osszeg;
    }

    public LocalDate getKasszaNyitas() {
        return kasszaNyitas;
    }

    public void setKasszaNyitas(LocalDate kasszaNyitas) {
        this.kasszaNyitas = kasszaNyitas;
    }

    public LocalDate getKasszaZaras() {
        return kasszaZaras;
    }

    public void setKasszaZaras(LocalDate kasszaZaras) {
        this.kasszaZaras = kasszaZaras;
    }

    @Column(columnDefinition = "DATE")
    private LocalDate kasszaNyitas;

    @Column(columnDefinition = "DATE")
    private LocalDate kasszaZaras;


   private String elado;

   @Basic
    public String getElado() {
        return elado;
    }

    public void setElado(String elado) {
        this.elado = elado;
    }
}
