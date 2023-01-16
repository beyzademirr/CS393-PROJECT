package com.example.Project393.Model;

;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipment {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "ID")
    private int id;


    @Column(name = "NAME", unique=true)
    private String name;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations (List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @ManyToMany(mappedBy = "equipments")
    private List<Reservation> reservations;

    public int getId() {
        return id;
    }

    public Equipment(String name, ArrayList<Reservation> reservations, double price) {
        this.name = name;
        this.reservations = reservations;
        this.price = price;
    }

    public Equipment(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Equipment() {

    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "PRICE")
    private double price;
}
