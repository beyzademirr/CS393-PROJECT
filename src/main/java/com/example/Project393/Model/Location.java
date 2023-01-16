package com.example.Project393.Model;


import javax.persistence.*;

@Entity
public class Location {
    @Id
    //@GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "LOCATION_ID")
    private int code;


    @Column(name = "NAME", unique=true)
    private String name;

    @Column(name = "ADDRESS", length = 150)
    private String address;

    public Location(int code, String name, String address) {
        this.code = code;
        this.name = name;
        this.address = address;
    }

    public Location(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
