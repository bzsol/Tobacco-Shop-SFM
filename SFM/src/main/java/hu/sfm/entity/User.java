package hu.sfm.entity;

import javax.persistence.*;

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


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    private String Password;

    @Basic
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
}
