package com.example.Project393.Model;



import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Member {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "MEMBER_ID")
    private int id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "ADDRESS", length = 150)
    private String address;

    @Column(name = "EMAIL", length = 150)
    private String email;

    @Column(name = "PHONE", length = 150)
    private String phone;

    @Column(name = "drivingLicenseNumber", length = 50)
    private int drivingLicenseNumber;

    @OneToMany(mappedBy = "member", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Car> cars;

    public Member() {
    }

    public Member(int id, String name, String address, String email, String phone, int drivingLicenseNumber, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.cars = cars;
    }



    public Member(String name, String address, String email, String phone, int drivingLicenseNumber, List<Car> cars) {

        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.cars = cars;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(int drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}

