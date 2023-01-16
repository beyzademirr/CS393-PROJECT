package com.example.Project393.Model;


import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "ID")
    private int id;

    @Column(unique=true, name = "BARCODE")
    private String barcode;


    @Column(name = "licensePlateNumber")
    private String licensePlateNumber;
    @Column(name = "passengerCapacity")
    private int passengerCapacity;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;

    @Column(name = "mileage")
    private double mileage;
    @Column(name = "transmissionType")
    private String transmissionType;
    @Column(name = "dailyPrice")
    private double dailyPrice;
    @Column(name = "type")
    private String type;

    @OneToOne(mappedBy="car")
    private Reservation reservation;

    @ManyToOne()
    private Member member;

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Car(int id, String barcode, String licensePlateNumber, int passengerCapacity, String brand, String model, double mileage, String transmissionType, double dailyPrice, String type, Reservation reservation, Member member, String status) {
        this.id = id;
        this.barcode = barcode;
        this.licensePlateNumber = licensePlateNumber;
        this.passengerCapacity = passengerCapacity;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
        this.dailyPrice = dailyPrice;
        this.type = type;
        this.reservation = reservation;
        this.member = member;
        this.status = status;
    }

    public Car(String barcode, String licensePlateNumber, int passengerCapacity, String brand, String model, double mileage, String transmissionType, double dailyPrice, String type, Reservation reservation, Member member, String status) {

        this.barcode = barcode;
        this.licensePlateNumber = licensePlateNumber;
        this.passengerCapacity = passengerCapacity;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
        this.dailyPrice = dailyPrice;
        this.type = type;
        this.reservation = reservation;
        this.member = member;
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }



    public Car(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


}
