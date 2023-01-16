package com.example.Project393.Model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Service {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", unique=true)
    private String name;


    @Column(name = "PRICE")
    private double price;



    @ManyToMany(mappedBy = "services")
    private List<Reservation> reservations;

    public Service() {
    }


    public Service(int id, String name, double price, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.reservations = reservations;
    }
    public Service(String name, double price, List<Reservation> reservations) {

        this.name = name;
        this.price = price;
        this.reservations = reservations;
    }


    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public int getId() {
        return id;
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
}
