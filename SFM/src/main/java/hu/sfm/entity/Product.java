package hu.sfm.entity;

import javax.persistence.*;

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

    private ProductGroups productGroups;

    @Basic
    @Enumerated(EnumType.STRING)
    public ProductGroups getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(ProductGroups productGroups) {
        this.productGroups = productGroups;
    }
}
