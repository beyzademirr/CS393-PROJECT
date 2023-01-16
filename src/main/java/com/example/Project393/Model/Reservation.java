package com.example.Project393.Model;

import javax.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
public class Reservation {
    //Generate a unique 8 digits reservation number in the
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "ID")
    private int id;

    @Column(unique = true)
    private String number;

    @Column(name = "CREATEDATE")
    private Date createDate;

    @Column(name = "DROPOFFDATE")
    private Date dropOffDate;

    @Column(name = "PICKUPDATE")
    private Date pickUpDate;

    @OneToOne
    private Location dropOffLocation;

    @OneToOne
    private Location pickUpLocation;

    @ManyToMany
    @JoinTable(name = "TBL_RES_EQP",
            joinColumns = @JoinColumn(name = "RID",
                    referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "EID",
                    referencedColumnName = "ID"))
    private List<Equipment> equipments;

    @ManyToMany
    @JoinTable(name = "TBL_RES_SER",
            joinColumns = @JoinColumn(name = "RID",
                    referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SID",
                    referencedColumnName = "ID"))
    private List<Service> services;

    @OneToOne()
    private Car car;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Location pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Reservation(int id, String number, Date createDate, Date dropOffDate, Date pickUpDate, Location dropOffLocation, Location pickUpLocation, List<Equipment> equipments,List<Service> services, Car car, String status) {
        this.id = id;
        this.number = number;
        this.createDate = createDate;
        this.dropOffDate = dropOffDate;
        this.pickUpDate = pickUpDate;
        this.dropOffLocation = dropOffLocation;
        this.pickUpLocation = pickUpLocation;
        this.equipments = equipments;
        this.services = services;
        this.car = car;
        this.status = status;
    }

    public Reservation(){

    }
}
