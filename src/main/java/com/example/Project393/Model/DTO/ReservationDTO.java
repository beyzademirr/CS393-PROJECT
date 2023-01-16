package com.example.Project393.Model.DTO;



import java.util.ArrayList;
import java.util.Date;


public class ReservationDTO {
    //Generate a unique 8 digits reservation number in the

    private String number;


    private Date dropOffDate;


    private Date pickUpDate;


    private String dropOffLocationName;


    private String pickUpLocationName;


    private int dropOffLocationCode;


    private int pickUpLocationCode;


    private double amount;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getDropOffLocationName() {
        return dropOffLocationName;
    }

    public void setDropOffLocationName(String dropOffLocationName) {
        this.dropOffLocationName = dropOffLocationName;
    }

    public String getPickUpLocationName() {
        return pickUpLocationName;
    }

    public void setPickUpLocationName(String pickUpLocationName) {
        this.pickUpLocationName = pickUpLocationName;
    }

    public int getDropOffLocationCode() {
        return dropOffLocationCode;
    }

    public void setDropOffLocationCode(int dropOffLocationCode) {
        this.dropOffLocationCode = dropOffLocationCode;
    }

    public int getPickUpLocationCode() {
        return pickUpLocationCode;
    }

    public void setPickUpLocationCode(int pickUpLocationCode) {
        this.pickUpLocationCode = pickUpLocationCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
