package hu.sfm.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Product {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    private String Name;

    @NotNull
    @Basic
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private int Quantity;

    @Basic
    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    private int Price;

    @Basic
    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }



}
