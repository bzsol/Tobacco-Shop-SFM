package hu.sfm.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class User {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }


    private String Username;


    @Basic(optional = false)
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }


    private String Password;


    @Basic(optional = false)
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private Permission perm;

    @Basic
    @Enumerated(EnumType.STRING)
    public Permission getPerm() {
        return perm;
    }

    public void setPerm(Permission perm) {
        this.perm = perm;
    }
    @Column(columnDefinition = "DATE")
    private LocalDate regDate;

    public LocalDate getregDate() {
        return regDate;
    }

    public void setregDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Basic
    private String vezetekNev;

    public String getVezetekNev() {
        return vezetekNev;
    }

    public void setVezetekNev(String vezetekNev) {
        this.vezetekNev = vezetekNev;
    }

    @Basic
    private String keresztNev;

    public String getKeresztNev() {
        return keresztNev;
    }

    public void setKeresztNev(String keresztNev) {
        this.keresztNev = keresztNev;
    }

    @Basic
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;
    @Basic
    private int sallary;

    public int getSallary() {
        return sallary;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public void setSallary(int sallary) {
        this.sallary = sallary;
    }

    @Column(columnDefinition = "DATE")
    private LocalDate addDate;

}
